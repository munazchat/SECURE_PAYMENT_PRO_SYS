/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Service class that implements CRUD for messages
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.Message;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	private final MessageRepository messageRepository;

	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
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

	public void sendMessage(Message message, User sender) {
		message.setSender(sender);
		messageRepository.save(message);
	}

	public void createSupportTicket(Message ticket, User sender, User randomAdmin) {
		ticket.setReceiver(randomAdmin);

		sendMessage(ticket, sender);
	}
}
