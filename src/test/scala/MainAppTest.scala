import com.restapp.proto.v1.Hello.{HelloRequestV1, HelloResponseV1}
import com.restapp.proto.v2.Hello.{HelloRequestV2, HelloResponseV2}
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
  def testV1() = {
    val requestBuilder = HelloRequestV1.newBuilder()
    val request = requestBuilder.setUserName("tabiul").build()
    val headers = new HttpHeaders()
    headers.setContentType(ProtobufHttpMessageConverter.PROTOBUF)
    headers.setContentLength(request.toByteArray.length)
    val helloRequest = new HttpEntity[Array[Byte]](requestBuilder.build().toByteArray, headers)
    val response = restTemplate.postForObject("http://localhost:8080/v1/hello", helloRequest, classOf[HelloResponseV1])
    assertEquals("hello tabiul", response.getMessage)
  }

  @Test
  def testV2() = {
    val requestBuilder = HelloRequestV2.newBuilder()
    val request = requestBuilder.setFirstName("Adel").setMiddleName("Eskandar").setLastNameName("Mahmood").build()
    val headers = new HttpHeaders()
    headers.setContentType(ProtobufHttpMessageConverter.PROTOBUF)
    headers.setContentLength(request.toByteArray.length)
    val helloRequest = new HttpEntity[Array[Byte]](requestBuilder.build().toByteArray, headers)
    val response = restTemplate.postForObject("http://localhost:8080/v2/hello", helloRequest, classOf[HelloResponseV2])
    assertEquals("hello Adel Eskandar Mahmood", response.getMessage)
  }
}


