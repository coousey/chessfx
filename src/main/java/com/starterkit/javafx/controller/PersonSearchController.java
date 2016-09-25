package com.starterkit.javafx.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;


import com.starterkit.javafx.dataprovider.UserDataProvider;
import com.starterkit.javafx.dataprovider.data.UserProfileVO;
import com.starterkit.javafx.model.PersonSearch;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PersonSearchController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private TextField userIdField;
	@FXML
	private TextField firstNameField2;
	@FXML
	private TextField lastNameField2;

	@FXML
	private TableView<UserProfileVO> resultTable;
	@FXML
	private TableColumn<UserProfileVO, String> userIdColumn;
	@FXML
	private TableColumn<UserProfileVO, String> firstNameColumn;
	@FXML
	private TableColumn<UserProfileVO, String> lastNameColumn;
	@FXML
	private TableColumn<UserProfileVO, String> emailColumn;

	@FXML
	private Button searchButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button editButton;
	
	@FXML
	private Label infoLabel;

	private final UserDataProvider userDataProvider = UserDataProvider.INSTANCE;

	private final PersonSearch model = new PersonSearch();

	public PersonSearchController() {
	}

	@FXML
	private void initialize() {

		userIdField.textProperty().bindBidirectional(model.loginProperty());
		firstNameField2.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameField2.textProperty().bindBidirectional(model.lastNameProperty());
		infoLabel.textProperty().bind(model.infoProperty());

		userIdColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLogin()));
		firstNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
		lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSurname()));
		emailColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));

		resultTable.setPlaceholder(new Label(resources.getString("table.emptyText")));

		resultTable.itemsProperty().bind(model.resultProperty());
	}

	@FXML
	private void searchButtonAction(ActionEvent event) {
		updateList();
	}

	@FXML
	private void deleteButtonAction(ActionEvent event) {
		if (resultTable.getSelectionModel().selectedItemProperty().getValue() != null) {
			
			Task<Void> backgroundTask = new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					userDataProvider.deleteUserProfile(resultTable.getSelectionModel().selectedItemProperty().getValue().getId());
					return null;
				}

				@Override
				protected void succeeded() {
					model.setInfo("deleted");
					updateList();
				}

				@Override
				protected void failed() {
					model.setInfo("server not responding");
				}
			};
			new Thread(backgroundTask).start();	
		}
	}

	@FXML
	private void editButtonAction(ActionEvent event) {
		if (resultTable.getSelectionModel().selectedItemProperty().getValue() == null) {
			return;
		}
		try {
			Stage stage = new Stage();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/starterkit/javafx/view/user-profile.fxml"),
					ResourceBundle.getBundle("com/starterkit/javafx/bundle/base"));

			Pane root = loader.load();

			UserProfileController userProfileController = loader.<UserProfileController> getController();

			userProfileController.getUser(resultTable.getSelectionModel().selectedItemProperty().getValue());

			stage.setScene(new Scene(root));

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node) event.getSource()).getScene().getWindow());

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateList() {
		Task<Collection<UserProfileVO>> backgroundTask = new Task<Collection<UserProfileVO>>() {

			@Override
			protected Collection<UserProfileVO> call() throws Exception {
				String login = model.getLogin() != null ? model.getLogin() : "";
				String firstName = model.getFirstName() != null ? model.getFirstName() : "";
				String lastName = model.getLastName() != null ? model.getLastName() : "";
				Collection<UserProfileVO> result = userDataProvider.findUserProfiles(login, firstName, lastName);
				return result;
			}

			@Override
			protected void succeeded() {
				Collection<UserProfileVO> result = getValue();
				model.setResult(new ArrayList<UserProfileVO>(result));
				resultTable.getSortOrder().clear();
				
				model.setInfo(model.getResult().size() + " results");
			}

			@Override
			protected void failed() {
				model.setInfo("server not responding");
			}
		};
		new Thread(backgroundTask).start();
	}

}
