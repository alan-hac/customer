package br.com.falconer.customer.route

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io._

object CustomerRoute {
  def routes(): HttpRoutes[IO] = {
    HttpRoutes.of[IO] {
      case POST -> Root / "customers" => Created("customers")
      case GET -> Root / "customers" => Ok("customers")
      case PUT -> Root / "customers" => Ok("customers")
      case DELETE -> Root / "customers" => Ok("customers")
    }
  }
}
