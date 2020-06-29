package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Offerta;
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

public class OfferteController implements Initializable, DataInitializable<Azienda>{
	
	@FXML
	private TableView<Offerta> offerteTable;
	@FXML
	private TableColumn<Offerta, Integer> codiceTableColumn;
	@FXML
	private TableColumn<Offerta, String> titoloTableColumn;
	@FXML
	private TableColumn<Offerta, Integer> candidatiTableColumn;
	@FXML
	private TableColumn<Offerta, String> statoTableColumn;
	@FXML
	private TableColumn<Offerta, Button> azioniTableColumn;
	@FXML
	private TableColumn<Offerta, LocalDate> dataInserimentoTableColumn;
	
	private Azienda azienda;
	
	private ViewDispatcher dispatcher;
	private OffertaService offertaService;
	
	public OfferteController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		offertaService = factory.getOffertaService();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		titoloTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offerta,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Offerta, String> param) {
				return new SimpleStringProperty(param.getValue().getTitoloOfferta());
			}
		});
		
		statoTableColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
		
		dataInserimentoTableColumn.setCellValueFactory(new PropertyValueFactory<>("dataCreazione"));
		
		azioniTableColumn.setStyle("-fx-alignment: CENTER;");
		azioniTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offerta,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Offerta, Button> param) {
				final Button offerteButton = new Button("Gestisci");
				offerteButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("offerta", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(offerteButton);
			}
		});
		
	}
	
	@Override
	public void initializeData(Azienda azienda) {
		this.azienda = azienda;
		try {
			List<Offerta> offerte = offertaService.findAllOfferte(azienda);
			ObservableList<Offerta> offerteData = FXCollections.observableArrayList(offerte);
			offerteTable.setItems(offerteData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void aggiungiOffertaAction(ActionEvent event) {
		Offerta offerta = new Offerta();
		offerta.setTitoloOfferta("[Inserisci titolo offerta]");
		offerta.setTestoOfferta("[Inserisci il testo dell'offerta]");
		offerta.setAzienda(azienda);
		offerta.setLocalita("[Inserisci localita`]");
		offerta.setDataCreazione(LocalDate.now());
		dispatcher.renderView("offerta", offerta);
	}
}
