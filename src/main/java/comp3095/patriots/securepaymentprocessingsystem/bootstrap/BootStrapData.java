/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 08/11/2020
 * Description: BootStrapData class that makes use of CommandLineRunner interface,
 * to add an admin user to the application
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.bootstrap;

import comp3095.patriots.securepaymentprocessingsystem.domain.*;
import comp3095.patriots.securepaymentprocessingsystem.repository.*;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class BootStrapData implements CommandLineRunner {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final MessageRepository messageRepository;
	private final CreditCardRepository creditCardRepository;
	private final ProfileRepository profileRepository;
	private final UserService userService;
	private final BCryptPasswordEncoder passwordEncoder;


	public BootStrapData(UserRepository userRepository, RoleRepository roleRepository, MessageRepository messageRepository,
	                     CreditCardRepository creditCardRepository, ProfileRepository profileRepository,
	                     UserService userService, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.messageRepository = messageRepository;
		this.creditCardRepository = creditCardRepository;
		this.profileRepository = profileRepository;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

		// Adds ADMIN role and admin users
		Role adminRole = roleRepository.save(new Role("ADMIN"));

		// User(String firstName, String lastName, String email, String password, Date DOB, Profile profile)
		User admin1 = new User(
				"admin", "user", "admin@isp.net", passwordEncoder.encode("P@ssword1"),
				format.parse("12-17-1995"));
		User admin2= new User(
				"admin", "user2", "admin2@isp.net", passwordEncoder.encode("P@ssword1"),
				format.parse("09-11-1965"));

		admin1.getRoles().add(adminRole);
		admin2.getRoles().add(adminRole);

		// Adds CLIENT role and client users
		Role clientRole = roleRepository.save(new Role("CLIENT"));

		User client1 = new User(
				"Lasse", "Berantzino", "lasseken.berantzino@georgebrown.ca",
				passwordEncoder.encode("P@ssword1"), format.parse("12-28-1994"));

		User client2 = new User(
				"test", "user", "testuser@isp.net",
				passwordEncoder.encode("P@ssword1"), format.parse("08-08-1924"));

		client1.getRoles().add(clientRole);
		client2.getRoles().add(clientRole);

		//admin profiles
		Profile admin1Profile = new Profile(
				"Jægerstræde 8", "Karlslunde", "Denmark", false, false, true);
		Profile admin2Profile = new Profile(
				"Rådhuspladsen 10", "Copenhagen", "Denmark", false, false, true);
		admin1Profile.setUser(admin1);
		admin2Profile.setUser(admin2);

		// client profiles
		Profile client1Profile1 = new Profile(
				"Jægerstræde 8", "Karlslunde", "Denmark", true, false, true);
		Profile client1Profile2 = new Profile(
				"Rådhuspladsen 10", "Copenhagen", "Denmark", false, true, false);
		Profile client2Profile1 = new Profile(
				"Rådhuspladsen 10", "Copenhagen", "Denmark", true, true, true);
		client1Profile1.setUser(client1);
		client1Profile2.setUser(client1);
		client2Profile1.setUser(client2);


		// Save all users
		userRepository.saveAll(Arrays.asList(admin1, admin2, client1, client2));

		// Save all profiles
		profileRepository.saveAll(Arrays.asList(admin1Profile, admin2Profile, client1Profile1, client1Profile2, client2Profile1));

		// create and save messages
		Message msg1 = new Message("Subject", "Content", admin2, admin1);
		Message msg2 = new Message("Subject", "Content", admin2, admin1);
		Message msg3 = new Message("Subject", "Content", admin2, admin1);
		Message msg4 = new Message("Subject", "Content", admin2, admin1);
		Message msg5 = new Message("Subject", "Content", admin2, admin1);

		messageRepository.saveAll(Arrays.asList(msg1, msg2, msg3, msg4, msg5));

		// create and save credit cards
		CreditCard c1 = new CreditCard(CardType.MASTER_CARD, client1.getFullName(), "0123456789012345", false, new Date(), client1);
		CreditCard c2 = new CreditCard(CardType.AMERICAN_EXPRESS, client1.getFullName(), "1111222233334444", true, new Date(), client1);
		CreditCard c3 = new CreditCard(CardType.VISA, client1.getFullName(), "5555666677778888", false, new Date(), client1);

		creditCardRepository.saveAll(Arrays.asList(c1, c2, c3));

		System.out.println("Started in bootstrap...");
		System.out.println("Number of Users: " + userRepository.count());
		System.out.println("Number of Roles: " + roleRepository.count());
		System.out.println(roleRepository.findByName("ADMIN").getUsers().size());
		//System.out.println("CreditCard: " + creditCardRepository.findAll().get(0));
	}
}
