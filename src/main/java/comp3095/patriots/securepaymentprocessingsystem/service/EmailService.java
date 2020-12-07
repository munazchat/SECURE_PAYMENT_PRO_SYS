/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Service class to allow sending of emails. Sends an email to the
 * user that just registered for the app with their registration information
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendVerificationEmail(User user) {
		sendEmail(user.getEmail(),
				"Secure Payment Processing System Registration",
				String.format("Dear %s %s,\n\nWelcome to our Secure Payment Processing System.\n\n" +
						"You have been registered with the following information:\n" +
						"Firstname: %s\n" +
						"Lastname: %s\n" +
						"Email: %s\n\n" +
						"You can now log in at: http://localhost:8080/login",
						user.getFirstName(), user.getLastName(),
						user.getFirstName(), user.getLastName(), user.getEmail()));
	}
	private void sendEmail(String toEmailAddress, String subject, String text) {
		System.out.println("sending email...");
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("lasseberantzino@gmail.com");
		simpleMailMessage.setTo(toEmailAddress);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);

		javaMailSender.send(simpleMailMessage);
		System.out.println("sent email...");
	}
}
