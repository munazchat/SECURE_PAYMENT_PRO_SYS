/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Controller class that manages endpoint for registration
 * redirects to dashboard if authenticated
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.Profile;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.dto.UserCreationDto;
import comp3095.patriots.securepaymentprocessingsystem.dto.UserDeletionDto;
import comp3095.patriots.securepaymentprocessingsystem.service.EmailService;
import comp3095.patriots.securepaymentprocessingsystem.service.ProfileService;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	private final UserService userService;
	private final EmailService emailService;
	private final ProfileService profileService;

	public RegistrationController(UserService userService, EmailService emailService, ProfileService profileService) {
		this.userService = userService;
		this.emailService = emailService;
		this.profileService = profileService;
	}

	@GetMapping
	public String getRegistrationForm(Model model) {
		if (userService.getAuthenticatedUser() != null) {
			return "redirect:/";
		}

		UserCreationDto usersForm = new UserCreationDto(new User(), new Profile(), "");
		model.addAttribute("form", usersForm);

		return "registration";
	}

	@PostMapping
	public String registerClient(@ModelAttribute("form") UserCreationDto form) throws ParseException {
		User user = form.getUser();
		Profile profile = form.getProfile();

		user.setDOB(dateFormat.parse(form.getDateString()));
		profile.setUser(form.getUser());

		if (userService.saveClient(user) == null) {
			return "redirect:/register?error";
		}
		profileService.save(profile);
		emailService.sendVerificationEmail(user);
		return "redirect:/register?success";
	}
}
