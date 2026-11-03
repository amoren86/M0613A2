package cat.institutmarianao.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication enables auto-configuration, component scan, and configuration properties
@SpringBootApplication
public class HelloWorldSpringBootApplication {

	public static void main(String[] args) {
		// Starts the embedded Tomcat server and initializes the Spring
		// ApplicationContext
		SpringApplication.run(HelloWorldSpringBootApplication.class, args);
	}

}
