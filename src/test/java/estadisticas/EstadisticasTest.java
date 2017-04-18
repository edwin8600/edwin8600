package estadisticas;

import static org.junit.Assert.*;

import org.junit.Test;

import apc.com.supervisioncompraspublicas.EstadisticasController;
import javafx.scene.chart.BarChart;

public class EstadisticasTest {

	@Test
	public void entidadCantidadSoftware() throws Exception {
		EstadisticasController estadisticas = new EstadisticasController();
		BarChart.Series<String, Integer> serie = estadisticas.generadorEntidadCantidadSoftware();
		assertNotNull(serie);
	}
	
	@Test
	public void entidadCantidadCosto() throws Exception {
		EstadisticasController estadisticas = new EstadisticasController();
		BarChart.Series<String, Double> serie = estadisticas.generadorEntidadCostoSoftware();
		assertNotNull(serie);
	}
	
	@Test
	public void entidadTipoCantidad() throws Exception {
		EstadisticasController estadisticas = new EstadisticasController();
		BarChart.Series<String, Integer> serie = estadisticas.generadorTipoCantidad();
		assertNotNull(serie);
	}
	
	@Test
	public void entidadLugarCantidad() throws Exception {
		EstadisticasController estadisticas = new EstadisticasController();
		BarChart.Series<String, Integer> serie = estadisticas.generadorLugarCantidad();
		assertNotNull(serie);
	}
}
