package restapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import proto.hello.v1.Hello.{HelloRequestV1, HelloResponseV1}
import proto.hello.v2.Hello.{HelloRequestV2, HelloResponseV2}
import restapp.dto.request.{Translate => RequestTranslate}
import restapp.dto.response.{Translate => ResponseTranslate}
import restapp.service.HelloService

/**
  * Created by tabiul on 15 Jul 2016.
  */
@RestController
class GreetingController {

    @Autowired
    private var helloService: HelloService = _

    @RequestMapping(path = Array("/v1/hello"), method = Array(RequestMethod.POST),
        consumes = Array("application/x-protobuf"), produces = Array("application/x-protobuf"))
    def sayHelloV1(@RequestBody request: HelloRequestV1): HelloResponseV1 = {
        ResponseTranslate.translateResponseV1(helloService.sayHello(RequestTranslate.translate(request)))
    }

    @RequestMapping(path = Array("/v2/hello"), method = Array(RequestMethod.POST),
        consumes = Array("application/x-protobuf"), produces = Array("application/x-protobuf"))
    def sayHelloV2(@RequestBody request: HelloRequestV2): HelloResponseV2 = {
        ResponseTranslate.translateResponseV2(helloService.sayHello(RequestTranslate.translate(request)))

    }


}

