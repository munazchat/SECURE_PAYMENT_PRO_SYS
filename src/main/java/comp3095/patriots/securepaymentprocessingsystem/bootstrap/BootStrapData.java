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

import comp3095.patriots.securepaymentprocessingsystem.domain.Role;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.repository.RoleRepository;
import comp3095.patriots.securepaymentprocessingsystem.repository.UserRepository;
import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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

		roleRepository.save(new Role("CLIENT"));

		User admin = new User(
				"admin", "user", "N/A",
				"admin@isp.net", passwordEncoder.encode("P@ssword1")
				//"lasseken.berantzino@georgebrown.ca"
		);
		admin.getRoles().add(new Role("ADMIN"));
		userRepository.save(admin);

		//userService.saveAdmin(admin);

		System.out.println("Started in bootstrap...");
		System.out.println("Number of Users: " + userRepository.count());
		System.out.println("Number of Roles: " + roleRepository.count());
	}
}
