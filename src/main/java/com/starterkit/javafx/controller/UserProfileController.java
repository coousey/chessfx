package com.starterkit.javafx.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import com.starterkit.javafx.dataprovider.UserDataProvider;
import com.starterkit.javafx.dataprovider.data.UserProfileVO;
import com.starterkit.javafx.model.PersonSearch;
import com.starterkit.javafx.model.UserProfile;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserProfileController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private Label userIdLabel;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextArea aboutMeArea;
	@FXML
	private TextArea lifeMottoArea;

	private Long id;

	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	
	@FXML
	private Label infoLabel2;

	private final UserDataProvider userDataProvider = UserDataProvider.INSTANCE;

	private final UserProfile model = new UserProfile();

	public UserProfileController() {
	}

	@FXML
	private void initialize() {

		userIdLabel.textProperty().bind(model.loginProperty());
		firstNameField.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameField.textProperty().bindBidirectional(model.lastNameProperty());
		emailField.textProperty().bindBidirectional(model.emailProperty());
		passwordField.textProperty().bindBidirectional(model.passwordProperty());
		aboutMeArea.textProperty().bindBidirectional(model.aboutMeProperty());
		lifeMottoArea.textProperty().bindBidirectional(model.lifeMottoProperty());
		
		infoLabel2.textProperty().bind(model.infoProperty());
	}

	@FXML
	private void saveButtonAction(ActionEvent event) {
		UserProfileVO user = new UserProfileVO(id, model.getLogin(), model.getPassword(), model.getFirstName(),
				model.getLastName(), model.getEmail(), model.getAboutMe(), model.getLifeMotto());

		Task<Void> backgroundTask = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				userDataProvider.saveUserProfile(user);
				return null;
			}

			@Override
			protected void succeeded() {
				((Stage) cancelButton.getScene().getWindow()).close();
			}

			@Override
			protected void failed() {
				model.setInfo("server not responding");
			}
		};
		new Thread(backgroundTask).start();
	}

	@FXML
	private void cancelButtonAction(ActionEvent event) {
		((Stage) cancelButton.getScene().getWindow()).close();
	}

	public void getUser(UserProfileVO user) {

		id = user.getId();
		model.setLogin(user.getLogin());
		model.setFirstName(user.getName());
		model.setLastName(user.getSurname());
		model.setEmail(user.getEmail());
		model.setPassword(user.getPassword());
		model.setAboutMe(user.getAboutMe());
		model.setLifeMotto(user.getLifeMotto());
	}

}
