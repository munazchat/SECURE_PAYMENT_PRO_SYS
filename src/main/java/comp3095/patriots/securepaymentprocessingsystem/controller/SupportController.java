package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.Message;
import comp3095.patriots.securepaymentprocessingsystem.service.MessageService;
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

	public SupportController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping()
	public String support(Model model) {
		model.addAttribute("ticket", new Message());

		return "support/index";
	}

	@PostMapping()
	public String submitTicket(@ModelAttribute("ticket") Message ticket) {
		messageService.createSupportTicket(ticket);

		return "redirect:/support?success";
	}
}
