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

public class MainController {

	@FXML private BorderPane panel;
	private String rutaArchivo;
	private FXMLLoader fxmlLoader;
	
	public MainController() {
		this.fxmlLoader = new FXMLLoader();
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
		
	}

	@FXML 
	void menuEstadisticas(ActionEvent event) {

	}

	@FXML 
	void menuEtiquetado(ActionEvent event) {
		try {
			Parent p = this.fxmlLoader.load(getClass().getResourceAsStream("/fxml/Etiquetado.fxml"));
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