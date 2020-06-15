package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.impl.file.FileOffertaServiceImpl;
import it.univaq.disim.oop.joblink.business.impl.ram.RAMOffertaServiceImpl;
import it.univaq.disim.oop.joblink.business.impl.ram.RAMUtenteServiceImpl;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.StatoOfferta;
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
	
	private ViewDispatcher dispatcher;
	private OffertaService offertaService;
	
	public OfferteController() {
		dispatcher = ViewDispatcher.getInstance();
//		offertaService = new RAMOffertaServiceImpl();
//		offertaService = new FileOffertaServiceImpl();
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
		
//		statoTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offerta,String>, ObservableValue<String>>() {
//
//			@Override
//			public ObservableValue<String> call(CellDataFeatures<Offerta, String> param) {
//				if(param.getValue().getStato().equals(StatoOfferta.ATTIVA)) {
//					return new SimpleStringProperty("Attiva");
//				}
//				else return new SimpleStringProperty("Non attiva");
//			}
//		
//		
//		});
		
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
//		Il metodo in basso `e poi da modificare con l'id di sessione quando verra` implementato
		offerta.setAzienda(new Azienda());
		offerta.setLocalita("[Inserisci localita`]");
		Date oggi = Calendar.getInstance().getTime();
		offerta.setDataCreazione(oggi.getYear(), oggi.getMonth(), oggi.getDay());
		dispatcher.renderView("offerta", offerta);
	}
}
