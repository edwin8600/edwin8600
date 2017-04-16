package apc.com.supervisioncompraspublicas;

import apc.com.supervisioncompraspublicas.persistencia.Catalogo;
import apc.com.supervisioncompraspublicas.persistencia.PopulateCatalogo;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * Carga de informacion y visualizacion en la tabla
 * 
 * @author luis
 *
 */

public class CatalogoController {

	@FXML private TableView<Catalogo> tablaCatalogo;

	@FXML
	public void initialize() {

		this.tablaCatalogo.setItems(PopulateCatalogo.getListaCatalogo());
		this.tablaCatalogo.getColumns().addAll(
				PopulateCatalogo.getColumnaContrato(),
				PopulateCatalogo.getColumnaDescripcion(),
				PopulateCatalogo.getColumnaSoftware(),
				PopulateCatalogo.getColumnaRelacion(),
				PopulateCatalogo.getColumnaCosto(),
				PopulateCatalogo.getColumnaTipo(),
				PopulateCatalogo.getColumnaLicencia(),
				PopulateCatalogo.getColumnaEntidad(),
				PopulateCatalogo.getColumnaFecha(),
				PopulateCatalogo.getColumnaLugar());
	}
}
