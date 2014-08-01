package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class People(id: Int, first_name: Option[String], last_name: Option[String], status: Option[String], office: Option[Int], phone: Option[String], email: Option[String], additional_info: Option[String])

object People {

  // -- Parsers

  /**
   * Parse a Person from a ResultSet
   */
  val simple = {
    get[Int]("people.id") ~
    get[Option[String]]("people.first_name") ~
    get[Option[String]]("people.last_name") ~
    get[Option[String]]("people.status") ~
    get[Option[Int]]("people.office") ~
    get[Option[String]]("people.phone") ~
    get[Option[String]]("people.email") ~
	get[Option[String]]("people.additional_info")	map {
      case id~first_name~last_name~status~office~phone~email~additional_info => People(id, first_name, last_name, status, office, phone, email, additional_info)
    }
  }

  // -- Queries

  /**
   * Retrieve Person by name.
   */
  def returnDataByName(first_name: String, last_name: String): Option[People] = {
    DB.withConnection { implicit connection =>
      SQL("select * from people where first_name = {first_name} AND last_name = {last_name}").on(
        "first_name" -> first_name,
		"last_name" -> last_name
      ).as(People.simple.singleOpt)
    }
  }

  /**
   * Retrieve Office by name.
   */
  def returnOfficeByName (first_name: String, last_name: String): Option[Int] = {
    DB.withConnection { implicit connection =>
      val result: List[Option[Int]] = SQL("select office from people where first_name = {first_name} AND last_name = {last_name}").on(
        "first_name" -> first_name,
		"last_name" -> last_name
		).as(get[Option[Int]]("office") *)
	if (result.isEmpty) {
		None
	} else {
		result.head
	}
    }
  }

  /**
   * Retrieve Phone by name.
   */
  def returnPhoneByName (first_name: String, last_name: String): Option[String] = {
    DB.withConnection { implicit connection =>
      val result: List[Option[String]] = SQL("select phone from people where first_name = {first_name} AND last_name = {last_name}").on(
        "first_name" -> first_name,
		"last_name" -> last_name
		).as(get[Option[String]]("phone") *)
	if (result.isEmpty) {
		None
	} else {
		result.head
	}
    }
  }

 }
