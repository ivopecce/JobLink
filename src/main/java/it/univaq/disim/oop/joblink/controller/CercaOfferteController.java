/**
 * Controller della vista per la ricerca di offerte
 */
package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.TemporaryObject;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CercaOfferteController implements Initializable, DataInitializable<Persona> {

	@FXML
	private TextField titoloField;
	@FXML
	private Button cercaTitoloButton;
	@FXML
	private TextField localitaField;
	@FXML
	private Button cercaLocalitaButton;
	
	private Persona persona;
	
	private ViewDispatcher dispatcher;
	private OffertaService offertaService;
	
	public CercaOfferteController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService  = factory.getOffertaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cercaTitoloButton.disableProperty().bind(titoloField.textProperty().isEmpty());
		cercaLocalitaButton.disableProperty().bind(localitaField.textProperty().isEmpty());
	}
	
	@Override
	public void initializeData(Persona persona) {
		this.persona = persona;
	}
	
	/*Ricerca delle offerte aventi nel titolo una stringa inserita dall'utente*/
	@FXML
	public void cercaTitoloAction(ActionEvent event) {
		TemporaryObject tmp = new TemporaryObject();
		tmp.setPersona(this.persona);
		tmp.setCercaTitolo(titoloField.getText());
		dispatcher.renderView("risultatiRicercaOfferte", tmp);
	}
	
	/*Ricerca delle offerte aventi nella localita' una stringa inserita dall'utente*/
	@FXML
	public void cercaLocalitaAction(ActionEvent event) {
		TemporaryObject tmp = new TemporaryObject();
		tmp.setPersona(this.persona);
		tmp.setCercaLocalita(localitaField.getText());
		tmp.setCercaTitolo("");
		dispatcher.renderView("risultatiRicercaOfferte", tmp);
	}

}
