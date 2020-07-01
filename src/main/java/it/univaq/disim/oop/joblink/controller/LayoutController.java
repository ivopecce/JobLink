package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Utente;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class LayoutController implements Initializable, DataInitializable<Utente> {

	private static final MenuElement MENU_HOME = new MenuElement("Home", "home");

	private static final MenuElement[] MENU_AZIENDA = { new MenuElement("Profilo", "profiloAzienda"), new MenuElement("Gestione Offerte di lavoro", "offerte"), 
			new MenuElement("Messaggi", "messaggi") };
	private static final MenuElement[] MENU_PERSONA = { new MenuElement("Profilo", "profiloUtente"), 
			new MenuElement("Visualizza offerte di lavoro", "offerteUtente"), new MenuElement("Ricerca offerte", "ricercaOfferte"), 
			new MenuElement("Messaggi", "messaggi") };
			
	private Utente utente;
	
	@FXML
	private VBox menuBar;
	
	private ViewDispatcher dispatcher;
	
	public LayoutController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {}

	@Override
	public void initializeData(Utente utente) {
		this.utente = utente;
		menuBar.getChildren().addAll(createButton(MENU_HOME));
		menuBar.getChildren().add(new Separator());
		if(utente instanceof Azienda) {
			for(MenuElement menu : MENU_AZIENDA) {
				menuBar.getChildren().add(createButton(menu));
			}
		}
		if(utente instanceof Persona) {
			for(MenuElement menu : MENU_PERSONA) {
				menuBar.getChildren().add(createButton(menu));
			}
		}
	}
	
	@FXML
	public void logoutAction(MouseEvent event) {
		dispatcher.logout();
		
	}

	private Button createButton(MenuElement viewItem) {
		Button button = new Button(viewItem.getNome());
		button.setStyle("-fx-background-color: transparent; -fx-font-size: 14;");
		button.setTextFill(Paint.valueOf("white"));
		button.setPrefHeight(10);
		button.setPrefWidth(260);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dispatcher.renderView(viewItem.getVista(), utente);
			}
		});
		return button;
	}

}
