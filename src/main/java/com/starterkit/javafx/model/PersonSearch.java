package com.starterkit.javafx.model;

import java.util.ArrayList;
import java.util.List;

import com.starterkit.javafx.dataprovider.data.UserProfileVO;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class PersonSearch {

	private final StringProperty login = new SimpleStringProperty();
	private final StringProperty firstName = new SimpleStringProperty();
	private final StringProperty lastName = new SimpleStringProperty();
	private final StringProperty info = new SimpleStringProperty();
	
	private final ListProperty<UserProfileVO> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));


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
	
	
	public final List<UserProfileVO> getResult() {
		return result.get();
	}

	public final void setResult(List<UserProfileVO> value) {
		result.setAll(value);
	}

	public ListProperty<UserProfileVO> resultProperty() {
		return result;
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
