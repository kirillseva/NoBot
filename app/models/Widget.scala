package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class Widget(id: String, row: Int, col: Int, xdim: Int, ydim: Int)

object Widget{

  def findAll = {
    Seq(
      Widget("hello", 1, 1, 1, 1),
      Widget("world", 1, 2, 2, 1),
      Widget("weather", 2, 1, 1, 1)
    )
  }
}
