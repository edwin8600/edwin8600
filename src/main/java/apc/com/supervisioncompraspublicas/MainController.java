package apc.com.supervisioncompraspublicas;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import java.io.File;

public class MainController {

	@FXML private BorderPane panel;
	private String rutaArchivo;
	
	@FXML 
	void menuAbrir(ActionEvent event) {
		FileChooser file = new FileChooser();
		file.setTitle("Seleccionar fuente de datos");
		File archivo = file.showOpenDialog(null);
		this.rutaArchivo = archivo.getPath();
	}

	@FXML 
	void menuCatalogo(ActionEvent event) {
		
	}

	@FXML 
	void menuEstadisticas(ActionEvent event) {

	}

	@FXML 
	void menuEtiquetado(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent p = fxmlLoader.load(getClass().getResourceAsStream("/fxml/Etiquetado.fxml"));
			panel.setCenter(p);   
		} catch (IOException e) {
		} 
	}

	@FXML 
	void menuSalir(ActionEvent event) {

	}

}