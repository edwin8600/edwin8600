package etiquetado;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import opennlp.tools.util.Span;
import org.junit.Test;

import apc.com.supervisioncompraspublicas.etiquetado.EtiquetadoNER;

public class EtiquetadoNERTest {

	@Test
	public void generarEtiquetado() {
		EtiquetadoNER etiquetado = new EtiquetadoNER();
		ArrayList<Span> etiquetas = etiquetado.generarEtiquetas("ADQUISICION DE LICENCIAS DE SOFTWARE UTILITARIOS PARA CONECUAKOR");
		assertTrue(etiquetas.size() > 0);
	}
	
	@Test
	public void verificarConflicto() {
		EtiquetadoNER etiquetado = new EtiquetadoNER();
		ArrayList<Span> etiquetas = etiquetado.generarEtiquetas("ADQUISICION DE LICENCIAS DE SOFTWARE UTILITARIOS PARA CONECUAKOR");
		assertTrue(etiquetado.conflicto(etiquetas));
	}
	
	@Test
	public void verificarSinConflicto() {
		EtiquetadoNER etiquetado = new EtiquetadoNER();
		ArrayList<Span> etiquetas = etiquetado.generarEtiquetas("ADQUISICIÓN DE SOFTWARE DE TELECOMUNICACIONES PARA EL CICTE");
		assertFalse(etiquetado.conflicto(etiquetas));
	}
	
	@Test
	public void oracionEtiquetada() {
		EtiquetadoNER etiquetado = new EtiquetadoNER();
		HashMap<String, String> mapaEtiquetas = etiquetado.etiquetaOracion("CONTRATACI\u00d3N DEL SERVICIO DE ACTUALIZACI\u00d3N DE HARDWARE Y SOFTWARE DEL MODULO DE ELECCIONES DEL SISTEMA DE MANDO Y CONTROL DEL CC.FF.AA.");
		assertNotNull(mapaEtiquetas);
	}
	
	@Test
	public void hashmapOracionEtiquetada() {
		EtiquetadoNER etiquetado = new EtiquetadoNER();
		HashMap<Integer, String> mapaEtiquetas = etiquetado.posicionEtiqueta("ADQUISICIÓN DE SOFTWARE DE TELECOMUNICACIONES PARA EL CICTE");
		assertNotNull(mapaEtiquetas);
	}
}
