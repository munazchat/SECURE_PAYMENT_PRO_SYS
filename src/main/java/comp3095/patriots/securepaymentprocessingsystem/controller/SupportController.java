package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.Message;
import comp3095.patriots.securepaymentprocessingsystem.service.MessageService;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support")
public class SupportController {

	private final MessageService messageService;
	private final UserService userService;

	public SupportController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}

	@GetMapping()
	public String support(Model model) {
		model.addAttribute("ticket", new Message());

		return "support/index";
	}

	@PostMapping()
	public String submitTicket(@ModelAttribute("ticket") Message ticket) {
		messageService.createSupportTicket(ticket, userService.getAuthenticatedUser(), userService.getRandomAdmin());

		return "redirect:/support?success";
	}
}
