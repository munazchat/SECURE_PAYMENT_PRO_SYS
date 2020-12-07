/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Service class that saves a user profile
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.service;

import comp3095.patriots.securepaymentprocessingsystem.domain.Profile;
import comp3095.patriots.securepaymentprocessingsystem.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

	private final ProfileRepository profileRepository;

	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	public Profile save(Profile profile) {
		return profileRepository.save(profile);
	}
}
