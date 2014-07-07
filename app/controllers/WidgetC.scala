package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json

import models._

object WidgetC extends Controller {
  def save = Action(parse.json) { request =>
    Widget.saveLayout(request.session.get("email").get, request.body)
    val json = Json.toJson(Map("saved" -> true))
    Ok(json)
  }
}
