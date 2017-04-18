package entrenamiento;
import static org.junit.Assert.*;

import org.junit.Test;

import apc.com.supervisioncompraspublicas.entrenamiento.EntrenamientoNER;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.TrainingParameters;

public class NerTest {

	@Test
	public void leerDatosDeEntranamiento() {
		EntrenamientoNER entrenamiento = new EntrenamientoNER();
		InputStreamFactory input = entrenamiento.datosEntrada("/home/luis/Descargas/es-ner-relacion2.train");
		assertNotNull(input);
	}
	
	@Test
	public void entranarModelo() {
		EntrenamientoNER entrenamiento = new EntrenamientoNER();
		InputStreamFactory input = entrenamiento.datosEntrada("/home/luis/Descargas/es-ner-relacion2.train");
		
		TokenNameFinderModel modelo = entrenamiento.entrenarModelo("es", "relacion", input, TrainingParameters.defaultParams(),
				new TokenNameFinderFactory());
		
		assertNotNull(modelo);
	}

	@Test
	public void generarModelo() {
		EntrenamientoNER entrenamiento = new EntrenamientoNER();
		InputStreamFactory input = entrenamiento.datosEntrada("/home/luis/Descargas/es-ner-relacion2.train");
		TokenNameFinderModel modelo = entrenamiento.entrenarModelo("es", "relacion", input, TrainingParameters.defaultParams(),
				new TokenNameFinderFactory());
		Boolean modelOut = entrenamiento.generarModelo(modelo, "/home/luis/Descargas/es-ner-relacion.bin");
		
		assertTrue(modelOut);
	}
	
	@Test
	public void entrenamiento() {
		EntrenamientoNER entrenamiento = new EntrenamientoNER();
		assertTrue(entrenamiento.entrenar());
	}
}
