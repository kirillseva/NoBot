package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class Widget(id: String, rows: Int, cols: Int, xdim: Int, ydim: Int)
