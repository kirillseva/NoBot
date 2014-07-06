package models
import play.api.db._
import play.api.Play.current
import scala.language.postfixOps
import play.api.data._
import play.api.data.Forms._

case class Weather (
  city: String, celcius: Boolean, tempC: Float, tempF: Float, humidity: Int, description: String, conditions: Int)

object Weather {
  var stubWeather = Weather("Paris", true, 18, 60, 64, "Partly Cloudy", 30)
}
