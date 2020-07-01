/**
 * Controller della vista contenente il form di registrazione per le aziende
 */
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistraPersonaController implements Initializable, DataInitializable<Object> {

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField ripetiPasswordField;
	@FXML
	private TextField nomeField;
	@FXML
	private TextField cognomeField;
	@FXML
	private TextField telefonoField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField residenzaField;
	@FXML
	private DatePicker dataDiNascitaField;
	@FXML
	private ComboBox<String> genereComboBox;
	@FXML
	private Button registraButton;
	@FXML
	private Button annullaButton;
	@FXML
	private Label registraErrorLabel;
	
	private UtenteService utenteService;
	
	private ViewDispatcher dispatcher;
	
	public RegistraPersonaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registraButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()).or(ripetiPasswordField.textProperty().isEmpty().or(cognomeField.textProperty().isEmpty())));
		genereComboBox.getItems().addAll("Maschio", "Femmina", "Altro");
	}
	
	@FXML
	public void registraAction(ActionEvent event) {
		if(passwordField.getText().equals(ripetiPasswordField.getText())) {
			try {
				utenteService.registerPersona(usernameField.getText(), passwordField.getText(), emailField.getText(), telefonoField.getText(), cognomeField.getText(), nomeField.getText(), dataDiNascitaField.getValue(), genereComboBox.getValue(), residenzaField.getText());
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
