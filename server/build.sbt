name := "server"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= {
  val akkaVersion = "2.3.10"
  val sprayVersion = "1.3.3"
  val sparkVersion = "1.5.2"
  Seq(
    //    akka
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    //    spray
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.3.1",
    "io.spray" %% "spray-httpx" % sprayVersion,
    //    spark
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-mllib" % sparkVersion,
    // elastic search client
    "com.sksamuel.elastic4s" % "elastic4s-core_2.10" % "2.3.0",
    // redis
    "com.livestream" %% "scredis" % "2.0.6",
    "com.github.scopt" %% "scopt" % "3.4.0"

  )
}

libraryDependencies += "io.snappydata" % "snappy-spark-mllib_2.10" % "1.6.1"

    