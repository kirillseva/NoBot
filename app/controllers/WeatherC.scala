package controllers

import play.api.mvc.{Action, Controller}
import play.api._
import play.api.mvc._
import play.api.Play.current
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
    val owmAPPID = "9b3e66306d1a439196275aaa44f66b81"
    val URL = "http://api.openweathermap.org/data/2.5/find?q=" + loc + "&units=metric" + "&APPID=" + owmAPPID
    WS.url(URL).get.map { response =>
      val res = response.json
      Ok(Json.obj(
            "temp" -> (res \\ "temp")(0).as[Float],
            "humidity" -> (res \\ "humidity")(0).as[Int],
            "location" -> loc,
            "description" -> (res \\ "description")(0).as[String],
            "code" -> ((res \\ "weather" )(0) \\ "id")(0).as[Int]
          ))
    }
  }


    def TheWeatherChannel = Action.async { implicit request =>
      val URL = "http://api.wunderground.com/api/002d3c7fc5a4fb0c/conditions/q/"+ loc + ".json"
      WS.url(URL).get.map { response =>
        val res = response.json
        Ok(Json.obj(
              "temp" -> (res \\ "temp_c")(0).as[Float],
              "humidity" -> (res \\ "relative_humidity")(0).as[String].substring(0,2).toInt,
              "location" -> loc,
              "description" -> (res \\ "weather")(0).as[String],
              "code" -> -1
            ))
      }
  }

}
