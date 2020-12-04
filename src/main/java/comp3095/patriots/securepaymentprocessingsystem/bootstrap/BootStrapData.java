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

import comp3095.patriots.securepaymentprocessingsystem.domain.Message;
import comp3095.patriots.securepaymentprocessingsystem.domain.Role;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.repository.MessageRepository;
import comp3095.patriots.securepaymentprocessingsystem.repository.RoleRepository;
import comp3095.patriots.securepaymentprocessingsystem.repository.UserRepository;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class BootStrapData implements CommandLineRunner {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserService userService;
	private final BCryptPasswordEncoder passwordEncoder;


	public BootStrapData(UserRepository userRepository, RoleRepository roleRepository, UserService userService, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {

		// Adds ADMIN role and admin users
		Role adminRole = roleRepository.save(new Role("ADMIN"));

		User admin1 = new User(
				"admin", "user", "N/A",
				"admin@isp.net", passwordEncoder.encode("P@ssword1")
				//""
		);
		User admin2 = new User(
				"admin2", "user", "N/A",
				"admin2@isp.net", passwordEncoder.encode("P@ssword1")
		);

		admin1.getRoles().add(adminRole);
		admin2.getRoles().add(adminRole);

		// Adds CLIENT role and client users
		Role clientRole = roleRepository.save(new Role("CLIENT"));

		User client1 = new User(
				"Lasse", "Berantzino", "Jægerstræde 8, 2690 Karlslunde, Denmark",
				"lasseken.berantzino@georgebrown.ca", passwordEncoder.encode("P@ssword1")
		);
		User client2 = new User(
				"test", "user", "N/A",
				"testuser@isp.net", passwordEncoder.encode("P@ssword1")
		);

		client1.getRoles().add(clientRole);
		client2.getRoles().add(clientRole);

		// Save all users
		userRepository.saveAll(Arrays.asList(admin1, admin2, client1, client2));


		System.out.println("Started in bootstrap...");
		System.out.println("Number of Users: " + userRepository.count());
		System.out.println("Number of Roles: " + roleRepository.count());
		System.out.println(roleRepository.findByName("ADMIN").getUsers().size());
	}
}
