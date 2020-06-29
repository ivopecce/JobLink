package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.SkillService;
import it.univaq.disim.oop.joblink.domain.LivelloSkill;
import it.univaq.disim.oop.joblink.domain.Richiesta;
import it.univaq.disim.oop.joblink.domain.Skill;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SkillRichiestaController implements Initializable, DataInitializable<Richiesta> {
	
	@FXML
	private TextField skillField;
	@FXML
	private ComboBox<LivelloSkill> livelloRichiestoComboBox;
	@FXML
	private Button salvaButton;
	@FXML
	private Button eliminaButton;
	
	private ViewDispatcher dispatcher;
	private SkillService skillService;
	private Richiesta richiesta;
	
	public SkillRichiestaController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		skillService = factory.getSkillService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		livelloRichiestoComboBox.getItems().addAll(LivelloSkill.values());

	}
	
	@Override
	public void initializeData(Richiesta richiesta) {
		this.richiesta = richiesta;
		this.skillField.setText(richiesta.getSkill().getSkill());
		this.livelloRichiestoComboBox.setValue(richiesta.getLivelloRichiesto());
		this.salvaButton.disableProperty().bind(skillField.textProperty().isEmpty());
		this.eliminaButton.disableProperty().bind(skillField.textProperty().isEmpty());
	}
	
	@FXML
	public void salvaAction(ActionEvent event) {
		try {
			richiesta.setLivelloRichiesto(livelloRichiestoComboBox.getValue());
			richiesta.setOfferta(this.richiesta.getOfferta());
			richiesta.setSkill(new Skill());
			richiesta.getSkill().setSkill(skillField.getText());
			
			if(richiesta.getId() == null) {
				skillService.createRichiesta(richiesta);
			}
			else {
				skillService.updateRichiesta(richiesta);
			}
			dispatcher.renderView("skillOfferta", richiesta.getOfferta());
		}catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.renderView("skillOfferta", richiesta.getOfferta());
	}
	
	@FXML
	public void eliminaAction(ActionEvent event) {
		if(richiesta.getId() !=null) {
			try {
				skillService.deleteRichiesta(richiesta);
			}catch (BusinessException e) {
				e.printStackTrace();
			}
			dispatcher.renderView("skillOfferta", richiesta.getOfferta());
		}
	}

}
