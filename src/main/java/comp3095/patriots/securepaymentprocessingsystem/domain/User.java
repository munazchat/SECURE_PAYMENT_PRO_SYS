/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino, Munazum Rauf
 * Student Number: 101326867, 100956112
 * Date: 06/12/2020
 * Description: Class for the User domain annotated as a db entity
 * contains annotations for primary key and relationship with instructions of
 * join table generation
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date DOB;
	private Date lastLogin;
	private Date lastProfile = new Date();
	private boolean upForDeletion = false;
	private Date registrationDate = new Date();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
	private List<Message> messagesReceived = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
	private List<Message> messagesSent = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<CreditCard> cards = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Profile> profiles = new ArrayList<>();

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, Date DOB) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.DOB = DOB;
	}

	public User(String firstName, String lastName, String email, String password, Date DOB, Profile profile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.DOB = DOB;
		this.profiles.add(profile);
	}

	public Map<String, Profile> getPrefAddresses() {
		Map<String, Profile> addresses = new HashMap<>();

		for (Profile p : profiles) {
			if (p.isDefaultBilling()) {
				addresses.put("prefBillingAddress", p);
			}
			if (p.isDefaultShipping()){
				addresses.put("prefShippingAddress", p);
			}
		}
		return addresses;
	}

	public int getAmountRead() {
		int read = 0;

		for (Message msg : messagesReceived) {
			if (msg.isRead()) {
				read++;
			}
		}
		return read;
	}

	public int getAmountUnread() {
		int unRead = 0;

		for (Message msg : messagesReceived) {
			if (!msg.isRead()) {
				unRead++;
			}
		}
		return unRead;
	}

	public Profile getCurrentProfile() {
		for (Profile p : profiles) {
			if (p.isCurrentProfile()) {
				return p;
			}
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date DOB) {
		this.DOB = DOB;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastProfile() {
		return lastProfile;
	}

	public void setLastProfile(Date lastProfile) {
		this.lastProfile = lastProfile;
	}

	public boolean isUpForDeletion() {
		return upForDeletion;
	}

	public void setUpForDeletion(boolean upForDeletion) {
		this.upForDeletion = upForDeletion;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public List<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	public List<CreditCard> getCards() {
		return cards;
	}

	public void setCards(List<CreditCard> cards) {
		this.cards = cards;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", DOB=" + DOB +
				", lastLogin=" + lastLogin +
				", lastProfile=" + lastProfile +
				", upForDeletion=" + upForDeletion +
				", registrationDate=" + registrationDate +
				'}';
	}
}
