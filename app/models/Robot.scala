package models
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import play.api.libs.json._
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Location(x: Int, y: Int)

object Location{


  implicit val locationReads:Reads[Location] = Json.reads[Location]

  val simple = {
    get[String]("location.name") ~
    get[Int]("location.x") ~
    get[Int]("location.y") map {
      case name~x~y => Location(x, y)
    }
  }

  def getLocation: Location = {
    val name = "CoBot3"
    val loc = DB.withConnection { implicit connection =>
      SQL(
        """
        select * from location where name={name}
        """
      ).on(
        "name" -> name
      ).as(Location.simple.singleOpt)
    }
    loc.getOrElse(Location(580, 1040)) //default
  }



  def setLocation(x: Int, y: Int) = {
    val name = "CoBot3"
    DB.withConnection { implicit connection =>
      SQL(
        """
        replace into location (name, x, y) values ({name}, {x}, {y})
        """
      ).on(
        "name" -> name,
        "x" -> x,
        "y" -> y
      ).executeUpdate()
    }
  }


  implicit val locationWrites = (
  (__ \ "x").write[Int] ~
  (__ \ "y").write[Int]
  )(unlift(Location.unapply))
}
