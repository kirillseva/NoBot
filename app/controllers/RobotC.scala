package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json
import models._

object RobotC extends Controller {
  def getLocation = Action { request =>
    val loc = Location.getLocation
    val json = Json.toJson(loc)
    println(json)
    Ok(json)
  }
}
