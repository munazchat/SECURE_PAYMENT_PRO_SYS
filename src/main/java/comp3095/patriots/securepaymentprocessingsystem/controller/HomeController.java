/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino, Munazum Rauf, Vivek Mathimakki
 * Student Number: 101326867, 100956112, 101078278
 * Date: 08/11/2020
 * Description: Controller class that manages endpoints for dashboard and future enhancement endpoints
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private final UserService userService;

	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String dashboard() {
		return "index";
	}

	@GetMapping("/future")
	public String future() {
		return "future";
	}

	@GetMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users", userService.findAllClients());

		return "admin/users";
	}
}
