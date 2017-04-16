package apc.com.supervisioncompraspublicas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import apc.com.supervisioncompraspublicas.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 * Despliega los graficos de barras
 * @author luis
 *
 */

public class EstadisticasController {

	@FXML private BarChart<String, Integer> entidadCantidad;
	@FXML private BarChart<String, Double> entidadCosto;
	@FXML private BarChart<String, Integer> tipoCantidad;
	@FXML private BarChart<String, Integer> lugarCantidad;
	
    /**
     * Carga datos en la grafica de barras
     */
	
	@FXML
	public void initialize() {
		entidadCantidad.getData().add(generadorEntidadCantidadSoftware());
		entidadCosto.getData().add(generadorEntidadCostoSoftware());
		tipoCantidad.getData().add(generadorTipoCantidad());
		lugarCantidad.getData().add(generadorLugarCantidad());
	}
	
	/**
	 * Genera la serie para entidad que adquiere software y la cantidad
	 * de software adquirido
	 * @return serie para ser usada en una grafica
	 */
	
	public Series<String, Integer> generadorEntidadCantidadSoftware() {
		
		BarChart.Series<String, Integer> series = new BarChart.Series<>();
		Connection conn = Persistencia.instancia();
		String consulta = "SELECT entidad, count(*) AS contador "
				+ "FROM catalogo "
				+ "GROUP BY entidad "
				+ "ORDER BY CONTADOR DESC LIMIT 10";

		PreparedStatement sentenciaSQL;
		ResultSet rs;

		try {
			sentenciaSQL = conn.prepareStatement(consulta);
			rs = sentenciaSQL.executeQuery();
			
			while(rs.next()) {
				series.getData().add(new BarChart.Data<String, Integer>(rs.getString("entidad").substring(0, 15), rs.getInt("contador")));
				System.out.println(rs.getString("entidad"));
				System.out.println(rs.getInt("contador"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Persistencia.cerrarConexion();
		
		return series;
	}

	/**
	 * Genera la serie para entidad que adquiere software y el costo total
	 * de software adquirido
	 * @return serie para ser usada en una grafica
	 */
	
	public Series<String, Double> generadorEntidadCostoSoftware() {
		BarChart.Series<String, Double> series = new BarChart.Series<>();
		Connection conn = Persistencia.instancia();
		String consulta = "SELECT entidad, sum(PRECIOREFERENCIAL) AS costo "
				+ "FROM catalogo "
				+ "GROUP BY entidad "
				+ "ORDER BY costo DESC LIMIT 10";

		PreparedStatement sentenciaSQL;
		ResultSet rs;

		try {
			sentenciaSQL = conn.prepareStatement(consulta);
			rs = sentenciaSQL.executeQuery();
			
			while(rs.next()) {
				series.getData().add(new BarChart.Data<String, Double>(rs.getString("entidad").substring(0, 15), rs.getDouble("costo")));
				System.out.println(rs.getString("entidad"));
				System.out.println(rs.getDouble("costo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Persistencia.cerrarConexion();
		
		return series;
	}
	
	/**
	 * Genera la serie para el tipo de software y la cantidad
	 * de software adquirido
	 * @return serie para ser usada en una grafica
	 */

	public Series<String, Integer> generadorTipoCantidad() {
		BarChart.Series<String, Integer> series = new BarChart.Series<>();
		Connection conn = Persistencia.instancia();
		String consulta = "SELECT tipo, count(*) AS contador "
				+ "FROM catalogo "
				+ "WHERE tipo IS NOT NULL "
				+ "GROUP BY tipo "
				+ "ORDER BY contador DESC LIMIT 10";

		PreparedStatement sentenciaSQL;
		ResultSet rs;

		try {
			sentenciaSQL = conn.prepareStatement(consulta);
			rs = sentenciaSQL.executeQuery();
			
			while(rs.next()) {
				series.getData().add(new BarChart.Data<String, Integer>(rs.getString("tipo"), rs.getInt("contador")));
				System.out.println(rs.getString("tipo"));
				System.out.println(rs.getInt("contador"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Persistencia.cerrarConexion();
		
		return series;
	}

	/**
	 * Genera la serie para el lugar en que se adquiere software y la cantidad
	 * de software adquirido
	 * @return serie para ser usada en una grafica
	 */
	
	public Series<String, Integer> generadorLugarCantidad() {
		BarChart.Series<String, Integer> series = new BarChart.Series<>();
		Connection conn = Persistencia.instancia();
		String consulta = "SELECT lugar, count(*) AS contador "
				+ "FROM catalogo "
				+ "GROUP BY lugar "
				+ "ORDER BY contador DESC LIMIT 10";

		PreparedStatement sentenciaSQL;
		ResultSet rs;

		try {
			sentenciaSQL = conn.prepareStatement(consulta);
			rs = sentenciaSQL.executeQuery();
			
			while(rs.next()) {
				series.getData().add(new BarChart.Data<String, Integer>(rs.getString("lugar"), rs.getInt("contador")));
				System.out.println(rs.getString("lugar"));
				System.out.println(rs.getInt("contador"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Persistencia.cerrarConexion();
		
		return series;
	}
}
