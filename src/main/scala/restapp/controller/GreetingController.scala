package restapp.controller

import com.restapp.scala.proto.Hello.{HelloRequest, HelloResponse}
import org.springframework.web.bind.annotation._

/**
  * Created by tabiul on 15 Jul 2016.
  */
@RestController
class GreetingController {
  @RequestMapping(path = Array("/hello"), method = Array(RequestMethod.POST),
    consumes = Array("application/x-protobuf"), produces = Array("application/x-protobuf"))
  @ResponseBody
  def sayHello(@RequestBody request: HelloRequest): HelloResponse = {
    val responseBuilder = HelloResponse.newBuilder()
    responseBuilder.setMessage(s"Hello ${request.getUsername}")
    return responseBuilder.build()

  }
}
