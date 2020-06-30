package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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

public class RisultatiRicercaOfferteController implements Initializable, DataInitializable<TemporaryObject> {

	@FXML
	private TableView<Offerta> offerteTable;
	@FXML
	private TableColumn<Offerta, String> titoloTableColumn;
	@FXML
	private TableColumn<Offerta, String> localitaTableColumn;
	@FXML
	private TableColumn<Offerta, Button> azioniTableColumn;
	
	private ViewDispatcher dispatcher;
	private Persona persona;
	private OffertaService offertaService;
	
	public RisultatiRicercaOfferteController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService = factory.getOffertaService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titoloTableColumn.setCellValueFactory(new PropertyValueFactory<>("titoloOfferta"));
		localitaTableColumn.setCellValueFactory(new PropertyValueFactory<>("localita"));
		statoTableColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
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
	public void initializeData(TemporaryObject temp) {
		List<Offerta> offerte = new ArrayList();
		try {
			this.persona = temp.getPersona();
			if(!temp.getCercaTitolo().isEmpty()) offerte = offertaService.findOfferteByTitolo(temp.getCercaTitolo());
			else if(!temp.getCercaLocalita().isEmpty()) offerte = offertaService.findOfferteByLocalita(temp.getCercaLocalita());
			ObservableList<Offerta> offerteData = FXCollections.observableArrayList(offerte);
			offerteTable.setItems(offerteData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}
