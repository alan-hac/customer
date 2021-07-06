val http4sVersion = "0.21.22"
val mongoDbVersion = "4.2.3"
val scalaTestVersion = "3.2.9"
val catsEffectVersion = "3.0.0"
val logbackVersion = "1.2.3"

lazy val customer = (project in file("."))
  .settings(
    name := "customer",
    organization := "br.com.falconer",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,

      "org.mongodb.scala" %% "mongo-scala-driver" % mongoDbVersion,

      "ch.qos.logback" % "logback-classic" % logbackVersion,

      "org.scalatest" %% "scalatest" % scalaTestVersion % Test
    )
  )

