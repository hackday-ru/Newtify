name := "server"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= {
  val akkaVersion = "2.3.10"
  val sprayVersion = "1.3.3"
  val sparkVersion = "1.5.2"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.3.1",
    "io.spray" %% "spray-httpx" % sprayVersion,
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-mllib" % sparkVersion
  )
}
    