package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.ProfiloPersonaService;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Esperienza;
import it.univaq.disim.oop.joblink.domain.Formazione;
import it.univaq.disim.oop.joblink.domain.Genere;
import it.univaq.disim.oop.joblink.domain.LivelloSkill;
import it.univaq.disim.oop.joblink.domain.Messaggio;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.domain.Risposta;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProfiloCandidatoController implements Initializable, DataInitializable<Risposta> {

	@FXML
	private TextField nomeField;
	@FXML
	private TextField cognomeField;
	@FXML
	private TextField residenzaField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField telefonoField;
	@FXML
	private TextField dataDiNascitaField;
	@FXML
	private TextField genereField;
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
	
	private ViewDispatcher dispatcher;
	private Persona persona;
	private Risposta risposta;
	private ProfiloPersonaService profiloPersonaService;
	private OffertaService offertaService;
	
	private List<Esperienza> esperienzaList = new ArrayList<>();
	private List<Formazione> formazioneList = new ArrayList<>();
	private List<Possiede> possiedeList = new ArrayList<>();
	
	public ProfiloCandidatoController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		profiloPersonaService = factory.getProfiloPersonaService();
		offertaService = factory.getOffertaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cognomeField.setEditable(false);
		this.nomeField.setEditable(false);
		this.dataDiNascitaField.setEditable(false);
		this.genereField.setEditable(false);
		this.residenzaField.setEditable(false);
		this.emailField.setEditable(false);
		this.telefonoField.setEditable(false);
		
	}
	
	@Override
	public void initializeData(Risposta risposta) {
		this.risposta = risposta;
		this.persona = risposta.getPersona();
		try {
			formazioneList = profiloPersonaService.findAllFormazione(persona);
			loadFormazione(formazioneList);
			esperienzaList = profiloPersonaService.findAllEsperienza(persona);
			loadEsperienza(esperienzaList);
			possiedeList = profiloPersonaService.findAllSkill(persona);
			loadSkill(possiedeList);
			this.nomeField.setText(persona.getNome());
			this.cognomeField.setText(persona.getCognome());
			this.dataDiNascitaField.setText(persona.getDataDiNascita().toString());
			this.genereField.setText(persona.getGenere().toString());
			this.residenzaField.setText(persona.getResidenza());
			this.emailField.setText(persona.getEmail());
			this.telefonoField.setText(persona.getTelefono());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadSkill(List<Possiede> possiede) {		
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
			TextField livelloField = new TextField(p.getLivelloPosseduto().toString());
			skillField.setEditable(false);
			livelloField.setEditable(false);
			gridSkill.addRow(index, l, skillField, l1, livelloField);
		}
	}
	
	public void loadFormazione(List<Formazione> formazione) {
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
			titoloFormazioneField.setEditable(false);
			descrizioneFormazioneField.setEditable(false);
			dataInizioFormazioneField.setEditable(false);
			istitutoField.setEditable(false);
			votoField.setEditable(false);
			formazioneGridPane.addColumn(0, titoloLabel, descrizioneLabel, dataInizioLabel, dataFineLabel, istitutoLabel, votoLabel);
			formazioneGridPane.addColumn(1, titoloFormazioneField, descrizioneFormazioneField, dataInizioFormazioneField, dataFineFormazioneField, istitutoField, votoField, idField);
			formazioneTPane.setContent(formazioneGridPane);
			this.formazioneVBox.getChildren().add(formazioneTPane);
			this.formazioneScrollPane.setContent(formazioneVBox);
		}
	}
	
	public void loadEsperienza(List<Esperienza> esperienza) {
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
			titoloEsperienzaField.setEditable(false);
			descrizioneEsperienzaField.setEditable(false);
			dataInizioEsperienzaField.setEditable(false);
			dataFineEsperienzaField.setEditable(false);
			aziendaField.setEditable(false);
			localitaField.setEditable(false);
			esperienzaGridPane.addColumn(0, titoloLabel, descrizioneLabel, dataInizioLabel, dataFineLabel, aziendaLabel, localitaLabel);
			esperienzaGridPane.addColumn(1, titoloEsperienzaField, descrizioneEsperienzaField, dataInizioEsperienzaField, dataFineEsperienzaField, aziendaField, localitaField, idField);
			esperienzaTPane.setContent(esperienzaGridPane);
			this.esperienzaVBox.getChildren().add(esperienzaTPane);
			this.esperienzaScrollPane.setContent(esperienzaVBox);
		}
	}
	
	@FXML
	public void contattaAction(ActionEvent event) {
		Messaggio messaggio = new Messaggio();
		messaggio.setMittente(risposta.getOfferta().getAzienda());
		messaggio.setDestinatario(persona);
		messaggio.setOggetto("");
		dispatcher.renderView("messaggio", messaggio);
	}
	
	@FXML
	public void indietroAction(ActionEvent event) {
		try {
			if(offertaService.getCandidatura(risposta.getOfferta(), persona)) dispatcher.renderView("candidati", this.risposta.getOfferta());
			else dispatcher.renderView("utentiAttinenti", this.risposta.getOfferta());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
