package persistencia;

import org.junit.Test;

import apc.com.supervisioncompraspublicas.persistencia.Catalogo;
import apc.com.supervisioncompraspublicas.persistencia.PopulateCatalogo;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class PersistenciaTest {

	@Test
	public void generaCatalogoDesdeBaseDeDatos() {
		PopulateCatalogo catalogoController = new PopulateCatalogo();
		ArrayList<Catalogo> catalogos = catalogoController.getCatalogoBaseDeDatos();
		assertTrue(catalogos.size() > 0);
	}
}
