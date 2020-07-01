/**
 * Controller della homepage
 */
package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Utente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HomeController implements Initializable, DataInitializable<Utente> {
	
	@FXML
	private Label benvenutoLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {}

	@Override
	public void initializeData(Utente utente) {
		StringBuilder testo = new StringBuilder();
		
		if(utente instanceof Azienda) {
			Azienda azienda = (Azienda) utente;
			testo.append("Benvenuta ");
			testo.append(azienda.getDenominazione());
			
		}
		
		if(utente instanceof Persona) {
			Persona persona = (Persona) utente;
			testo.append("Benvenuto ");
			testo.append(persona.getNome());
			testo.append(" ");
			testo.append(persona.getCognome());
		}
		
		benvenutoLabel.setText(testo.toString());;		
		
	}
	
	
	
	
}
