package com.eklaweb.googlegeocodingapi


object Status extends Enumeration {
    val OK, ZERO_RESULTS, OVER_QUERY_LIMIT, REQUEST_DENIED, INVALID_REQUEST = Value
  }

  case class Response(status: String, results: List[Result]) {
    import Status._
    def isOk : Boolean = OK == withName(status)
  }

  object AddressComponentType extends Enumeration {
    val street_number, route, sublocality, locality, administrative_area_level_1, country, postal_code = Value
  }

  case class Result(types: List[String],
                    formatted_address: String,
                    address_components: List[AddressComponent],
                    geometry: Geometry,
                    partial_match: Option[Boolean]) {
    def isStreetLevel = types.contains("street_address")

    def addressComponent(addressComponentType : AddressComponentType.Value) =
      address_components.filter((component) => component.types.contains(addressComponentType.toString)).headOption
  }

  case class AddressComponent(long_name: String,
                              short_name: String,
                              types: List[String])

  case class Geometry(location: GeoPoint,
                      location_type: String,
                      viewport: ViewPort,
                      bounds: Option[ViewPort])

  case class ViewPort(southwest: GeoPoint,
                      northeast: GeoPoint)

  case class GeoPoint(lat: Double, lng: Double)