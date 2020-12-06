package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.Message;
import comp3095.patriots.securepaymentprocessingsystem.domain.Role;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MessageService {

	private final MessageRepository messageRepository;
	private final UserService userService;

	public MessageService(MessageRepository messageRepository, UserService userService) {
		this.messageRepository = messageRepository;
		this.userService = userService;
	}

	public void updateMessage(Message message) {
		messageRepository.save(message);
	}

	public Message getOne(Long id) {
		return messageRepository.getOne(id);
	}

	public void deleteById(Long id) {
		messageRepository.deleteById(id);
	}

	public void sendMessage(Message message) {
		message.setSender(userService.getAuthenticatedUser());
		messageRepository.save(message);
	}

	// TODO: OPTIMIZE THIS IF POSSIBLE
	public void createSupportTicket(Message ticket) {
		ticket.setReceiver(getRandomAdmin());

		sendMessage(ticket);
	}


	private User getRandomAdmin() {
		List<User> adminList = new ArrayList<>();

		for (User user : userService.findAll()) {
			for (Role role : user.getRoles()) {
				if (role.getName().equals("ADMIN")) {
					adminList.add(user);
				}
			}
		}
		System.out.println("adminList size: " + adminList.size());

		return adminList.get(ThreadLocalRandom.current().nextInt(0, adminList.size()));
	}
}
