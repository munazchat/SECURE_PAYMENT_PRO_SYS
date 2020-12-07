/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Interface for database access methods. extends JpaRepository and adds
 * methods findByEmail, findAllByRoles, deleteByIdIn
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.repository;

import comp3095.patriots.securepaymentprocessingsystem.domain.Role;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	List<User> findAllByRoles(Role role);
	void deleteByIdIn(List<Long> ids);
}
