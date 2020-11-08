/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino, Munazum Rauf, Vivek Mathimakki
 * Student Number: 101326867, 100956112, 101078278
 * Date: 08/11/2020
 * Description: Controller class that manages endpoint for registration
 * redirects to dashboard if authenticated
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.service.EmailService;
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
	private final EmailService emailService;

	public RegistrationController(UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
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
		emailService.sendVerificationEmail(user);
		return "redirect:/register?success";
	}
}
