package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.ProfiloPersonaService;
import it.univaq.disim.oop.joblink.domain.Formazione;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormazioneController implements Initializable, DataInitializable<Formazione> {
	
	@FXML
	private TextField titoloFormazioneField;
	@FXML
	private TextArea descrizioneFormazioneField;
	@FXML
	private DatePicker dataInizioFormazioneField;
	@FXML
	private DatePicker dataFineFormazioneField;
	@FXML
	private TextField istitutoField;
	@FXML
	private TextField votoField;
	@FXML
	private Button salvaButton;
	
	private ViewDispatcher dispatcher;
	private Formazione formazione;
	private ProfiloPersonaService profiloPersonaService;
	
	public FormazioneController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		profiloPersonaService = factory.getProfiloPersonaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		salvaButton.disableProperty().bind(titoloFormazioneField.textProperty().isEmpty());
	}
	
	@Override
	public void initializeData(Formazione formazione) {
		this.formazione = formazione;
		this.titoloFormazioneField.setText(formazione.getTitolo());
		this.descrizioneFormazioneField.setText(formazione.getDescrizione());
		this.dataInizioFormazioneField.setValue(formazione.getDataInizio());
		this.dataFineFormazioneField.setValue(formazione.getDataFine());
		this.istitutoField.setText(formazione.getIstituto());
		if(formazione.getVoto() != null) this.votoField.setText(formazione.getVoto().toString());
		
	}
	
	@FXML
	public void salvaAction(ActionEvent event) {
		try {
			formazione.setTitolo(titoloFormazioneField.getText());
			formazione.setDescrizione(descrizioneFormazioneField.getText());
			formazione.setDataInizio(dataInizioFormazioneField.getValue());
			formazione.setDataFine(dataFineFormazioneField.getValue());
			formazione.setIstituto(istitutoField.getText());
			formazione.setVoto(Integer.parseInt(votoField.getText()));
			if(formazione.getId() == null) profiloPersonaService.createFormazione(formazione);
			else profiloPersonaService.updateFormazione(formazione);
			dispatcher.renderView("profiloUtente", formazione.getPersona());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.renderView("profiloUtente", formazione.getPersona());
	}
	
	@FXML
	public void eliminaAction(ActionEvent event) {
		if(formazione.getId() != null) {
			try {
				profiloPersonaService.deleteFormazione(formazione);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		dispatcher.renderView("profiloUtente", formazione.getPersona());
	}

}
