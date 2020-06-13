package it.univaq.disim.oop.joblink;

import it.univaq.disim.oop.joblink.view.ViewDispatcher;
import it.univaq.disim.oop.joblink.view.ViewException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JobLink extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/viste/login.fxml"));
//		Parent login = loader.load();
//		Scene scene = new Scene(login);
//        stage.setScene(scene);
//        stage.show();
		
		try {
			ViewDispatcher viewDispatcher = ViewDispatcher.getInstance();
			viewDispatcher.loginView(stage);
		} catch (ViewException e) {
			e.printStackTrace();
		}
		
	}

}
