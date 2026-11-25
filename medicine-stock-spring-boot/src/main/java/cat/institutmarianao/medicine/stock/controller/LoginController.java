package cat.institutmarianao.medicine.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller responsible for rendering the custom authentication page
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}