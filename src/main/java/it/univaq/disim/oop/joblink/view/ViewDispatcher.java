package it.univaq.disim.oop.joblink.view;

import java.io.IOException;

import it.univaq.disim.oop.joblink.controller.DataInitializable;
import it.univaq.disim.oop.joblink.domain.Utente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewDispatcher {

	private static final String FXML_SUFFIX = ".fxml";
	private static final String RESOURCE_BASE = "/viste/";

	private static ViewDispatcher instance = new ViewDispatcher();

	private Stage stage;
	private BorderPane layout;

	private ViewDispatcher() {
	}

	public static ViewDispatcher getInstance() {
		return instance;
	}

	public void loginView(Stage stage) throws ViewException {
		this.stage = stage;
		Parent loginView = loadView("login").getView();
		Scene scene = new Scene(loginView);
		stage.setScene(scene);
		stage.show();
	}

	public void loggedIn(Utente utente) {
		try {
			View<Utente> layoutView = loadView("layout");
			DataInitializable<Utente> layoutController = layoutView.getController();
			layoutController.initializeData(utente);
			layout = (BorderPane) layoutView.getView();
			renderView("home", utente);
			Scene scene = new Scene(layout);
			scene.getStylesheets().add(getClass().getResource(RESOURCE_BASE + "styles.css").toExternalForm());
			stage.setScene(scene);			
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public void logout() {
		try {
			Parent loginView = loadView("login").getView();
			Scene scene = new Scene(loginView);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}
	
	public void registraView(String viewToLoad) throws ViewException {
		try {
			Parent registraView = loadView(viewToLoad).getView();
			Scene scene = new Scene(registraView);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}

	public <T> void renderView(String viewName, T data) {
		try {
			View<T> view = loadView(viewName);
			DataInitializable<T> controller = view.getController();
			controller.initializeData(data);
			layout.setCenter(view.getView());
		} catch (ViewException e) {
			renderError(e);
		}
	}
	
	public void renderError(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}

	private <T> View<T> loadView(String viewName) throws ViewException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(RESOURCE_BASE + viewName + FXML_SUFFIX));
			Parent parent = (Parent) loader.load();
			return new View<>(parent, loader.getController());

		} catch (IOException ex) {
			throw new ViewException(ex);
		}
	}

}
