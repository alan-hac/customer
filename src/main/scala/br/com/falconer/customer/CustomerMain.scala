package br.com.falconer.customer

import br.com.falconer.customer.config.MongoConnectionFactory.connectMongoDb
import br.com.falconer.customer.route.CustomerRoute
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.HttpRoutes
import org.http4s.implicits.http4sKleisliResponseSyntaxOptionT
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.log4s.getLogger

import scala.concurrent.ExecutionContext.global

object CustomerMain extends IOApp {

  private val logger = getLogger

  def run(args: List[String]): IO[ExitCode] = {
    logger.info("starting the customer application")
    connectMongoDb()
    startWebServer()
  }

  def startWebServer(): IO[ExitCode] = {
    BlazeServerBuilder[IO](global)
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(CustomerRoute.routes().orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }

}