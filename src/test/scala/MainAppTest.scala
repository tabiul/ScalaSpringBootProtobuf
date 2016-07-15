import com.restapp.scala.proto.Hello.{HelloRequest, HelloResponse}
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.{SpringApplicationConfiguration, WebIntegrationTest}
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.http.{HttpEntity, HttpHeaders}
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.client.RestTemplate
import restapp.MainApp

/**
  * Created by tabiul on 15 Jul 2016.
  */
@Configuration
class RestClientConfiguration {
  @Bean
  def restTemplate(hmc: ProtobufHttpMessageConverter): RestTemplate = {
    val restTemplate = new RestTemplate()
    restTemplate.getMessageConverters.add(hmc)
    return restTemplate
  }
}

@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[MainApp],classOf[RestClientConfiguration]))
@WebIntegrationTest(Array("server.port=8080"))
class MainAppTest {
  @Autowired
  private val restTemplate: RestTemplate = null

  @Test
  def test() = {
    val requestBuilder = HelloRequest.newBuilder()
    val request = requestBuilder.setUsername("tabiul").build()
    val headers = new HttpHeaders()
    headers.setContentType(ProtobufHttpMessageConverter.PROTOBUF)
    headers.setContentLength(request.toByteArray.length)
    val helloRequest = new HttpEntity[Array[Byte]](requestBuilder.build().toByteArray, headers)
    val response = restTemplate.postForObject("http://localhost:8080/hello", helloRequest, classOf[HelloResponse])
    assertEquals("Hello tabiul", response.getMessage)
  }
}


