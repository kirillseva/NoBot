package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import scala.language.postfixOps

case class Time (email: String, time: String)

object Time {

  // default value for when no value is stored in the DB yet
  var default = "No value. Yet."

  //a helper method that converts result of the reading from
  //a database to a case class, a native scala container
  val simple = {
    get[String]("savedtime.email") ~
    get[String]("savedtime.time") map {
      case email~time => Time(email, time)
    }
  }

  def readTime(email: String): Option[Time] = {
    var result = DB.withConnection { implicit connection =>
      SQL("select * from savedtime where email = {email}").on(
        "email" -> email
      ).as(Time.simple.singleOpt)
    }
    //if nothing found in database - return refault value
    if (result.isEmpty){
      result = Some(Time(email, default))
    }
    result
  }

  def saveTime(t: Time) {
    DB.withConnection { implicit connection =>
      SQL(
        """
          replace into savedtime (email, time) values
          ( {email}, {time} )
        """
      ).on(
        "email" -> t.email,
        "time" -> t.time
      ).executeUpdate()
  }
}
