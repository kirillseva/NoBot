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
  val loc = "Pittsburgh" //cobot's location


  def OWMweather = Action.async { implicit request =>
    val URL = "http://api.openweathermap.org/data/2.5/find?q=" + loc + "&units=metric"
    WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
            "temp" -> (res \\ "temp")(0).as[Int],
            "humidity" -> (res \\ "humidity")(0).as[Int],
            "location" -> loc,
            "description" -> (res \\ "description")(0).as[String],
            "code" -> ((res \\ "weather" )(0) \\ "id")(0).as[Int]
          ))
    }

  }

}
