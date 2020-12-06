package comp3095.patriots.securepaymentprocessingsystem.repository;

import comp3095.patriots.securepaymentprocessingsystem.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	CreditCard findByDefaultCardEquals(boolean isDefault);
}