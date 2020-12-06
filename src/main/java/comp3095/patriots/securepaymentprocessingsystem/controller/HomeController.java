/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino, Munazum Rauf, Vivek Mathimakki
 * Student Number: 101326867, 100956112, 101078278
 * Date: 08/11/2020
 * Description: Controller class that manages endpoints for dashboard and future enhancement endpoints
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.dto.UserDeletionDto;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

		UserDeletionDto usersForm = new UserDeletionDto(userService.findAll());

		model.addAttribute("form", usersForm);

		return "admin/users";
	}

	@PostMapping("/users")
	public String deleteUsers(@ModelAttribute("form") UserDeletionDto form) {

		boolean usersDeleted = userService.deleteUsers(form.getUsers());

		if (!usersDeleted) {
			return "redirect:/users?error";
		}
		return "redirect:/users";
	}
}
