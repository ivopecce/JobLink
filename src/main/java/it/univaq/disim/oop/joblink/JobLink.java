package it.univaq.disim.oop.joblink;

import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import it.univaq.disim.oop.joblink.view.ViewException;
import javafx.application.Application;
import javafx.stage.Stage;

public class JobLink extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
			
		try {
			ViewDispatcher viewDispatcher = ViewDispatcher.getInstance();
			viewDispatcher.loginView(stage);
		} catch (ViewException e) {
			e.printStackTrace();
		}
		
	}

}
