package br.com.falconer.customer.domain

import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.annotations.BsonProperty

import java.lang.annotation.Documented

@Documented
case class Customer (
  @BsonProperty("_id") id: ObjectId,
  name: String,
  document: Int) {
}