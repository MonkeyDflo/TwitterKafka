name := "TwitterKafka"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka" % "3.0.0",
  "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0"
)