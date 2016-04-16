name := "server"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaVersion = "2.3.10"
  val sprayVersion = "1.3.3"
  val sparkVersion = "1.3.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.3.1",
    "io.spray" %% "spray-httpx" % sprayVersion,
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion
  )
}
    