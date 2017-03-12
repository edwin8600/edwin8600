package apc.com.supervisioncompraspublicas.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class PopulateCatalogo {

	public static ObservableList<Catalogo> getListaCatalogo() {
		
		ArrayList<Catalogo> catalogos = getCatalogoBaseDeDatos();
		ObservableList<Catalogo> catalogoObservable = FXCollections.observableList(catalogos);
		return catalogoObservable;
	
	}
	
	public static ArrayList<Catalogo> getCatalogoBaseDeDatos() {
		ArrayList<Catalogo> catalogos = new ArrayList<Catalogo>();
		Connection conn = Persistencia.instancia();
		String select = "SELECT * FROM CATALOGO";
		try {
			PreparedStatement sentenciaSQL = conn.prepareStatement(select);
			ResultSet rs = sentenciaSQL.executeQuery();
			while (rs.next()) {
				Catalogo catalogo = new Catalogo(
				rs.getString("ide"),
				rs.getString("descripcion"),
				rs.getString("software"),
				rs.getString("relacion"),
				rs.getDouble("precioReferencial"),
				rs.getString("tipo"),
				rs.getString("licencia"),
				rs.getString("entidad"),
				rs.getDate("fechaCompra"),
				rs.getString("lugar")
				);

				catalogos.add(catalogo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return catalogos;
	}
	
	public static TableColumn<Catalogo, String> getColumnaContrato () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("contrato");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("contrato"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, String> getColumnaDescripcion () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("descripcion");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("descripcion"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, String> getColumnaSoftware () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("software");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("software"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, String> getColumnaRelacion () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("relacion");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("relacion"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, Double> getColumnaCosto () {
		TableColumn<Catalogo, Double> catalogoContrato = new TableColumn<>("costo");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, Double>("precioRefencial"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, String> getColumnaTipo () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("tipo");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("tipo"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, String> getColumnaLicencia () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("licencia");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("licencia"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, String> getColumnaEntidad () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("entidad");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("entidad"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, Date> getColumnaFecha () {
		TableColumn<Catalogo, Date> catalogoContrato = new TableColumn<>("fecha");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, Date>("fechaCompra"));
		return catalogoContrato;
	}
	
	public static TableColumn<Catalogo, String> getColumnaLugar () {
		TableColumn<Catalogo, String> catalogoContrato = new TableColumn<>("lugar");
		catalogoContrato.setCellValueFactory(new PropertyValueFactory<Catalogo, String>("lugar"));
		return catalogoContrato;
	}
}
