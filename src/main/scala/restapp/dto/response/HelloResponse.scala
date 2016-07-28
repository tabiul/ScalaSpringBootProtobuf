package restapp.dto.response

import proto.hello.v1.Hello.HelloResponseV1
import proto.hello.v2.Hello.HelloResponseV2

/**
  * Created by tabiul on 28/07/16.
  */
sealed trait HelloResponse
case class NormalStringResponse(val message: String) extends HelloResponse

object Translate {
    def translateResponseV1(response: HelloResponse) : HelloResponseV1 = {
        response match {
            case r: NormalStringResponse => {
                val builder = HelloResponseV1.newBuilder()
                builder.setMessage(r.message)
                builder.build()
            }
        }
    }

    def translateResponseV2(response: HelloResponse) : HelloResponseV2 = {
        response match {
            case r: NormalStringResponse => {
                val builder = HelloResponseV2.newBuilder()
                builder.setMessage(r.message)
                builder.build()
            }
        }
    }

}


