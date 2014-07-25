package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json

import models._

object PeopleC extends Controller {

	def getPerson = Action(parse.json) {
		implicit request =>
		val fname: String = (request.body \ "fname").as[String]
		val lname: String = (request.body \ "lname").as[String]
		val peopleOpt = People.returnDataByName(fname, lname)
		if (peopleOpt != None) {
		val people = peopleOpt.get
		Ok(Json.obj(
			"id" -> people.id,
			"first_name" -> people.first_name,
			"last_name" -> people.last_name,
			"status" -> people.status,
			"office" -> people.office,
			"phone" -> people.phone,
			"email" -> people.email,
			"additional_info" -> people.additional_info
		))
		} else {
			Ok(Json.obj("notfound" -> 1))
		}
	}

	def getLocation = Action(parse.json) {
		implicit request =>
		val fname: String = (request.body \ "loc_fname").as[String]
		val lname: String = (request.body \ "loc_lname").as[String]
		val office = People.returnOfficeByName(fname, lname)
		val phone = People.returnPhoneByName(fname, lname)
		Ok(Json.obj("office" -> office, "phone" -> phone))
	}
}
