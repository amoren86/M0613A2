package cat.institutmarianao.medicine.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// @Controller indicates that this class handles HTTP requests in Spring MVC
@Controller
// @RequestMapping sets the base URI for all methods in this controller
@RequestMapping
public class HomeController {

	// @GetMapping maps HTTP GET requests on the root path "/" to this method
	@GetMapping
	public ModelAndView handleWelcomeRequest() {
		// "welcome" refers to the Thymeleaf view template at templates/welcome.html
		ModelAndView modelview = new ModelAndView("welcome");
		modelview.getModelMap().addAttribute("welcomeMsg", "Welcome to Medicine Stock!!");
		modelview.getModelMap().addAttribute("tagline", "A Spring Boot Application");
		return modelview;
	}
}