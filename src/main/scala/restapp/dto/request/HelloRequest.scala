package restapp.dto.request

import proto.hello.v1.Hello.HelloRequestV1
import proto.hello.v2.Hello.HelloRequestV2

/**
  * Created by tabiul on 28/07/16.
  */
sealed trait HelloRequest
case class HelloRequestWithUserName(val userName: String) extends HelloRequest
case class HelloRequestWithFullName(val firstName: String, val middleName: String, val lastName: String) extends HelloRequest

object Translate {
    def translate(request: HelloRequestV1) : HelloRequest = {
        HelloRequestWithUserName(request.getUserName)
    }

    def translate(request: HelloRequestV2) : HelloRequest = {
        HelloRequestWithFullName(request.getFirstName, request.getMiddleName, request.getLastNameName)
    }

}