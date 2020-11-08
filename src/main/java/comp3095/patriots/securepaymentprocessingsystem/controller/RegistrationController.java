package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private final UserService userService;

	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String getRegistrationForm(Model model) {
		if (userService.isAuthenticated()) {
			return "redirect:/";
		}
		model.addAttribute("user", new User());

		return "registration";
	}

	@PostMapping
	public String registerClient(@ModelAttribute("user") User user) {
		if (userService.saveClient(user) == null) {
			return "redirect:/register?error";
		}
		return "redirect:/register?success";
	}
}
