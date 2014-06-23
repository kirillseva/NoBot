name := """Cobot Nobotics"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(jdbc, anorm, cache, ws)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

resolvers += Classpaths.sbtPluginReleases

libraryDependencies ++= Seq(
  "org.webjars" 			%% 	"webjars-play" 				 % "2.3.0",
  "org.webjars" 			%	"bootstrap" 				      % "3.1.1-1",
  "org.webjars" 			% 	"bootswatch-flatly"			% "3.1.1",
  "org.webjars" 			% 	"html5shiv" 				% "3.7.0",
  "org.webjars" 			% 	"respond" 					% "1.4.2",
  "org.webjars"       % "angularjs"           % "1.2.18",
  "org.scalatest"     %% "scalatest"          % "2.1.6"            % "test"
)

instrumentSettings

ScoverageKeys.minimumCoverage := 70

ScoverageKeys.failOnMinimumCoverage := false

ScoverageKeys.highlighting := {
  if (scalaBinaryVersion.value == "2.10") false
  else false
}

publishArtifact in Test := false

parallelExecution in Test := false
