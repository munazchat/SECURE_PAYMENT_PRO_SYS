/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Controller class for credit profile page
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.CreditCard;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.service.CreditCardService;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/credit-profile")
public class CreditProfileController {

	private final CreditCardService creditCardService;
	private final UserService userService;

	public CreditProfileController(CreditCardService creditCardService, UserService userService) {
		this.creditCardService = creditCardService;
		this.userService = userService;
	}

	@GetMapping
	public String creditProfile(Model model) {
		User authUser = userService.getAuthenticatedUser();
		model.addAttribute("cards", creditCardService.findAllByUser(authUser));
		model.addAttribute("defaultCard", creditCardService.getDefaultCard(authUser));

		return "profile/credit/profile";
	}

	@GetMapping("/add")
	public String getAddForm(Model model) {
		CreditCard card = new CreditCard();
		card.setUser(userService.getAuthenticatedUser());
		model.addAttribute("card", card);

		return "profile/credit/profile-form";
	}

	@GetMapping("/update/{id}")
	public String getUpdateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("card", creditCardService.getOne(id));

		return "profile/credit/profile-form";
	}

	@PostMapping("/update")
	public String saveCreditProfile(@ModelAttribute("card") CreditCard card) {
		User authUser = userService.getAuthenticatedUser();
		CreditCard defaultCard = creditCardService.getDefaultCard(authUser);

		if (defaultCard != null && card.isDefaultCard()) {
			defaultCard.setDefaultCard(false);
			creditCardService.save(defaultCard);
		}
		card.setUser(authUser);
		creditCardService.save(card);

		return "redirect:/credit-profile?success";
	}

	@PostMapping("/remove/{id}")
	public String delete(@PathVariable("id") Long id) {
		creditCardService.deleteById(id);

		return "redirect:/credit-profile?deleted";
	}
}
