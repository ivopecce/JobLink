package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;
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

public class UtentiAttinentiController implements Initializable, DataInitializable<Offerta> {

	@FXML
	private TableView<Persona> attinentiTable;
	@FXML
	private TableColumn<Persona, String> cognomeTableColumn;
	@FXML
	private TableColumn<Persona, String> nomeTableColumn;
	@FXML
	private TableColumn<Persona, String> emailTableColumn;
	@FXML
	private TableColumn<Persona, String> candidatoTableColumn;
	@FXML
	private TableColumn<Persona, Button> azioniTableColumn;
	
	private ViewDispatcher dispatcher;
	private Offerta offerta;
	private OffertaService offertaService;
	
	public UtentiAttinentiController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService = factory.getOffertaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cognomeTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Persona,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Persona, String> param) {
				return new SimpleStringProperty(param.getValue().getCognome());
			}
		});
		nomeTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Persona,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Persona, String> param) {
				return new SimpleStringProperty(param.getValue().getNome());
			}
		});
		emailTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Persona,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Persona, String> param) {
				return new SimpleStringProperty(param.getValue().getEmail());
			}
		});
		candidatoTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Persona,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Persona, String> param) {
				String candidato = new String();
				try {
					if(offertaService.getCandidatura(offerta, param.getValue())) candidato = "Si";
					else candidato = "No";
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				return new SimpleStringProperty(candidato);
			}
		});
		azioniTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Persona,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Persona, Button> param) {
				final Button cvbutton = new Button("Visualizza CV");
				cvbutton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						Risposta risposta = new Risposta();
						risposta.setPersona(param.getValue());
						risposta.setOfferta(offerta);
						dispatcher.renderView("profiloCandidato", risposta);
						
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
			List<Persona> attinenti = offertaService.getAttinenti(offerta);
			ObservableList<Persona> attinentiData = FXCollections.observableArrayList(attinenti);
			attinentiTable.setItems(attinentiData);
		}catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void indietroAction(ActionEvent event) {
		dispatcher.renderView("offerta", offerta);
	}

}
