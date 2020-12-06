package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.CreditCard;
import comp3095.patriots.securepaymentprocessingsystem.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

	private final CreditCardRepository creditCardRepository;

	public CreditCardService(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}

	public CreditCard getDefaultCard() {
		return creditCardRepository.findByDefaultCardEquals(true);
	}

	public CreditCard getOne(Long id) {
		return creditCardRepository.getOne(id);
	}

	public CreditCard save(CreditCard creditCard) {
		return creditCardRepository.save(creditCard);
	}

	public void deleteById(Long id) {
		creditCardRepository.deleteById(id);
	}
}
