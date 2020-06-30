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
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.domain.Skill;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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
	private TableView<Possiede> skillTable;
	@FXML 
	private TableColumn<Possiede, String> skillColumn;
	@FXML 
	private TableColumn<Possiede, LivelloSkill> livelloColumn;
	@FXML 
	private TableColumn<Possiede, Button> azioniSColumn;	
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
		
		/*Inizializza tabella Formazione*/
		titoloFormazioneColumn.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		dataInizioFColumn.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));
		dataFineFColumn.setCellValueFactory(new PropertyValueFactory<>("dataFine"));
		istitutoColumn.setCellValueFactory(new PropertyValueFactory<>("istituto"));
		votoColumn.setCellValueFactory(new PropertyValueFactory<>("voto"));
		azioniFColumn.setStyle("-fx-alignment: CENTER;");
		azioniFColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Formazione,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Formazione, Button> param) {
				final Button formazioneButton= new Button("Modifica");
				formazioneButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("formazione", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(formazioneButton);
			}
		});
		
		/*Inizializza tabella Esperienza*/
		titoloEsperienzaColumn.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		dataInizioEColumn.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));
		dataFineEColumn.setCellValueFactory(new PropertyValueFactory<>("dataFine"));
		aziendaColumn.setCellValueFactory(new PropertyValueFactory<>("azienda"));
		azioniEColumn.setStyle("-fx-alignment: CENTER;");
		azioniEColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Esperienza,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Esperienza, Button> param) {
				final Button esperienzaButton= new Button("Modifica");
				esperienzaButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("esperienza", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(esperienzaButton);
			}
		});
		
		/*Inizializza tabella Skill*/
		skillColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Possiede,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Possiede, String> param) {
				return new SimpleStringProperty(param.getValue().getSkill().getSkill());
			}
		});
		livelloColumn.setCellValueFactory(new PropertyValueFactory<>("livelloPosseduto"));
		azioniSColumn.setStyle("-fx-alignment: CENTER;");
		azioniSColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Possiede,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Possiede, Button> param) {
				final Button skillButton= new Button("Modifica");
				skillButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("skill", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(skillButton);
			}
		});
		
	}
	
	@Override
	public void initializeData(Persona persona) {
		this.persona = persona;
		try {
			/*Inizializza i dati del profilo utente*/
			this.nomeField.setText(persona.getNome());
			this.cognomeField.setText(persona.getCognome());
			this.dataNascitaField.setValue(persona.getDataDiNascita());
			this.genereComboBox.setValue(persona.getGenere());
			this.residenzaField.setText(persona.getResidenza());
			this.usernameField.setText(persona.getUsername());
			this.emailField.setText(persona.getEmail());
			this.telefonoField.setText(persona.getTelefono());
			
			/*Inizializza i dati della formazione della persona*/
			List<Formazione> formazioneList = profiloPersonaService.findAllFormazione(persona);
			ObservableList<Formazione> formazioneData = FXCollections.observableArrayList(formazioneList);
			formazioneTable.setItems(formazioneData);
			
			/*Inizializza i dati dell'esperienza della persona*/
			List<Esperienza> esperienzaList = profiloPersonaService.findAllEsperienza(persona);
			ObservableList<Esperienza> esperienzaData = FXCollections.observableArrayList(esperienzaList);
			esperienzaTable.setItems(esperienzaData);
			
			/*Inizializza i dati delle skill della persona*/
			List<Possiede> possiedeList = profiloPersonaService.findAllSkill(persona);
			ObservableList<Possiede> possiedeData = FXCollections.observableArrayList(possiedeList);
			skillTable.setItems(possiedeData);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void aggiungiFormazioneAction(ActionEvent event) {
		Formazione f = new Formazione();
		f.setPersona(this.persona);
		dispatcher.renderView("formazione", f);
	}
	
	public void aggiungiEsperienzaAction(ActionEvent event) {
		Esperienza esp = new Esperienza();
		esp.setPersona(this.persona);
		dispatcher.renderView("esperienza", esp);
	}
	
	public void aggiungiSkillAction(ActionEvent event) {
		Possiede p = new Possiede();
		p.setPersona(this.persona);
		p.setSkill(new Skill());
		dispatcher.renderView("skill", p);
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
		try {
			persona.setCognome(cognomeField.getText());
			persona.setNome(nomeField.getText());
			persona.setDataDiNascita(dataNascitaField.getValue());
			persona.setGenere(genereComboBox.getValue());
			persona.setResidenza(residenzaField.getText());
			persona.setEmail(emailField.getText());
			persona.setTelefono(telefonoField.getText());
			
			utenteService.updatePersona(persona);
			
			dispatcher.loggedIn(persona);
			
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
		
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.loggedIn(persona);
	}

}
