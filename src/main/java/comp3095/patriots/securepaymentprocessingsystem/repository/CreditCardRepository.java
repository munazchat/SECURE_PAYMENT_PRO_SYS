/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Interface for database access methods. extends JpaRepository and adds
 * a findAllByUser method to find a users credit cards
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.repository;

import comp3095.patriots.securepaymentprocessingsystem.domain.CreditCard;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	List<CreditCard> findAllByUser(User user);
}