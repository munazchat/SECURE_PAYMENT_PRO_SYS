/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Model and Enitity class for Credit cards
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "credit_card")
public class CreditCard {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private CardType cardType;
	private String cardHolder;
	private String ccn;
	private boolean defaultCard;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	public CreditCard() {
	}

	public CreditCard(CardType cardType, String cardHolder, String ccn, boolean defaultCard, Date expirationDate, User user) {
		this.cardType = cardType;
		this.cardHolder = cardHolder;
		this.ccn = ccn;
		this.defaultCard = defaultCard;
		this.expirationDate = expirationDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public String getCcn() {
		return ccn;
	}

	public void setCcn(String ccn) {
		this.ccn = ccn;
	}

	public boolean isDefaultCard() {
		return defaultCard;
	}

	public void setDefaultCard(boolean defaultCard) {
		this.defaultCard = defaultCard;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	@Override
	public String toString() {
		return "CreditCard:\n" +
				"id=" + id +
				"\ncardType=" + cardType +
				"\ncardHolder='" + cardHolder + '\'' +
				"\nccn='" + ccn + '\'' +
				"\ndefaultCard=" + defaultCard +
				"\nexpirationDate=" + expirationDate +
				"\nuser=" + user;
	}
}
