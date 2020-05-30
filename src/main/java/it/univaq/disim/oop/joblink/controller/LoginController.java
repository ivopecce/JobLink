package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	@FXML
	private void loginAction(ActionEvent event) {
		
	}

}
