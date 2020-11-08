package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import comp3095.patriots.securepaymentprocessingsystem.repository.RoleRepository;
import comp3095.patriots.securepaymentprocessingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User saveClient(User user) {
		user.getRoles().add(roleRepository.findByName("CLIENT"));

		User returnedUserFromDb = userRepository.findByEmail(user.getEmail());

		if (returnedUserFromDb != null) {
			System.out.println("returnedUserFromDb != null");
			System.out.println(returnedUserFromDb.getEmail());
			return null;
		}
		return userRepository.save(user);
	}
}
