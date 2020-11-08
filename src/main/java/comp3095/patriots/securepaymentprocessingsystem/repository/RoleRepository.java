/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino, Munazum Rauf, Vivek Mathimakki
 * Student Number: 101326867, 100956112, 101078278
 * Date: 08/11/2020
 * Description: Interface for database access methods. extends JpaRepository and adds
 * a findByName method to find a role by it's name
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.repository;

import comp3095.patriots.securepaymentprocessingsystem.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
