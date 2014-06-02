package models
import play.api.libs.json
import play.api.mvc.Action

import play.api.libs.ws._
import scala.concurrent.Future

case class Weather (
  woeid: Int, city: String, celcius: Boolean, temp: Int, humidity: Int, description: String, conditions: Int)

object Weather {
  var stubWeather = Weather(615702, "Paris", true, 18, 64, "Partly Cloudy", 30)


}
