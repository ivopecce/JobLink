package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.StatoOfferta;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OffertaController implements Initializable, DataInitializable<Offerta> {
	
	@FXML
	private TextArea testo;
	@FXML
	private TextField titolo;
	@FXML
	private TextField localita;
	@FXML
	private Button salvaButton;
	@FXML
	private Button annullaButton;
	@FXML
	private ComboBox<StatoOfferta> statoOfferta;
	@FXML
	private Label dataInserimento;
	
	private ViewDispatcher dispatcher;
	private OffertaService offertaService;
	private Offerta offerta;
	
	public OffertaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService = factory.getOffertaService();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		statoOfferta.getItems().addAll(StatoOfferta.values());
	}
	
	@Override
	public void initializeData(Offerta offerta) {
		this.offerta = offerta;
		this.testo.setText(offerta.getTestoOfferta());
		this.titolo.setText(offerta.getTitoloOfferta());
		this.localita.setText(offerta.getLocalita());
		this.dataInserimento.setText(offerta.getDataCreazione().toString());
		this.statoOfferta.setValue(offerta.getStato());
		salvaButton.disableProperty().bind(testo.textProperty().isEmpty());
	}
	
	@FXML
	public void skillRichiesteAction(ActionEvent event) {
		dispatcher.renderView("skillOfferta", offerta);
		
	}
	
	@FXML
	public void salvaAction(ActionEvent event) {
		try {
			offerta.setTitoloOfferta(titolo.getText());
			offerta.setTestoOfferta(testo.getText());
			offerta.setLocalita(localita.getText());
			offerta.setDataCreazione(LocalDate.now());
			offerta.setStato(statoOfferta.getValue());
			
			if(offerta.getId() == null) {
				offertaService.createOfferta(offerta);
			}
			else {
				offertaService.updateOfferta(offerta);
			}
			dispatcher.renderView("offerte", offerta.getAzienda());
					
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.renderView("offerte", offerta.getAzienda());
	}
	
	@FXML
	public void eliminaAction(ActionEvent event) {
		if(offerta.getId() != null) {
			try {
				offertaService.deleteOfferta(offerta);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		dispatcher.renderView("offerte", offerta.getAzienda());
	}
	
	@FXML
	public void visualizzaCandidatiAction(ActionEvent event) {
		dispatcher.renderView("candidati", offerta);
	}
	
	@FXML
	public void utentiAttinentiAction(ActionEvent event) {
		dispatcher.renderView("utentiAttinenti", offerta);
	}

}
