package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.SkillService;
import it.univaq.disim.oop.joblink.business.TemporaryObject;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OffertaUtenteController implements DataInitializable<TemporaryObject>, Initializable {

	@FXML
	private TextField titoloField;
	@FXML
	private TextArea testoField;
	@FXML
	private Label dataInserimentoLabel;
	@FXML
	private TextField skillRichiesteField;
	@FXML
	private Label candidatoLabel;
	@FXML
	private TextField localitaField;
	@FXML
	private TextField aziendaField;
	@FXML
	private Button ritiraCandidaturaButton;
	@FXML
	private Button candidatiButton;
	@FXML
	private Button indietroButton;
	
	private ViewDispatcher dispatcher;
	private OffertaService offertaService;
	private SkillService skillService;
	private Offerta offerta;
	private Persona persona;
	
	public OffertaUtenteController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService = factory.getOffertaService();
		skillService = factory.getSkillService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.titoloField.setEditable(false);
		this.testoField.setEditable(false);
		this.skillRichiesteField.setEditable(false);
		this.localitaField.setEditable(false);
		this.aziendaField.setEditable(false);

	}
	
	@Override
	public void initializeData(TemporaryObject temp) {
		try {
			this.offerta = temp.getOfferta();
			this.persona = temp.getPersona();
			this.testoField.setText(offerta.getTestoOfferta());
			this.titoloField.setText(offerta.getTitoloOfferta());
			this.localitaField.setText(offerta.getLocalita());
			this.dataInserimentoLabel.setText(offerta.getDataCreazione().toString());
			this.aziendaField.setText(offerta.getAzienda().getDenominazione());
			this.skillRichiesteField.setText(skillService.skillRichieste(this.offerta).toString());
			switch (offertaService.getCandidatura(offerta, persona).toString()) {
			case "true":
				this.candidatoLabel.setText("Si");
				break;
			case "false":
				this.candidatoLabel.setText("No");
				break;
			default:
				this.candidatoLabel.setText("No");
				break;
			}
			
			
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void candidatiAction(ActionEvent event) {
		try {
			offertaService.SetCandidatura(this.offerta, this.persona, true);
			initializeData(new TemporaryObject(this.offerta, this.persona));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void ritiraCandidaturaAction(ActionEvent event) {
		try {
			offertaService.SetCandidatura(this.offerta, this.persona, false);
			initializeData(new TemporaryObject(this.offerta, this.persona));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void indietroAction(ActionEvent event) {
		dispatcher.renderView("offerteUtente", this.persona);
	}
}
