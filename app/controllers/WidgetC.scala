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

  def restoreDefault = Action(parse.json) { request =>
    val task = (request.body \ "task").as[String]
    Widget.restoreDefault(request.session.get("email").get, task)
    val json = Json.toJson(Map("restored" -> true))
    Ok(json)
  }

  def addWidget = Action(parse.json) { request =>
    println(request.body)
    val name = (request.body \ "name").as[String]
    val task = (request.body \ "task").as[String]
    Widget.addWidget(name, task, request.session.get("email").get)

    val json = Json.toJson(Map("added" -> name))
    Ok(json)
  }

  def removeWidget = Action(parse.json) { request =>
    val name = (request.body \ "name").as[String]
    val task = (request.body \ "task").as[String]
    Widget.removeWidget(name, task, request.session.get("email").get)

    val json = Json.toJson(Map("removed" -> name))
    Ok(json)
  }
}
