import play.Project._

name := "weather-demo"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)

resolvers += Classpaths.sbtPluginReleases

play.Project.playScalaSettings
