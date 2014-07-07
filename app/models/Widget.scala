package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import play.api.libs.json._

import scala.language.postfixOps

case class Widget(id: String, row: Int, col: Int, xdim: Int, ydim: Int, xmax: Int, ymax: Int, xmin: Int, ymin: Int)

object Widget{

  def default = {
    Seq(
      Widget("hello", 1, 1, 1, 1, 1, 2, 1, 1),
      Widget("world", 1, 2, 2, 1, 2, 2, 1, 1),
      Widget("weather", 2, 1, 1, 1, 1, 1, 1, 1)
    )
  }

  

  def getLayout(task: String, email: String): Seq[Widget] = {
    DB.withConnection { implicit connection =>
      var a = SQL(
        """
        select * from widgets where
        email = {email} and task = {task}
        """
      ).on(
        "task" -> task,
        "email" -> email
      ).as(WDATA.simple.singleOpt)

      /*val json = Json.parse(a)*/
      println(a)

      a.get.layout
    }
  }


  /**
   * Set widget layout for a user.
   */

  def saveLayout(email: String, js: JsValue) = {
    DB.withConnection { implicit connection =>
      val task = (js \\ "task")(0).as[String]
      val layout = Json.stringify((js \\ "widgets")(0))
      val id = email + task
      println(layout)
      println(task)
      SQL(
        """
          insert into widgets values (
            {id}, {task}, {email}, {layout}
          )
        """
      ).on(
        "id" -> id,
        "task" -> task,
        "email" -> email,
        "layout" -> layout
      ).executeUpdate()
    }
  }
}
