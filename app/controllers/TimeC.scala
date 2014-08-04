package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json

import models._

object TimeC extends Controller {
  def save = Action(parse.json) { request =>
    val time = (request.body \ "time").as[String]
    val t:Time = Time(request.session.get("email").get, time)
    Time.saveTime(t)
    val json = Json.toJson(Map("saved" -> true))
    Ok(json)
  }

  def get = Action { request =>
    val savedtime = Time.readTime(request.session.get("email").get)
    val json = Json.toJson(
      Map(
        "savedtime" -> savedtime.get.time
      )
    )
    Ok(json)
  }
}
