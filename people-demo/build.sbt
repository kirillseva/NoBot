name := "People_2"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  jdbc,
  anorm,
  "mysql" % "mysql-connector-java" % "5.1.31",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j"    % "2.1.2",
  "org.scalatest"              %% "scalatest"              % "2.1.6"            % "test"
)     

instrumentSettings

ScoverageKeys.minimumCoverage := 80

ScoverageKeys.failOnMinimumCoverage := false

ScoverageKeys.highlighting := {
  if (scalaBinaryVersion.value == "2.10") false
  else false
}

publishArtifact in Test := false

parallelExecution in Test := false
