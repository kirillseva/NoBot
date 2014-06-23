package models

case class Weather (
  city: String, celcius: Boolean, temp: Int, humidity: Int, description: String, conditions: Int)

object Weather {
  var stubWeather = Weather("Paris", true, 18, 64, "Partly Cloudy", 30)
}
