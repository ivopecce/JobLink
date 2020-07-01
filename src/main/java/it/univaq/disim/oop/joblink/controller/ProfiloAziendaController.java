package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProfiloAziendaController implements Initializable, DataInitializable<Azienda> {

	@FXML
	private TextField denominazioneField;
	@FXML
	private TextField sedeField;
	@FXML
	private TextField dipendentiField;
	@FXML
	private TextField settoreField;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField telefonoField;
	@FXML
	private TextField sitoWebField;
	@FXML
	private Button salvaButton;
	
	private ViewDispatcher dispatcher;
	private UtenteService utenteService;
	private Azienda azienda;
	
	public ProfiloAziendaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		salvaButton.disableProperty().bind(denominazioneField.textProperty().isEmpty());
		usernameField.setEditable(false);
	}
	
	@Override
	public void initializeData(Azienda azienda) {
		this.azienda = azienda;
		this.denominazioneField.setText(azienda.getDenominazione());
		this.sedeField.setText(azienda.getSede());
		this.dipendentiField.setText(azienda.getNumeroDipendenti().toString());
		this.settoreField.setText(azienda.getSettore());
		this.usernameField.setText(azienda.getUsername());
		this.emailField.setText(azienda.getEmail());
		this.telefonoField.setText(azienda.getTelefono());
		this.sitoWebField.setText(azienda.getSitoWeb());
	}
	
	@FXML
	public void salvaAction(ActionEvent event) {
		try {
			azienda.setDenominazione(denominazioneField.getText());
			azienda.setSede(sedeField.getText());
			azienda.setNumeroDipendenti(Integer.parseInt(dipendentiField.getText()));
			azienda.setSettore(settoreField.getText());
			azienda.setEmail(emailField.getText());
			azienda.setTelefono(telefonoField.getText());
			azienda.setSitoWeb(sitoWebField.getText());
			utenteService.updateAzienda(azienda);
			dispatcher.loggedIn(azienda);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void eliminaUtenteAction(ActionEvent event) {
		try {
			utenteService.deleteAccount(azienda);
			dispatcher.logout();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.loggedIn(azienda);
	}

}
