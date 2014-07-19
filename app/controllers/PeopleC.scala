package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json

import models._

object PeopleC extends Controller {

	def getPerson = Action {
		implicit request => println(request.body)
		val json = Json.toJson(Map("success" -> 1))
		Ok(json)
	}
	
	def getLocation = Action {
		implicit request => 
		val body: AnyContent = request.body
		val formContent = body.asFormUrlEncoded
		println(formContent)
		val json = Json.toJson(Map("success" -> "Yes", "fname" -> formContent.get("loc_fname").toString))
		Ok(json)
	}
}