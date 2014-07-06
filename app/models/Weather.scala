package models

case class Weather (
  city: String, celcius: Boolean, tempC: Float, tempF: Float, humidity: Int, description: String, conditions: Int)

object Weather {
  var stubWeather = Weather("Paris", true, 18, 60, 64, "Partly Cloudy", 30)
}
