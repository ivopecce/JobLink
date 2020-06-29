package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.TemporaryObject;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class OfferteUtenteController implements DataInitializable<Persona>, Initializable {
	
	@FXML
	private TableView<Offerta> offerteTable;
	@FXML
	private TableColumn<Offerta, String> titoloTableColumn;
	@FXML
	private TableColumn<Offerta, String> localitaTableColumn;
	@FXML
	private TableColumn<Offerta, LocalDate> dataInserimentoTableColumn;
	@FXML
	private TableColumn<Offerta, Button> azioniTableColumn;
	
	private Persona persona;
	
	private ViewDispatcher dispatcher;
	private OffertaService offertaService;
	
	
	public OfferteUtenteController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService  = factory.getOffertaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titoloTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offerta,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Offerta, String> param) {
				return new SimpleStringProperty(param.getValue().getTitoloOfferta());
			}
		});
		
		localitaTableColumn.setCellValueFactory(new PropertyValueFactory<>("localita"));
		dataInserimentoTableColumn.setCellValueFactory(new PropertyValueFactory<>("dataCreazione"));
		
		azioniTableColumn.setStyle("-fx-alignment: CENTER;");
		azioniTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offerta,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Offerta, Button> param) {
				final Button offerteButton = new Button("Visualizza");
				offerteButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("offertaUtente", new TemporaryObject(param.getValue(), persona));
						
					}
				});
				
				return new SimpleObjectProperty<Button>(offerteButton);
			}
		});
	}
	
	@Override
	public void initializeData(Persona persona) {
		this.persona = persona;
		try {
			List<Offerta> offerte = offertaService.findOfferteAttinenti(persona);
			ObservableList<Offerta> offerteData = FXCollections.observableArrayList(offerte);
			offerteTable.setItems(offerteData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}
