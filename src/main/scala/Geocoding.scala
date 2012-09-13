package com.eklaweb.googlegeocodingapi

import net.liftweb.json._
import dispatch._
import dispatch.liftjson.Js._

object Geocoding {
  val endPoint = "http://maps.googleapis.com/maps/api/geocode/json"
  val defaultParams = List(
      ("sensor", "true"),
      ("region", "fr"),
      ("language", "en")
  )
  def encodeAddress(address: String, params: List[(String, String)] = defaultParams) : Response = {
    implicit val formats = DefaultFormats
    val req = url(apiUrl) <<? (("address", address) :: params)
    Http(req OK as.String).either() match {
      case Right(resJson) => parse(resJson).extract[Response]
      case Left(unexpected) => throw new IllegalStateException("unexpected response from google geocoder api: " + unexpected)
    }
  }
}

