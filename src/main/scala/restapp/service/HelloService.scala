package restapp.service

import org.springframework.stereotype.Service
import restapp.dto.request.{HelloRequest, HelloRequestWithFullName, HelloRequestWithUserName}
import restapp.dto.response.{HelloResponse, NormalStringResponse}

/**
  * Created by tabiul on 28/07/16.
  */
@Service
class HelloService {
   def sayHello(request: HelloRequest):HelloResponse = {
       request match {
           case m: HelloRequestWithUserName => NormalStringResponse(s"hello ${m.userName}")
           case m: HelloRequestWithFullName => NormalStringResponse(s"hello ${m.firstName} ${m.middleName} ${m.lastName}")
       }
   }
}
