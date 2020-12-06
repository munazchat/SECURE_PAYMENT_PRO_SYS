/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino, Munazum Rauf, Vivek Mathimakki
 * Student Number: 101326867, 100956112, 101078278
 * Date: 08/11/2020
 * Description: UserService interface that lists two methods to be implemented.
 * extends UserDetailsService to Allow Spring Security login authentication
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
	User saveClient(User user);
	boolean isAuthenticated();
	User findByEmail(String email);
	List<User> findAll();
	List<User> findAllClients();
	User getAuthenticatedUser();
	boolean deleteUsers(List<User> users);
	void transferMessagesToNewAdmin(User admin);
	User getRandomAdmin();
}
