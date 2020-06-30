package it.univaq.disim.oop.joblink.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.SkillService;
import it.univaq.disim.oop.joblink.domain.LivelloSkill;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SkillController implements Initializable, DataInitializable<Possiede> {

	@FXML
	private TextField skillField;
	@FXML
	private ComboBox<LivelloSkill> livelloComboBox;
	@FXML
	private Button salvaButton;
	
	private ViewDispatcher dispatcher;
	private SkillService skillService;
	private Possiede possiede;
	
	public SkillController() {
		dispatcher = ViewDispatcher.getInstance();
		JobLinkBusinessFactory factory = JobLinkBusinessFactory.getInstance();
		skillService = factory.getSkillService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		livelloComboBox.getItems().addAll(LivelloSkill.values());
		salvaButton.disableProperty().bind(skillField.textProperty().isEmpty());

	}
	
	@Override
	public void initializeData(Possiede possiede) {
		this.possiede=possiede;
		this.skillField.setText(possiede.getSkill().getSkill());
		this.livelloComboBox.setValue(possiede.getLivelloPosseduto());
	}
	
	@FXML
	public void salvaAction(ActionEvent event) {
		try {
			possiede.getSkill().setSkill(skillField.getText());
			possiede.setLivelloPosseduto(livelloComboBox.getValue());
			if(possiede.getSkill().getId() == null) skillService.createPossiede(possiede);
			else skillService.updatePossiede(possiede);
			dispatcher.renderView("profiloUtente", possiede.getPersona());
		} catch (BusinessException e) { 
			dispatcher.renderError(e);
		}
	}
	
	@FXML
	public void annullaAction(ActionEvent event) {
		dispatcher.renderView("profiloUtente", possiede.getPersona());
	}
	
	@FXML
	public void eliminaAction(ActionEvent event) {
		if(possiede.getSkill().getId() != null) {
			try {
				skillService.deletePossiede(possiede);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		dispatcher.renderView("profiloUtente", possiede.getPersona());
	}

}
