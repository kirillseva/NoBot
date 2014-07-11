package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import play.api.libs.json._

import scala.language.postfixOps
import play.api.libs.functional.syntax._

case class Widget(id: String, col: Int, row: Int, size_x: Int, size_y: Int)

case class LastID(id: Long)

object LastID{
  val simple = {
    get[Long]("ID") map {
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
      Widget("weather", 2, 1, 1, 1),
      Widget("map", 4, 1, 3, 2)
    )
  }

  val simple = {
    get[String]("widget.id") ~
    get[Int]("widget.col") ~
    get[Int]("widget.row") ~
    get[Int]("widget.size_x") ~
    get[Int]("widget.size_y") map {
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
    //first, add layout to DB
    //add the new layout
    DB.withConnection { implicit connection =>
      SQL(
        """
        replace into layout (task, email)
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
        SELECT max(id) as ID from layout where
        task={task} and email={email}
        """
      ).on(
        "task" -> task,
        "email" -> email
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
          insert into widget (id, col, row, size_x, size_y)
          values ({id}, {col}, {row}, {size_x}, {size_y})
          """
        ).on(
          "id" -> widget.id,
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
          SELECT max(prim_id) as ID from widget
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
