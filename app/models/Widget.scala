package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import play.api.libs.json._

import scala.language.postfixOps
import play.api.libs.functional.syntax._

case class Widget(id: String, col: Int, row: Int, size_x: Int, size_y: Int)

case class LastID(id: Int)

object LastID{
  val simple = {
    get[Int]("LAST_INSERT_ID()") map {
      case id => LastID(id)
    }
  }
}

object Widget{

  implicit val widgetFormat:Format[Widget] = Json.format[Widget]
  val readUserFromInput = (__ \ "widgets").read[List[Widget]]

  def default = {
    Seq(
      Widget("hello", 1, 1, 1, 1),
      Widget("world", 1, 2, 2, 1),
      Widget("weather", 2, 1, 1, 1)
    )
  }

  val simple = {
    get[String]("widget.id") ~
    get[Int]("user.col") ~
    get[Int]("user.row") ~
    get[Int]("user.size_x") ~
    get[Int]("user.size_y") map {
      case id~col~row~size_x~size_y => Widget(id,col,row,size_x,size_y)
    }
  }

  def getLayout(task: String, email: String): Seq[Widget] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
        select widget.id, widget.col, widget.row, widget.size_x, widget.size_y
        from widget, layout, widget_layout where
        layout.id=widget_layout.layout_id and widget.prim_id=widget_layout.widget_id and
        layout.task={task} and layout.email={email}
        """
      ).on(
        "task" -> task,
        "email" -> email
      ).as(Widget.simple *)
    }
  }

  /**
  * Return the list of saved widgets. If none found, use default
  */

  def saved(task: String, email: String): Seq[Widget] = {
    var wjts = getLayout(task, email)
    if (wjts.isEmpty) wjts = Widget.default
    wjts
  }


  /**
  * Set widget layout for a user.
  */

  def saveLayout(email: String, js: JsValue) = {
    val task = (js \\ "task")(0).as[String]
    val layout = (js \\ "widgets")(0)
    //first, add layout to DB (it's easy)
    DB.withConnection { implicit connection =>
      SQL(
        """
        insert into layout (task, email)
        values ({task}, {email})
        """
      ).on(
        "task" -> task,
        "email" -> email
      ).executeUpdate()
    }
    //get the latest id of layout
    val layoutID = DB.withConnection { implicit connection =>
      SQL(
        """
        SELECT LAST_INSERT_ID()
        """
      ).as(LastID.simple.singleOpt)
    }
    //parse json into array of widgets
    val content = js.validate(readUserFromInput).get //returns a List[Widget]
    //iterate over each widget
    for (widget<-content){
      //write the widget to DB
      DB.withConnection { implicit connection =>
        SQL(
          """
          insert into widget (col, row, size_x, size_y)
          values ({col}, {row}, {size_x}, {size_y})
          """
        ).on(
          "col" -> widget.col,
          "row" -> widget.row,
          "size_x" -> widget.size_x,
          "size_y" -> widget.size_y
        ).executeUpdate()
      }
      //get the latest ID
      var widgetID = DB.withConnection { implicit connection =>
        SQL(
          """
          SELECT LAST_INSERT_ID()
          """
        ).as(LastID.simple.singleOpt)
      }
      //write to widget_layout table to sync widgets and layouts
      DB.withConnection { implicit connection =>
        SQL(
          """
          insert into widget_layout (widget_id, layout_id)
          values ({widget_id}, {layout_id})
          """
        ).on(
          "widget_id" -> widgetID.get.id,
          "layout_id" -> layoutID.get.id
        ).executeUpdate()
      }
    }//end of for loop
  }//end of safe layout
}
