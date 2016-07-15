package restapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter

/**
  * Created by tabiul on 15 Jul 2016.
  */
@SpringBootApplication
class MainApp {

  @Bean
  def protobufHttpMessageConverter = {
    new ProtobufHttpMessageConverter()
  }

  def main(args: Array[String]) {
    SpringApplication.run(classOf[MainApp])
  }

}
