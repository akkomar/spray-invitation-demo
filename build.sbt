name := "spray-invitation-demo"

version := "1.0"

scalaVersion := "2.11.2"

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.1"
  Seq(
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-testkit" % sprayV % "test",
    "io.spray" %% "spray-json" % "1.2.6",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.specs2" %% "specs2-core" % "2.4.2" % "test",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "ch.qos.logback" % "logback-classic" % "1.1.2"
  )
}

resolvers += "spray repo" at "http://repo.spray.io"
