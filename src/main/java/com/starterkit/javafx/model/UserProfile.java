package com.starterkit.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.starterkit.javafx.dataprovider.data.UserProfileVO;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class UserProfile {

	private final StringProperty login = new SimpleStringProperty();
	private final StringProperty firstName = new SimpleStringProperty();
	private final StringProperty lastName = new SimpleStringProperty();
	private final StringProperty email = new SimpleStringProperty();
	private final StringProperty password = new SimpleStringProperty();
	private final StringProperty aboutMe = new SimpleStringProperty();
	private final StringProperty lifeMotto = new SimpleStringProperty();
	
	private final StringProperty info = new SimpleStringProperty();
	
	public final String getLogin() {
		return login.get();
	}
	public final void setLogin(String value) {
		login.set(value);
	}
	public StringProperty loginProperty() {
		return login;
	}
	
	public final String getFirstName() {
		return firstName.get();
	}
	public final void setFirstName(String value) {
		firstName.set(value);
	}
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	public final String getLastName() {
		return lastName.get();
	}
	public final void setLastName(String value) {
		lastName.set(value);
	}
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	public final String getEmail() {
		return email.get();
	}
	public final void setEmail(String value) {
		email.set(value);
	}
	public StringProperty emailProperty() {
		return email;
	}
	
	public final String getPassword() {
		return password.get();
	}
	public final void setPassword(String value) {
		password.set(value);
	}
	public StringProperty passwordProperty() {
		return password;
	}
	
	public final String getAboutMe() {
		return aboutMe.get();
	}
	public final void setAboutMe(String value) {
		aboutMe.set(value);
	}
	public StringProperty aboutMeProperty() {
		return aboutMe;
	}
	
	public final String getLifeMotto() {
		return lifeMotto.get();
	}
	public final void setLifeMotto(String value) {
		lifeMotto.set(value);
	}
	public StringProperty lifeMottoProperty() {
		return lifeMotto;
	}
	
	public final String getInfo() {
		return info.get();
	}
	public final void setInfo(String value) {
		info.set(value);
	}
	public StringProperty infoProperty() {
		return info;
	}
}
