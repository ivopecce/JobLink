package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.SkillService;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Richiesta;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class SkillOffertaController implements Initializable, DataInitializable<Offerta> {
	
	@FXML
	private TableView<Richiesta>  skillTable;
	@FXML
	private TableColumn<Richiesta, String> skillTableColumn;
	@FXML
	private TableColumn<Richiesta, String> livelloTableColumn;
	@FXML
	private TableColumn<Richiesta, Button> azioniTableColumn;
	
	private Offerta offerta;
	
	private ViewDispatcher dispatcher;
	private SkillService skillService;
	
	public SkillOffertaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		skillService = factory.getSkillService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		skillTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Richiesta,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Richiesta, String> param) {
				return new SimpleStringProperty(param.getValue().getSkill().getSkill());
			}
		});
		livelloTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Richiesta,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Richiesta, String> param) {
				return new SimpleStringProperty(param.getValue().getLivelloRichiesto().toString());
			}
		});
		azioniTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Richiesta,Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Richiesta, Button> param) {
				final Button offerteButton = new Button("Gestisci");
				offerteButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						dispatcher.renderView("skillRichiesta", param.getValue());
						
					}
				});
				
				return new SimpleObjectProperty<Button>(offerteButton);
			}
		});

	}
	
	@Override
	public void initializeData(Offerta offerta) {
		this.offerta = offerta;
		try {
			List<Richiesta> richiesta = skillService.getSkillRichieste(offerta);
			ObservableList<Richiesta> richiestaData = FXCollections.observableArrayList(richiesta);
			skillTable.setItems(richiestaData);
		}catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void aggiungiSkillAction(ActionEvent event) {
		Richiesta richiesta = new Richiesta();
		richiesta.setOfferta(offerta);
		richiesta.setSkill(new Skill());
		dispatcher.renderView("skillRichiesta", richiesta);
	}
	
	@FXML
	public void indietroAction(ActionEvent event) {
		dispatcher.renderView("offerta", offerta);
	}
	
	

}
