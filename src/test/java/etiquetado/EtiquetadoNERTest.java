package etiquetado;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import opennlp.tools.util.Span;
import org.junit.Test;

import apc.com.supervicioncompraspublicas.etiquetado.EtiquetadoNER;

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
		etiquetado.oracionEtiquetada("ADQUISICIÓN DE SOFTWARE DE TELECOMUNICACIONES PARA EL CICTE");
	}
	
	@Test
	public void hashmapOracionEtiquetada() {
		EtiquetadoNER etiquetado = new EtiquetadoNER();
		HashMap<Integer, String> mapaEtiquetas = etiquetado.oracionEtiquetadaHash("ADQUISICIÓN DE SOFTWARE DE TELECOMUNICACIONES PARA EL CICTE");
		assertNotNull(mapaEtiquetas);
	}
}
