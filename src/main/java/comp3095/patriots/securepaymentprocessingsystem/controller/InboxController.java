/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Controller class inbox page
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.controller;

import comp3095.patriots.securepaymentprocessingsystem.domain.Message;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.service.MessageService;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inbox")
public class InboxController {

	private final MessageService messageService;
	private final UserService userService;

	public InboxController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}

	@GetMapping
	public String inbox(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.findByEmail(authentication.getName());

		model.addAttribute("messages_received", currentUser.getMessagesReceived());
		model.addAttribute("messages_sent", currentUser.getMessagesSent());
		return "inbox/index";
	}

	@GetMapping("/message/{id}")
	public String message(@PathVariable("id") Long id, Model model) {
		Message message = messageService.getOne(id);
		User currentUser = userService.getAuthenticatedUser();

		if (currentUser.getEmail().equals(message.getReceiver().getEmail())) {
			message.setRead(true);
			messageService.updateMessage(message);
		}

		model.addAttribute("message", message);
		model.addAttribute("authenticatedUser", currentUser);
		return "inbox/message";
	}

	@PostMapping("/remove/{id}")
	public String delete(@PathVariable("id") Long id) {
		messageService.deleteById(id);

		return "redirect:/inbox?deleted";
	}

	@GetMapping("/message/{id}/reply")
	public String getReplyForm(@PathVariable("id") Long id, Model model) {
		Message messageToReply = messageService.getOne(id);

		Message reply = new Message();
		reply.setSubject("Re: " + messageToReply.getSubject());
		reply.setReceiver(messageToReply.getSender());

		model.addAttribute("reply", reply);

		return "inbox/reply";
	}

	@PostMapping("/message/reply")
	public String replyToMessage(@ModelAttribute("reply") Message reply) {
		System.out.println(reply.toString());
		messageService.sendMessage(reply, userService.getAuthenticatedUser());

		return String.format("redirect:/inbox?success");
	}
}
