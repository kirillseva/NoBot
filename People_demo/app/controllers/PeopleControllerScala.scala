package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.libs.json.JsResult
import models.People
import scala.collection.JavaConverters._

object PeopleControllerScala extends Controller {
	def showAllPeople = Action { implicit request =>
		val allPeople = People.getAll
		//val matt = allPeople.length
		val res = allPeople.asJava
		//Ok(Json.obj("length" -> matt))
		Ok(views.html.list(res))
	}
	
	//def getOfficeByName {
	
	//}
}