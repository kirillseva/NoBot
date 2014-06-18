// Comment to get more information during initialization
logLevel := Level.Warn

name := "CoBot framework"

version := "0.1"

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Classpaths.sbtPluginReleases

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.3")

addSbtPlugin("com.sksamuel.scoverage" % "sbt-scoverage" % "0.95.1")

addSbtPlugin("com.sksamuel.scoverage" %% "sbt-coveralls" % "0.0.5")

val appDependencies = Seq(
    "jp.t2v" %% "play2-auth"      % "0.12.0",
    "jp.t2v" %% "play2-auth-test" % "0.12.0" % "test"
  )
