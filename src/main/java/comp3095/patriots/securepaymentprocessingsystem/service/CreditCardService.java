package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.CreditCard;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;
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

	public List<CreditCard> findAllByUser(User user) {
		return creditCardRepository.findAllByUser(user);
	}

	public CreditCard getDefaultCard(User user) {
		CreditCard defaultCard = null;

		for (CreditCard c : creditCardRepository.findAllByUser(user)) {
			if (c.isDefaultCard()) {
				defaultCard = c;
			}
		}
		return defaultCard;
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
