package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.domain.Utente;
import javafx.fxml.Initializable;

public class MessaggiController implements Initializable, DataInitializable<Utente> {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void initializeData(Utente t) {
		// TODO Auto-generated method stub
		DataInitializable.super.initializeData(t);
	}

}
