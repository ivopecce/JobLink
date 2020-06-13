package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.UtenteNotFoundException;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.business.impl.ram.RAMUtenteServiceImpl;
import it.univaq.disim.oop.joblink.domain.Utente;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable, DataInitializable<Object> {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label loginErrorLabel;
	@FXML
	private Button loginButton;
	
	private UtenteService utenteService;
	
	private ViewDispatcher dispatcher;
	
	public LoginController() {
		dispatcher = ViewDispatcher.getInstance();
		utenteService = new RAMUtenteServiceImpl();
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loginButton.disableProperty().bind(username.textProperty().isEmpty().or(password.textProperty().isEmpty()));
		
		
	}
	
	@FXML
	private void loginAction(ActionEvent event) {
		
		try {
			Utente utente = utenteService.authenticate(username.getText(), password.getText());
			
			dispatcher.loggedIn(utente);
		} catch (UtenteNotFoundException e) {
			loginErrorLabel.setText("Username e/o password errati!");
			e.printStackTrace();
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}

	}

}
