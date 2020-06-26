package it.univaq.disim.oop.joblink.controller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.border.TitledBorder;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.ProfiloPersonaService;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Esperienza;
import it.univaq.disim.oop.joblink.domain.Formazione;
import it.univaq.disim.oop.joblink.domain.LivelloSkill;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProfiloUtenteController implements Initializable, DataInitializable<Persona> {

	private static final ActionEvent ActionEvent = null;
	@FXML
	private TextField nomeField;
	@FXML
	private TextField cognomeField;
	@FXML
	private TextField residenzaField;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField telefonoField;
	@FXML
	private DatePicker dataNascitaField;
	@FXML
	private ComboBox<String> genereComboBox;
	@FXML
	private ScrollPane formazioneScrollPane;
	@FXML
	private VBox formazioneVBox;
	@FXML
	private ScrollPane esperienzaScrollPane;
	@FXML
	private VBox esperienzaVBox;
	@FXML
	private GridPane gridSkill;
	@FXML
	private Button eliminaUtenteButton;
	@FXML
	private Button salvaButton;
	@FXML
	private Button annullaButton;
	
	private ViewDispatcher dispatcher;
	private Persona persona;
	private UtenteService utenteService;
	private ProfiloPersonaService profiloPersonaService;
	
	
	public ProfiloUtenteController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		aggiungiSkillAction(ActionEvent);
		aggiungiFormazioneAction(ActionEvent);
		aggiungiEsperienzaAction(ActionEvent);

	}
	
	@Override
	public void initializeData(Persona persona) {
//		this.persona = persona;
//		try {
//			List<Formazione> formazione = profiloPersonaService.findAllFormazione(persona);
//			List<Esperienza> esperienza = profiloPersonaService.findAllEsperienza(persona);
//			List<Possiede> possiede = profiloPersonaService.findAllSkill(persona);
//		} catch (BusinessException e) {
//			dispatcher.renderError(e);
//		}
	}
	
	public void aggiungiSkillAction(ActionEvent event) {
		int index = gridSkill.getRowCount();
		for (Node n : gridSkill.getChildren()) {
	        Integer row = GridPane.getRowIndex(n);
	        if (row != null && row.intValue() >= index) {
	        	row+=1;
	            GridPane.setRowIndex(n, row);
	        }
	    }
		Label l = new Label("Skill:");
		TextField skillField = new TextField();
		Label l1 = new Label("Livello:");
		ComboBox livelloComboBox = new ComboBox<String>();
		Button aggiungiSkillButton = new Button("+");
		aggiungiSkillButton.setOnAction(evt -> {
			aggiungiSkillAction(evt);
		});
		
		gridSkill.addRow(index, l, skillField, l1, livelloComboBox, aggiungiSkillButton);
	}
	
	public void rimuoviSkillAction(ActionEvent event) {
		
	}
	
	public void aggiungiFormazioneAction(ActionEvent event) {
		TitledPane formazioneTPane = new TitledPane();
		formazioneTPane.setText("Formazione");
		GridPane formazioneGridPane = new GridPane();
		GridPane.setRowIndex(formazioneGridPane, 7);
		GridPane.setColumnIndex(formazioneGridPane, 2);
		Label titoloLabel = new Label("Titolo:");
		Label descrizioneLabel = new Label("Descrizione:");
		Label dataInizioLabel = new Label("Data inizo:");
		Label dataFineLabel = new Label("Data fine:");
		Label istitutoLabel = new Label("Istituto:");
		Label votoLabel = new Label("Voto:");
		TextField titoloFormazioneField = new TextField();
		TextArea descrizioneFormazioneField = new TextArea();
		DatePicker dataInizioFormazioneField = new DatePicker();
		DatePicker dataFineFormazioneField = new DatePicker();
		TextField istitutoField = new TextField();
		TextField votoField = new TextField();
		Button aggiungiFormazioneButton = new Button("Aggiungi formazione");
		aggiungiFormazioneButton.setOnAction(evt -> {
			aggiungiFormazioneAction(evt);
		});
		formazioneGridPane.addColumn(0, titoloLabel, descrizioneLabel, dataInizioLabel, dataFineLabel, istitutoLabel, votoLabel);
		formazioneGridPane.addColumn(1, titoloFormazioneField, descrizioneFormazioneField, dataInizioFormazioneField, dataFineFormazioneField, istitutoField, votoField, aggiungiFormazioneButton);
		formazioneTPane.setContent(formazioneGridPane);
		this.formazioneVBox.getChildren().add(formazioneTPane);
		this.formazioneScrollPane.setContent(formazioneVBox);
		
		
		
	}
	
	public void eliminaFormazioneAction(ActionEvent event) {
		
	}
	
	public void aggiungiEsperienzaAction(ActionEvent event) {
		TitledPane esperienzaTPane = new TitledPane();
		esperienzaTPane.setText("Esperienza");
		GridPane esperienzaGridPane = new GridPane();
		GridPane.setRowIndex(esperienzaGridPane, 7);
		GridPane.setColumnIndex(esperienzaGridPane, 2);
		Label titoloLabel = new Label("Titolo:");
		Label descrizioneLabel = new Label("Descrizione:");
		Label dataInizioLabel = new Label("Data inizo:");
		Label dataFineLabel = new Label("Data fine:");
		Label aziendaLabel = new Label("Azienda:");
		Label localitaLabel = new Label("Localita`:");
		TextField titoloEsperienzaField = new TextField();
		TextArea descrizioneEsperienzaField = new TextArea();
		DatePicker dataInizioEsperienzaField = new DatePicker();
		DatePicker dataFineEsperienzaField = new DatePicker();
		TextField aziendaField = new TextField();
		TextField localitaField = new TextField();
		Button aggiungiEsperienzaButton = new Button("Aggiungi esperienza");
		aggiungiEsperienzaButton.setOnAction(evt -> {
			aggiungiEsperienzaAction(evt);
		});
		esperienzaGridPane.addColumn(0, titoloLabel, descrizioneLabel, dataInizioLabel, dataFineLabel, aziendaLabel, localitaLabel);
		esperienzaGridPane.addColumn(1, titoloEsperienzaField, descrizioneEsperienzaField, dataInizioEsperienzaField, dataFineEsperienzaField, aziendaField, localitaField, aggiungiEsperienzaButton);
		esperienzaTPane.setContent(esperienzaGridPane);
		this.esperienzaVBox.getChildren().add(esperienzaTPane);
		this.esperienzaScrollPane.setContent(esperienzaVBox);
	}
	
	public void rimuoviEsperienzaAction(ActionEvent event) {
		
	}
	
	public void eliminaUtenteAction(ActionEvent event) {
		
	}
	
	public void salvaAction(ActionEvent event) {
		
	}
	
	public void annullaAction(ActionEvent event) {
		
	}

}
