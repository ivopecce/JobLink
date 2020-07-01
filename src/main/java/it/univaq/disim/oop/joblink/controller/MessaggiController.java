package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.MessaggiService;
import it.univaq.disim.oop.joblink.domain.Messaggio;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Utente;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class MessaggiController implements Initializable, DataInitializable<Utente> {

	@FXML
	private TableView<Messaggio> messaggiRicevutiTable;
	@FXML
	private TableColumn<Messaggio, String> mittenteColumn;
	@FXML
	private TableColumn<Messaggio, String> oggettoRColumn;
	@FXML
	private TableColumn<Messaggio, LocalDate> dataRicezioneColumn;
	@FXML
	private TableColumn<Messaggio, Button> azioniRColumn;
	@FXML
	private TableView<Messaggio> messaggiInviatiTable;
	@FXML
	private TableColumn<Messaggio, String> destinatarioColumn;
	@FXML
	private TableColumn<Messaggio, String> oggettoIColumn;
	@FXML
	private TableColumn<Messaggio, LocalDate> dataInvioColumn;
	@FXML
	private TableColumn<Messaggio, Button> azioniIcolumn;
	
	private Utente utente;
	private ViewDispatcher dispatcher;
	private MessaggiService messaggiService;
	
	public MessaggiController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		messaggiService = factory.getMessaggiService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*Inizializza tabella messaggi ricevuti*/
		mittenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Messaggio,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Messaggio, String> param) {
				return new SimpleStringProperty(param.getValue().getMittente().getUsername());
			}
		});
		oggettoRColumn.setCellValueFactory(new PropertyValueFactory<>("oggetto"));
		dataRicezioneColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
		azioniRColumn.setStyle("-fx-alignment: CENTER;");
		azioniRColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Messaggio,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Messaggio, Button> param) {
				final Button messaggioButton = new Button("Visualizza");
				messaggioButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("messaggio", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(messaggioButton);
			}
		});
		
		/*Inizializza tabella messaggi inviati*/
		destinatarioColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Messaggio,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Messaggio, String> param) {
				return new SimpleStringProperty(param.getValue().getDestinatario().getUsername());
			}
		});
		oggettoIColumn.setCellValueFactory(new PropertyValueFactory<>("oggetto"));
		dataInvioColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
		azioniIcolumn.setStyle("-fx-alignment: CENTER;");
		azioniIcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Messaggio,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Messaggio, Button> param) {
				final Button messaggioButton = new Button("Visualizza");
				messaggioButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("messaggio", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(messaggioButton);
			}
		});
		

	}
	
	@Override
	public void initializeData(Utente utente) {
		this.utente = utente;
		try {
			/*Carica i messaggi ricevuti e li inserisce nella tabella*/
			List<Messaggio> messaggiRicevuti = messaggiService.getMessaggiRicevuti(utente);
			ObservableList<Messaggio> ricevutiData = FXCollections.observableArrayList(messaggiRicevuti);
			messaggiRicevutiTable.setItems(ricevutiData);
			
			/*Carica i messaggi inviati e li inserisce nella tabella*/
			List<Messaggio> messaggiInviati = messaggiService.getMessaggiInviati(utente);
			ObservableList<Messaggio> inviatiData = FXCollections.observableArrayList(messaggiInviati);
			messaggiInviatiTable.setItems(inviatiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}
