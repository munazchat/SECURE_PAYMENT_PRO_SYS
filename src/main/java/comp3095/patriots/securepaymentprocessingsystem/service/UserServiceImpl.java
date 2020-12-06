/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino, Munazum Rauf, Vivek Mathimakki
 * Student Number: 101326867, 100956112, 101078278
 * Date: 08/11/2020
 * Description: Implementation class for UserService. Handles the saving of clients,
 * authentication check and method for Spring Security to load a user
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.Message;
import comp3095.patriots.securepaymentprocessingsystem.domain.Role;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.repository.MessageRepository;
import comp3095.patriots.securepaymentprocessingsystem.repository.RoleRepository;
import comp3095.patriots.securepaymentprocessingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final MessageRepository messageRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, MessageRepository messageRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.messageRepository = messageRepository;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAllClients() {
		return userRepository.findAllByRoles(roleRepository.findByName("CLIENT"));
	}

	@Override
	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return findByEmail(authentication.getName());
	}

	@Transactional
	@Override
	public boolean deleteUsers(List<User> users) {
		List<User> usersToDelete = new ArrayList<>();

		for (User user : users) {
			if (user.isUpForDeletion()) {
				if (user.getId().equals(getAuthenticatedUser().getId())) {
					return false;
				}
				usersToDelete.add(userRepository.getOne(user.getId()));
			}
		}
		if (!usersToDelete.isEmpty()) {
			userRepository.deleteByIdIn(
					usersToDelete.stream().map(User::getId).collect(Collectors.toList())
			);
		}
		for (User user : usersToDelete) {
			if (user.getRoles().contains(roleRepository.findByName("ADMIN"))) {
				transferMessagesToNewAdmin(user);
			}
		}
		return true;
	}

	@Override
	public void transferMessagesToNewAdmin(User admin) {

		User randomAdminAssigned = getRandomAdmin();
		for (Message msg : admin.getMessagesSent()) {
			msg.setSender(randomAdminAssigned);
			msg.setId(null);
			messageRepository.save(msg);
		}
		for (Message msg : admin.getMessagesReceived()) {
			msg.setId(null);
			if (!msg.isRead()) {
				msg.setReceiver(randomAdminAssigned);
				messageRepository.save(msg);
			}
		}
	}

	// TODO: OPTIMIZE THIS IF POSSIBLE
	public User getRandomAdmin() {
		List<User> adminList = new ArrayList<>();

		for (User user : findAll()) {
			for (Role role : user.getRoles()) {
				if (role.getName().equals("ADMIN")) {
					adminList.add(user);
				}
			}
		}
		System.out.println("adminList size: " + adminList.size());

		return adminList.get(ThreadLocalRandom.current().nextInt(0, adminList.size()));
	}

	@Override
	public User saveClient(User user) {
		user.setEmail(user.getEmail().toLowerCase());
		User returnedUserFromDb = userRepository.findByEmail(user.getEmail());

		if (returnedUserFromDb != null) {
			System.out.println("returnedUserFromDb != null");
			System.out.println(returnedUserFromDb.getEmail());
			return null;
		}

		user.getRoles().add(roleRepository.findByName("CLIENT"));
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			return false;
		}
		return auth.isAuthenticated();
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		System.out.println("loadUderByUsername called");
		System.out.println("param username: " + s);
		User user = userRepository.findByEmail(s);


		if (user == null) {
			throw new UsernameNotFoundException("Invalid username and/or password");
		}
		System.out.println("findByEmail username: " + user.getEmail());
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				mapRolesToAuthorities(user.getRoles())
		);
	}

	private Set<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
	}
}
