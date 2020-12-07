package comp3095.patriots.securepaymentprocessingsystem.repository;

import comp3095.patriots.securepaymentprocessingsystem.domain.CreditCard;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	//CreditCard findByDefaultCardEqualsAndUser(boolean isDefault, User user);
	List<CreditCard> findAllByUser(User user);
}