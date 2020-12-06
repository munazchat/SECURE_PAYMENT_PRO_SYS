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

import comp3095.patriots.securepaymentprocessingsystem.domain.Role;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
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
	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("getAuthenticatedUser" + authentication.getName());
		return findByEmail(authentication.getName());
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
