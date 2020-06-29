package it.univaq.disim.oop.joblink.controller;


import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.ProfiloPersonaService;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Esperienza;
import it.univaq.disim.oop.joblink.domain.Formazione;
import it.univaq.disim.oop.joblink.domain.Genere;
import it.univaq.disim.oop.joblink.domain.LivelloSkill;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.domain.Skill;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProfiloUtenteController implements Initializable, DataInitializable<Persona> {

	@FXML
	private TextField nomeField;
	@FXML
	private TextField cognomeField;
	@FXML
	private TextField residenzaField;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField telefonoField;
	@FXML
	private DatePicker dataNascitaField;
	@FXML
	private ComboBox<Genere> genereComboBox;
	@FXML
	private TableView<Formazione> formazioneTable;
	@FXML
	private TableColumn<Formazione, String> titoloFormazioneColumn;
	@FXML
	private TableColumn<Formazione, LocalDate> dataInizioFColumn;
	@FXML
	private TableColumn<Formazione, LocalDate> dataFineFColumn;
	@FXML
	private TableColumn<Formazione, String> istitutoColumn;
	@FXML
	private TableColumn<Formazione, Integer> votoColumn;
	@FXML
	private TableColumn<Formazione, Button> azioniFColumn;
	@FXML
	private TableView<Esperienza> esperienzaTable;
	@FXML
	private TableColumn<Esperienza, String> titoloEsperienzaColumn;
	@FXML
	private TableColumn<Esperienza, LocalDate> dataInizioEColumn;
	@FXML
	private TableColumn<Esperienza, LocalDate> dataFineEColumn;
	@FXML
	private TableColumn<Esperienza, String> aziendaColumn;
	@FXML
	private TableColumn<Esperienza, Button> azioniEColumn;
	@FXML
	private TableView<Skill> skillTable;
	@FXML 
	private TableColumn<Skill, String> skillColumn;
	@FXML 
	private TableColumn<Skill, LivelloSkill> livelloColumn;
	@FXML 
	private TableColumn<Skill, Button> azioniSColumn;	
	@FXML
	private Button eliminaUtenteButton;
	@FXML
	private Button salvaButton;
	@FXML
	private Button annullaButton;
	
	private ViewDispatcher dispatcher;
	private Persona persona;
	private ProfiloPersonaService profiloPersonaService;
	private UtenteService utenteService;
	
	private List<Esperienza> esperienzaList = new ArrayList<>();
	private List<Formazione> formazioneList = new ArrayList<>();
	private List<Possiede> possiedeList = new ArrayList<>();

	
	public ProfiloUtenteController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		profiloPersonaService = factory.getProfiloPersonaService();
		utenteService = factory.getUtenteService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.genereComboBox.getItems().addAll(Genere.values());
		this.usernameField.setEditable(false);

	}
	
	@Override
	public void initializeData(Persona persona) {
		this.persona = persona;
		try {
			formazioneList = profiloPersonaService.findAllFormazione(persona);
			loadFormazione(formazioneList);
			esperienzaList = profiloPersonaService.findAllEsperienza(persona);
			loadEsperienza(esperienzaList);
			possiedeList = profiloPersonaService.findAllSkill(persona);
			loadSkill(possiedeList);
			this.nomeField.setText(persona.getNome());
			this.cognomeField.setText(persona.getCognome());
			this.dataNascitaField.setValue(persona.getDataDiNascita());
			this.genereComboBox.setValue(persona.getGenere());
			this.residenzaField.setText(persona.getResidenza());
			this.usernameField.setText(persona.getUsername());
			this.emailField.setText(persona.getEmail());
			this.telefonoField.setText(persona.getTelefono());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void loadSkill(List<Possiede> possiede) {
		if(possiede.isEmpty()) aggiungiSkillAction(ActionEvent);
		
		for(Possiede p : possiede) {
			int index = gridSkill.getRowCount();
			for (Node n : gridSkill.getChildren()) {
		        Integer row = GridPane.getRowIndex(n);
		        if (row != null && row.intValue() >= index) {
		        	row+=1;
		            GridPane.setRowIndex(n, row);
		        }
		    }
			Label l = new Label("Skill:");
			TextField skillField = new TextField(p.getSkill().getSkill());
			Label l1 = new Label("Livello:");
			ComboBox livelloComboBox = new ComboBox<LivelloSkill>();
			livelloComboBox.getItems().addAll(LivelloSkill.values());
			livelloComboBox.setValue(p.getLivelloPosseduto());
			Button aggiungiSkillButton = new Button("+");
			aggiungiSkillButton.setOnAction(evt -> {
				aggiungiSkillAction(evt);
			});
			
			gridSkill.addRow(index, l, skillField, l1, livelloComboBox, aggiungiSkillButton);
		}
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
	
	public void loadFormazione(List<Formazione> formazione) {
		if(formazione.isEmpty()) {
			aggiungiFormazioneAction(ActionEvent);
			
		}
		for(Formazione f : formazione) {
			TitledPane formazioneTPane = new TitledPane();
			formazioneTPane.setText(f.getTitolo());
			GridPane formazioneGridPane = new GridPane();
			GridPane.setRowIndex(formazioneGridPane, 7);
			GridPane.setColumnIndex(formazioneGridPane, 2);
			Label titoloLabel = new Label("Titolo:");
			Label descrizioneLabel = new Label("Descrizione:");
			Label dataInizioLabel = new Label("Data inizo:");
			Label dataFineLabel = new Label("Data fine:");
			Label istitutoLabel = new Label("Istituto:");
			Label votoLabel = new Label("Voto:");
			TextField idField = new TextField(f.getId().toString());
			idField.setVisible(false);
			TextField titoloFormazioneField = new TextField(f.getTitolo());
			TextArea descrizioneFormazioneField = new TextArea(f.getDescrizione());
			DatePicker dataInizioFormazioneField = new DatePicker(f.getDataInizio());
			DatePicker dataFineFormazioneField = new DatePicker(f.getDataFine());
			TextField istitutoField = new TextField(f.getIstituto());
			TextField votoField = new TextField(f.getVoto().toString());
			Button aggiungiFormazioneButton = new Button("Aggiungi formazione");
			aggiungiFormazioneButton.setOnAction(evt -> {
				aggiungiFormazioneAction(evt);
			});
			formazioneGridPane.addColumn(0, titoloLabel, descrizioneLabel, dataInizioLabel, dataFineLabel, istitutoLabel, votoLabel);
			formazioneGridPane.addColumn(1, titoloFormazioneField, descrizioneFormazioneField, dataInizioFormazioneField, dataFineFormazioneField, istitutoField, votoField, aggiungiFormazioneButton, idField);
			formazioneTPane.setContent(formazioneGridPane);
			this.formazioneVBox.getChildren().add(formazioneTPane);
			this.formazioneScrollPane.setContent(formazioneVBox);
		}
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
	
	public void loadEsperienza(List<Esperienza> esperienza) {
		if(esperienza.isEmpty()) aggiungiEsperienzaAction(ActionEvent);
		
		for(Esperienza esp : esperienza) {
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
			TextField idField = new TextField(esp.getId().toString());
			idField.setVisible(false);
			TextField titoloEsperienzaField = new TextField(esp.getTitolo());
			TextArea descrizioneEsperienzaField = new TextArea(esp.getDescrizione());
			DatePicker dataInizioEsperienzaField = new DatePicker(esp.getDataInizio());
			DatePicker dataFineEsperienzaField = new DatePicker(esp.getDataFine());
			TextField aziendaField = new TextField(esp.getAzienda());
			TextField localitaField = new TextField(esp.getLocalita());
			Button aggiungiEsperienzaButton = new Button("Aggiungi esperienza");
			aggiungiEsperienzaButton.setOnAction(evt -> {
				aggiungiEsperienzaAction(evt);
			});
			esperienzaGridPane.addColumn(0, titoloLabel, descrizioneLabel, dataInizioLabel, dataFineLabel, aziendaLabel, localitaLabel);
			esperienzaGridPane.addColumn(1, titoloEsperienzaField, descrizioneEsperienzaField, dataInizioEsperienzaField, dataFineEsperienzaField, aziendaField, localitaField, aggiungiEsperienzaButton, idField);
			esperienzaTPane.setContent(esperienzaGridPane);
			this.esperienzaVBox.getChildren().add(esperienzaTPane);
			this.esperienzaScrollPane.setContent(esperienzaVBox);
		}
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
		try {
			utenteService.deletePersona(persona);
			dispatcher.logout();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void salvaAction(ActionEvent event) {
		
		
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.loggedIn(persona);
	}

}
