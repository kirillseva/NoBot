package controllers

import play.api.mvc.{Action, Controller}
import play.api._
import play.api.mvc._
import models.Weather
import scala.concurrent._
import scala.concurrent.duration._
import play.api.libs.ws._
import scala.concurrent.Future
import ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.libs.json.JsResult

object WeatherC extends Controller {
  def showC = Action.async { implicit request =>
    val loc = "Pittsburgh"
    val URL = "http://api.openweathermap.org/data/2.5/find?q=" + loc + "&units=metric"
    WS.url(URL).get.map { response =>
      Ok(response.json)
    }

  }
}
