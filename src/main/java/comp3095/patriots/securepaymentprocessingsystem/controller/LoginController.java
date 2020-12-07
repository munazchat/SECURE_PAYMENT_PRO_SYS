/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino, Munazum Rauf
 * Student Number: 101326867, 100956112
 * Date: 06/12/2020
 * Description: Controller class that manages endpoint login at /login.
 * redirects a user to the dashboard if they're authenticated
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		if (userService.getAuthenticatedUser() != null) {
			return "redirect:/";
		}
		return "login";
	}
}
