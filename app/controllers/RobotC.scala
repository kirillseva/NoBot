package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json
import models._

object RobotC extends Controller {
  def getLocation = Action { request =>
    val loc = Location.getFakeLocation
    val json = Json.toJson(loc)
    Ok(json)
  }
}
