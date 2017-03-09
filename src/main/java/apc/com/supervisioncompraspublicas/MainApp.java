package apc.com.supervisioncompraspublicas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static final Logger log = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) throws Exception {

		// Entrenamiento
		//EntrenamientoNER entrenamientoNER = new EntrenamientoNER();
		//entrenamientoNER.entrenar();
		
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		log.info("Supervision compras públicas");

		String fxmlFile = "/fxml/Etiquetado.fxml";
		log.debug("Cargado FXML vista principal desde: {}", fxmlFile);
		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

		log.debug("Mostrando JFX scene");
		Scene scene = new Scene(rootNode);
		scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle("Supervision compras públicas");
		stage.setScene(scene);
		stage.show();
	}
}
