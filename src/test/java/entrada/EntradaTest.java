package entrada;

import org.junit.Test;

import apc.com.supervisioncompraspublicas.entrada.EntradaDatos;

import static org.junit.Assert.*;

import org.json.JSONArray;

public class EntradaTest {

	@Test
	public void leerArchivo() {
		EntradaDatos entrada = new EntradaDatos();
		JSONArray datos = entrada.archivoDatos("/home/luis/Descargas/datos.json");
		assertNotNull(datos);
	}
	
	@Test
	public void llenarBaseDeDatos() {
		EntradaDatos entrada = new EntradaDatos();
		JSONArray datos = entrada.archivoDatos("/home/luis/Descargas/datos.json");
		boolean estado = entrada.llenarBaseDeDatos(datos);
		assertTrue(estado);
	}
}
