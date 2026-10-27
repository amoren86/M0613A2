package cat.institutmarianao.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// @Controller annotation indicates to Spring MVC that this component is a controller
@Controller
// @RequestMapping annotation indicates to Spring MVC that this controller will handle
// requests starting with the root path
@RequestMapping
public class HelloController {

	// @GetMapping annotation indicates to Spring MVC that this method will handle
	// GET requests to the root path
	@GetMapping
	public ModelAndView handleRequest() {
		ModelAndView modelview = new ModelAndView("response");
		modelview.getModelMap().addAttribute("greetings", "Hello everybody!!");
		return modelview;
	}

	// @GetMapping annotation indicates to Spring MVC that this method will handle
	// GET requests to the /good-morning path
	@GetMapping(value = "/good-morning")
	public ModelAndView handleGoodMorningRequest() {
		ModelAndView modelview = new ModelAndView("response");
		modelview.getModelMap().addAttribute("greetings", "Good morning everyone!");
		return modelview;
	}

	// @GetMapping annotation indicates to Spring MVC that this method will handle
	// GET requests to the /good-afternoon path
	@GetMapping(value = "/good-afternoon")
	public ModelAndView handleGoodAfternoonRequest() {
		ModelAndView modelview = new ModelAndView("response");
		modelview.getModelMap().addAttribute("greetings", "Good afternoon everyone!");
		return modelview;
	}
}
