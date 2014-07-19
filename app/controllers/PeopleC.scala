package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json

import models._

object PeopleC extends Controller {

	def getPerson = Action(parse.json) {
		request => println(request.body)
		val json = Json.toJson(Map("success" -> 1))
		Ok(json)
	}
	
	def getLocation = Action(parse.json) {
		request => println(request.body)
		val json = Json.toJson(Map("success" -> 1))
		Ok(json)
	}
/*
    def returnOfficeByName = Action(parse.json) { request =>
    println(request.body)
    val office = (request.body \ "office").as[Int]
    People.returnOfficeByName(office, request.session.get("first_name").get)
    Ok(json)
  }
  */

}