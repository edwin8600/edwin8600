package apc.com.supervisioncompraspublicas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import apc.com.supervisioncompraspublicas.persistencia.Catalogo;
import apc.com.supervisioncompraspublicas.persistencia.Persistencia;
import apc.com.supervisioncompraspublicas.persistencia.PopulateCatalogo;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

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
