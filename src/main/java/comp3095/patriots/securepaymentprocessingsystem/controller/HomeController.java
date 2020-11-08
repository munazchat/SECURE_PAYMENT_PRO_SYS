package comp3095.patriots.securepaymentprocessingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String dashboard() {
		return "index";
	}

	@GetMapping("/future")
	public String future() {
		return "future";
	}
}
