package com.starterkit.javafx.dataprovider;

import java.util.Collection;

import com.starterkit.javafx.dataprovider.data.UserProfileVO;
import com.starterkit.javafx.dataprovider.impl.UserDataProviderImpl;

public interface UserDataProvider {

	UserDataProvider INSTANCE = new UserDataProviderImpl();
	
	Collection<UserProfileVO> findUserProfiles(String login, String firstName, String LastName);
	void saveUserProfile(UserProfileVO user);
	void deleteUserProfile(Long id);
}
