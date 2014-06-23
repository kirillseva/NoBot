name := "People_2"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  jdbc,
  anorm,
  "mysql" % "mysql-connector-java" % "5.1.31"
)     

play.Project.playJavaSettings

ScoverageSbtPlugin.instrumentSettings

CoverallsPlugin.coverallsSettings
