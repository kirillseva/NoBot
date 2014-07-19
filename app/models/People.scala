package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class People(id: Int, first_name: String, last_name: String, status: String, office: Int, phone: String, email: String, additional_info: String)

object People {

  // -- Parsers

  /**
   * Parse a Person from a ResultSet
   */
  val simple = {
    get[Int]("people.id") ~
    get[String]("people.first_name") ~
    get[String]("people.last_name") ~
	get[String]("people.status") ~
    get[Int]("people.office") ~
	get[String]("people.phone") ~
    get[String]("people.email") ~
	get[String]("people.additional_info")	map {
      case id~first_name~last_name~status~office~phone~email~additional_info => People(id, first_name, last_name, status, office, phone, email, additional_info)
    }
  }

  // -- Queries

  /**
   * Retrieve Person by name.
   */
  def returnDataByName(first_name: String, last_name: String): Option[People] = {
    DB.withConnection { implicit connection =>
      SQL("select * from people where first_name = {first_name} OR last_name = {last_name}").on(
        "first_name" -> first_name,
		"last_name" -> last_name
      ).as(People.simple.singleOpt)
    }
  }

  /**
   * Retrieve Office by name.
   */
  def returnOfficeByName (first_name: String, last_name: String): Option[People] = {
    DB.withConnection { implicit connection =>
      SQL("select office from people where first_name = {first_name} OR last_name = {last_name}").on(
        "first_name" -> first_name,
		"last_name" -> last_name
		).as(People.simple.singleOpt)
    }
  }
  
  /**
   * Retrieve Phone by name.
   */
  def returnPhoneByName (first_name: String, last_name: String): Option[People] = {
    DB.withConnection { implicit connection =>
      SQL("select phone from people where first_name = {first_name} OR last_name = {last_name}").on(
        "first_name" -> first_name,
		"last_name" -> last_name
		).as(People.simple.singleOpt)
    }
  }
  
  /**
   * Retrieve email by name.
   */
  def returnEmailByName (first_name: String, last_name: String): Option[People] = {
    DB.withConnection { implicit connection =>
      SQL("select email from people where first_name = {first_name} OR last_name = {last_name}").on(
        "first_name" -> first_name,
		"last_name" -> last_name
		).as(People.simple.singleOpt)
    }
  }

}