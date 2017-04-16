package apc.com.supervisioncompraspublicas;

import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 * Eventos que cargan las vistas cuando se da clic
 * en los diferentes elementos
 * 
 * @author luis
 */

public class MainController {

	@FXML private BorderPane panel;
	private String rutaArchivo;
	
	public MainController() {
		
	}
	
	@FXML 
	void menuAbrir(ActionEvent event) {
		FileChooser file = new FileChooser();
		file.setTitle("Seleccionar fuente de datos");
		File archivo = file.showOpenDialog(null);
		if (archivo != null) {
			this.rutaArchivo = archivo.getPath();
		}
	}

	@FXML 
	void menuCatalogo(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent p = fxmlLoader.load(getClass().getResourceAsStream("/fxml/Catalogo.fxml"));
			this.panel.setCenter(p);   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML 
	void menuEstadisticas(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent p = fxmlLoader.load(getClass().getResourceAsStream("/fxml/Estadisticas.fxml"));
			this.panel.setCenter(p);   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML 
	void menuEtiquetado(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent p = fxmlLoader.load(getClass().getResourceAsStream("/fxml/Etiquetado.fxml"));
			this.panel.setCenter(p);   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML 
	void menuSalir(ActionEvent event) {
		Platform.exit();
	}

}