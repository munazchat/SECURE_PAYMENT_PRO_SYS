/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Model and Enitity class for user profiles
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.domain;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String address;
	private String city;
	private String country;
	private boolean defaultBilling = false;
	private boolean defaultShipping = false;
	private boolean currentProfile = true;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	public Profile() {
	}

	public Profile(String address, String city, String country, boolean defaultBilling, boolean defaultShipping, boolean currentProfile) {
		this.address = address;
		this.city = city;
		this.country = country;
		this.defaultBilling = defaultBilling;
		this.defaultShipping = defaultShipping;
		this.currentProfile = currentProfile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isDefaultBilling() {
		return defaultBilling;
	}

	public void setDefaultBilling(boolean defaultBilling) {
		this.defaultBilling = defaultBilling;
	}

	public boolean isDefaultShipping() {
		return defaultShipping;
	}

	public void setDefaultShipping(boolean defaultShipping) {
		this.defaultShipping = defaultShipping;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCurrentProfile() {
		return currentProfile;
	}

	public void setCurrentProfile(boolean currentProfile) {
		this.currentProfile = currentProfile;
	}

	@Override
	public String toString() {
		return "Profile{" +
				"id=" + id +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", defaultBilling=" + defaultBilling +
				", defaultShipping=" + defaultShipping +
				", currentProfile=" + currentProfile +
				", user=" + user +
				'}';
	}
}
