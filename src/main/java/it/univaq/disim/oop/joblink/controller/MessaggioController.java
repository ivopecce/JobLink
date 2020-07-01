package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.MessaggiService;
import it.univaq.disim.oop.joblink.domain.Messaggio;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MessaggioController implements Initializable, DataInitializable<Messaggio> {
	
	@FXML
	private TextField mittenteField;
	@FXML
	private TextField destinatarioField;
	@FXML
	private TextField oggettoField;
	@FXML
	private TextArea testoField;
	@FXML
	private Label etichettaDataLabel;
	@FXML
	private Label dataLabel;
	@FXML
	private Button actionButton;
	
	private ViewDispatcher dispatcher;
	private MessaggiService messaggiService;
	private Messaggio messaggio;
	
	public MessaggioController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		messaggiService = factory.getMessaggiService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mittenteField.setEditable(false);
		destinatarioField.setEditable(false);
	}
	
	@Override
	public void initializeData(Messaggio messaggio) {
		this.messaggio = messaggio;
		mittenteField.setText(messaggio.getMittente().getUsername());
		destinatarioField.setText(messaggio.getDestinatario().getUsername());
		if(messaggio.getId() != null) {
			oggettoField.setText(messaggio.getOggetto());
			oggettoField.setEditable(false);
			testoField.setText(messaggio.getTesto());
			testoField.setEditable(false);
			dataLabel.setText(messaggio.getData().toString());
			actionButton.setText("Rispondi");
			actionButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					Messaggio msg = new Messaggio();
					msg.setMittente(messaggio.getDestinatario());
					msg.setDestinatario(messaggio.getMittente());
					msg.setOggetto(messaggio.getOggetto());
					dispatcher.renderView("messaggio", msg);
				}
			});
		}
		else {
			if(!(messaggio.getOggetto().isEmpty())) {
				oggettoField.setText(messaggio.getOggetto());
				oggettoField.setEditable(false);
			}
			etichettaDataLabel.setVisible(false);
			actionButton.setText("Invia");
			actionButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						Messaggio msg = new Messaggio();
						msg.setMittente(messaggio.getMittente());
						msg.setDestinatario(messaggio.getDestinatario());
						msg.setOggetto(oggettoField.getText());
						msg.setTesto(testoField.getText());
						messaggiService.sendMessaggio(msg);
						dispatcher.renderView("messaggi", messaggio.getMittente());
					} catch (BusinessException e) {
						dispatcher.renderError(e);
					}
				}
			});
		}
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		if(messaggio.getId() == null) dispatcher.renderView("messaggi", messaggio.getMittente());
		else dispatcher.renderView("messaggi", messaggio.getDestinatario());
	}

}
