package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Location(x: Int, y: Int)

case class Robot(location: Location)

object Robot{

  // just a stub for now. Ideally update all of the robot's aspects here
  def updateInfo: Robot = {
    val loc = Location.getLocation
    Robot(loc)
  }
}

object Location{

  //just a stub for now. Ideally will get the latest robot's location
  def getLocation: Location = {
    Location(14, 88)
  }

  implicit val locationWrites = (
  (__ \ "x").write[Int] ~
  (__ \ "y").write[Int]
  )(unlift(Location.unapply))
}
