/**
 * Controller della vista candidati
 */
package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Risposta;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CandidatiController implements Initializable, DataInitializable<Offerta> {

	@FXML
	private TableView<Risposta> candidatiTable;
	@FXML
	private TableColumn<Risposta, String> cognomeTableColumn;
	@FXML
	private TableColumn<Risposta, String> nomeTableColumn;
	@FXML
	private TableColumn<Risposta, String> emailTableColumn;
	@FXML
	private TableColumn<Risposta, Button> azioniTableColumn;
	
	private ViewDispatcher dispatcher;
	private Risposta risposta;
	private Offerta offerta;
	private OffertaService offertaService;
	
	public CandidatiController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService = factory.getOffertaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cognomeTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Risposta,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Risposta, String> param) {
				return new SimpleStringProperty(param.getValue().getPersona().getCognome());
			}
		});
		nomeTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Risposta,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Risposta, String> param) {
				return new SimpleStringProperty(param.getValue().getPersona().getNome());
			}
		});
		emailTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Risposta,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Risposta, String> param) {
				return new SimpleStringProperty(param.getValue().getPersona().getEmail());
			}
		});
		azioniTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Risposta,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Risposta, Button> param) {
				final Button cvbutton = new Button("Visualizza CV");
				cvbutton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("profiloCandidato", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(cvbutton);
			}
		});

	}
	
	@Override
	public void initializeData(Offerta offerta) {
		this.offerta=offerta;
		try {
			List<Risposta> risposte = offertaService.getCandidati(offerta);
			ObservableList<Risposta> risposteData = FXCollections.observableArrayList(risposte);
			candidatiTable.setItems(risposteData);
		}catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void indietroAction(ActionEvent event) {
		dispatcher.renderView("offerta", offerta);
	}

}
