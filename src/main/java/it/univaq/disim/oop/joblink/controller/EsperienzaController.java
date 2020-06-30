package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.ProfiloPersonaService;
import it.univaq.disim.oop.joblink.domain.Esperienza;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EsperienzaController implements Initializable, DataInitializable<Esperienza> {

	@FXML
	private TextField titoloEsperienzaField;
	@FXML
	private TextArea descrizioneEsperienzaField;
	@FXML
	private DatePicker dataInizioEsperienzaField;
	@FXML
	private DatePicker dataFineEsperienzaField;
	@FXML
	private TextField aziendaField;
	@FXML
	private TextField localitaField;
	@FXML
	private Button salvaButton;
	
	private ViewDispatcher dispatcher;
	private ProfiloPersonaService profiloPersonaService;
	private Esperienza esperienza;
	
	public EsperienzaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		profiloPersonaService = factory.getProfiloPersonaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		salvaButton.disableProperty().bind(titoloEsperienzaField.textProperty().isEmpty());

	}
	
	@Override
	public void initializeData(Esperienza esperienza) {
		this.esperienza = esperienza;
		this.titoloEsperienzaField.setText(esperienza.getTitolo());
		this.descrizioneEsperienzaField.setText(esperienza.getDescrizione());
		this.dataInizioEsperienzaField.setValue(esperienza.getDataInizio());
		this.dataFineEsperienzaField.setValue(esperienza.getDataFine());
		this.aziendaField.setText(esperienza.getAzienda());
		this.localitaField.setText(esperienza.getLocalita());
	}
	
	@FXML
	public void salvaAction(ActionEvent event) {
		try {
			esperienza.setTitolo(titoloEsperienzaField.getText());
			esperienza.setDescrizione(descrizioneEsperienzaField.getText());
			esperienza.setDataInizio(dataInizioEsperienzaField.getValue());
			esperienza.setDataFine(dataFineEsperienzaField.getValue());
			esperienza.setAzienda(aziendaField.getText());
			esperienza.setLocalita(localitaField.getText());
			if(esperienza.getId() == null) profiloPersonaService.createEsperienza(esperienza);
			else profiloPersonaService.updateEsperienza(esperienza);
			dispatcher.renderView("profiloUtente", esperienza.getPersona());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.renderView("profiloUtente", esperienza.getPersona());
	}
	
	@FXML
	public void eliminaAction(ActionEvent event) {
		if(esperienza.getId() != null) {
			try {
				profiloPersonaService.deleteEsperienza(esperienza);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		dispatcher.renderView("profiloUtente", esperienza.getPersona());
	}

}
