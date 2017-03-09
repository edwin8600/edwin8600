package apc.com.supervicioncompraspublicas.entrenamiento;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class EntrenamientoNER {

	private InputStreamFactory input;
	private TokenNameFinderModel modelo;
	
	/**
	 * Entrenamiento para etiquetado NER
	 */
	
	public EntrenamientoNER() {

	}

	/**
	 * Lectura de los datos de entrada
	 * 
	 * @param ruta ruta de los datos de entrada
	 * @return datos de entrada a ser procesados
	 */
	
	public InputStreamFactory datosEntrada(final String ruta) {
		InputStreamFactory entrada = new InputStreamFactory() {

			public InputStream createInputStream() throws IOException {
				InputStream modelInNER = new FileInputStream(ruta);
				return modelInNER;
			}
		};
		return entrada;
	}

	/**
	 * Entrena el modelo
	 * 
	 * @param idioma etiqueta de idioma
	 * @param etiqueta nombre de la etiqueta
	 * @param entrada datos de entrada
	 * @param parametros parametros de configuracion
	 * @param tokenNameFinderFactory buscador de tokens
	 * @return
	 */
	
	public TokenNameFinderModel entrenarModelo(String idioma, String etiqueta, InputStreamFactory entrada,
			TrainingParameters parametros, TokenNameFinderFactory tokenNameFinderFactory) {
		
		Charset charset = Charset.forName("UTF-8");
		ObjectStream<String> lineStream;
		TokenNameFinderModel model = null;
		ObjectStream<NameSample> sampleStream;
		
		try {
			lineStream = new PlainTextByLineStream(entrada, charset);
			sampleStream = new NameSampleDataStream(lineStream);
			model = NameFinderME.train(idioma, etiqueta, sampleStream, parametros, tokenNameFinderFactory);
			sampleStream.close();
			return model;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return model;
	}

	/**
	 * Genera el modelo archivo bin
	 * 
	 * @param modelo modelo entrenado
	 * @param ruta ruta y nombre del modelo
	 * @return Verdadero si el modelo fue creado con existo sino falso
	 */
	
	public Boolean generarModelo(TokenNameFinderModel modelo, String ruta) {
		
		OutputStream modelOut = null;
		try {
			modelOut = new BufferedOutputStream(new FileOutputStream(ruta));
			modelo.serialize(modelOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelOut != null) {
				try {
					modelOut.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}      
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * Entenamiento del modelo
	 * 
	 * @return true si el modelo fue creado con exito sino false
	 */
	
	public boolean entrenar() {
		
		this.input = datosEntrada("/home/luis/Descargas/es-ner-software2.train");
		this.modelo = entrenarModelo("es", "software", input, TrainingParameters.defaultParams(),
				new TokenNameFinderFactory());
		boolean modelOut = generarModelo(modelo, "/home/luis/Descargas/es-ner-software.bin");
		return modelOut;
	}
}
