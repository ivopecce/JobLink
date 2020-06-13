package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label loginErrorLabel;
	@FXML
	private Button loginButton;
	
	private ViewDispatcher dispatcher;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loginButton.disableProperty().bind(username.textProperty().isEmpty().or(password.textProperty().isEmpty()));
		
		
	}
	
	@FXML
	private void loginAction(ActionEvent event) {
		if (!("ivo".equals(username.getText()) && ("ivo".equals(password.getText())))) {
			loginErrorLabel.setText("Username e/o password errati!");
		} else {
			dispatcher.loggedIn();
		}
	}

}
