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
    Ok(json)
  }

  def setLocation = Action(parse.json) { request =>
    val loc = request.body.validate[Location].get
    Location.setLocation(loc.x, loc.y)
    val json = Json.toJson(Map("success" -> 1))
    Ok(json)
  }
}
