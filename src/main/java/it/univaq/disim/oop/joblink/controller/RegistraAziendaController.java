package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.fxml.Initializable;

public class RegistraAziendaController implements Initializable, DataInitializable<Object> {
	
	@FXML
	
	
	private UtenteService utenteService;
	
	private ViewDispatcher dispatcher;

	public RegistraAziendaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

}
