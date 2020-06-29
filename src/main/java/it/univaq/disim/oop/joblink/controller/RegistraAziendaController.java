package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistraAziendaController implements Initializable, DataInitializable<Object> {
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField ripetiPasswordField;
	@FXML
	private TextField denominazioneField;
	@FXML
	private TextField settoreField;
	@FXML
	private TextField telefonoField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField sedeLegaleField;
	@FXML
	private TextField sitoWebField;
	@FXML
	private TextField numeroDipendentiField;
	@FXML
	private Button registraButton;
	@FXML
	private Button annullaButton;
	@FXML
	private Label registraErrorLabel;
	
	private UtenteService utenteService;
	
	private ViewDispatcher dispatcher;

	public RegistraAziendaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registraButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()).or(ripetiPasswordField.textProperty().isEmpty().or(denominazioneField.textProperty().isEmpty())));

	}
	
	@FXML
	public void registraAction(ActionEvent event) {
		if(passwordField.getText().equals(ripetiPasswordField.getText())) {
			try {
				utenteService.registerAzienda(usernameField.getText(), passwordField.getText(), emailField.getText(), telefonoField.getText(), denominazioneField.getText(), sedeLegaleField.getText(), settoreField.getText(), sitoWebField.getText(), Integer.parseInt(numeroDipendentiField.getText()));
				
				dispatcher.logout();
			} catch (SQLException e) {
				registraErrorLabel.setText("Username gia` presente!");
				e.printStackTrace();
			} catch (BusinessException e) {
				dispatcher.renderError(e);
			}
		}
		else {
			registraErrorLabel.setText("Le password non coincidono!");
		}
		
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.logout();
	}

}
