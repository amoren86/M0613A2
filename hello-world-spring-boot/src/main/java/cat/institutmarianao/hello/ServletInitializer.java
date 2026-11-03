package cat.institutmarianao.hello;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//SpringBootServletInitializer binds the application to an external Servlet container
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// Delegates configuration to the main Spring Boot application class
		return application.sources(HelloWorldSpringBootApplication.class);
	}

}
