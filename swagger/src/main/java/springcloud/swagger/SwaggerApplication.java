package springcloud.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SwaggerApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

}
