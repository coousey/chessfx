package com.starterkit.javafx.dataprovider.impl;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starterkit.javafx.dataprovider.UserDataProvider;
import com.starterkit.javafx.dataprovider.data.UserProfileVO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserDataProviderImpl implements UserDataProvider {

	@Override
	public Collection<UserProfileVO> findUserProfiles(String login, String firstName, String lastName) {
		String output = null;

		// REV: te obiekty powinny byc utworzone tylko raz
		Client client = Client.create();
		// REV: adres powinien byc pobrany z konfiguracji
		WebResource webResource = client.resource("http://localhost:8090/user/search").queryParam("login", login)
				.queryParam("name", firstName).queryParam("surname", lastName);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		// REV: Jersey sprawdza status i rzuca wyjatki
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		output = response.getEntity(String.class);

		// REV: nie ma potrzeby uzywania GSONa, Jersey potrafi mapowac obiekty
		Type token = new TypeToken<Collection<UserProfileVO>>() {
		}.getType();

		return new Gson().fromJson(output, token);
	}

	@Override
	public void saveUserProfile(UserProfileVO user) {

		// REV: j.w.
		Client client = Client.create();
		// REV: j.w.
		WebResource webResource = client.resource("http://localhost:8090/user");

		ClientResponse response = webResource.accept("application/json").type("application/json")
				.put(ClientResponse.class, new Gson().toJson(user, UserProfileVO.class));
		// REV: j.w.
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
	}

	@Override
	public void deleteUserProfile(Long id) {
		
		// REV: j.w.
		Client client = Client.create();
		// REV: j.w.
		WebResource webResource = client.resource("http://localhost:8090/user/" + id);

		ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
		
		// REV: j.w.
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		response.getEntity(String.class);

	}
}
