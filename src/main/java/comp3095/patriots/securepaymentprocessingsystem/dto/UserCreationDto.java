package comp3095.patriots.securepaymentprocessingsystem.dto;

import comp3095.patriots.securepaymentprocessingsystem.domain.Profile;
import comp3095.patriots.securepaymentprocessingsystem.domain.User;

public class UserCreationDto {
	private User user;
	private Profile profile;
	private String dateString;


	public UserCreationDto() {
	}

	public UserCreationDto(User user, Profile profile, String dateString) {
		this.user = user;
		this.profile = profile;
		this.dateString = dateString;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
}



