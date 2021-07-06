package br.com.falconer.customer.config

import br.com.falconer.customer.domain.Customer
import cats.effect.{IO, Resource}
import org.bson.codecs.ValueCodecProvider
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.connection.ConnectionPoolSettings
import org.mongodb.scala.{ConnectionString, MongoClient, MongoClientSettings, MongoDatabase}
import org.mongodb.scala.bson.codecs.Macros._

object MongoConnectionFactory {

  private val registry: CodecRegistry =
    fromProviders(
      classOf[Customer],
      MongoClient.DEFAULT_CODEC_REGISTRY,
      new ValueCodecProvider())

  def connectMongoDb(): Resource[IO, MongoDatabase] = {
    getMongoDbConnection()
      .map(_.getDatabase("app").withCodecRegistry(registry))
  }

  private def getMongoDbConnection(): Resource[IO, MongoClient] = {
    Resource.make(IO(MongoClient(getMongoClientSettings())))(conn => IO(conn.close()))
  }

  private def getMongoClientSettings(): MongoClientSettings = {
    MongoClientSettings
      .builder()
      .applyConnectionString(
        new ConnectionString("mongodb://admin:admin@localhost:27017/app"))
      .applyToConnectionPoolSettings(poolSettings => {
        poolSettings.applySettings(
          ConnectionPoolSettings.builder()
            .maxSize(100)
            .minSize(5)
            .build())
      })
      .codecRegistry(DEFAULT_CODEC_REGISTRY)
      .build()
  }

}
