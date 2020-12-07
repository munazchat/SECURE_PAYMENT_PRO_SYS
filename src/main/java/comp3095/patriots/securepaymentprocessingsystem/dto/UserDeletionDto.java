/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Data Transfer Object used for holding the list of Users to delete
 * on users pages for admin roles
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.dto;

import comp3095.patriots.securepaymentprocessingsystem.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDeletionDto {
	private List<User> users;

	public UserDeletionDto() {
		this.users = new ArrayList<>();
	}

	public UserDeletionDto(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
