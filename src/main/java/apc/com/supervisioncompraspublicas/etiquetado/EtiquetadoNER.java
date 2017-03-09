package apc.com.supervisioncompraspublicas.etiquetado;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class EtiquetadoNER {

	private InputStream modelInNERRelacion;
	private InputStream modelInNERSoftware;
	private InputStream modelInNEROrganizacion;
	private InputStream modelInNERTipo;
	private ArrayList<InputStream> modelos = new ArrayList<InputStream>();
	private ArrayList<Span> etiquetado = new ArrayList<Span>();
	private String tokenPalabras[];


	/**
	 * Encargada de el etiquetada de la oracion
	 */

	public EtiquetadoNER() {

		try {
			//Cargar modelos
			this.modelInNERRelacion = new FileInputStream("/home/luis/Descargas/es-ner-relacion.bin");
			this.modelInNERSoftware = new FileInputStream("/home/luis/Descargas/es-ner-software.bin");
			this.modelInNEROrganizacion = new FileInputStream("/home/luis/Descargas/es-ner-organizacion.bin");
			this.modelInNERTipo = new FileInputStream("/home/luis/Descargas/es-ner-tipo.bin");

			this.modelos.add(this.modelInNERRelacion);
			this.modelos.add(this.modelInNERSoftware);
			this.modelos.add(this.modelInNEROrganizacion);
			this.modelos.add(this.modelInNERTipo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera las etiquetas a traves de los modelos cargados
	 * @param oracionEntrada oracion de entrada
	 * @return genera objeto las partes de la oracion etiquetada
	 */
	public ArrayList<Span> generarEtiquetas(String oracionEntrada) {

		// Tokens
		this.tokenPalabras = oracionEntrada.split(" ");
		TokenNameFinderModel modelNER;

		for (InputStream modelo : this.modelos) {
			try {
				// Carga modelo
				modelNER = new TokenNameFinderModel(modelo);
				NameFinderME nameFinder = new NameFinderME(modelNER);

				// Etiquetas
				Span nameSpans[] = nameFinder.find(this.tokenPalabras);

				for (int i = 0; i < nameSpans.length; i++) {
					this.etiquetado.add(nameSpans[i]);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return this.etiquetado;
	}

	/**
	 * Verifica si existe conflictos en la oracion
	 * @param etiquetado Contiene los objetos con las partes de la oracion etiquetada
	 * @return true si es alguna parte de la oracion se encuentra etiquetada por mas
	 * de una ocasion
	 */
	public boolean conflicto(ArrayList<Span> etiquetado) {

		if (etiquetado.size() > 0) {
			// Caso base
			int inicio = etiquetado.get(0).getStart();
			int fin = etiquetado.get(0).getEnd();

			for (int i = 1; i < etiquetado.size(); i++) {
				if (inicio <= etiquetado.get(i).getStart() 
						&& etiquetado.get(i).getStart() < fin)
					return true;
			}
		} else {
			return true;
		}
		return false;
	}

	/**
	 * Imprime las partes de la oracion con sus respectiva etiquetas
	 * @param oracionEntrada oracion a ser etiquetada
	 */

	public void oracionEtiquetada(String oracionEntrada) {
		if (!conflicto(generarEtiquetas(oracionEntrada))) {

			int inicio, fin;
			String oracion = "";
			String etiqueta = "";

			for (int i = 0; i < this.etiquetado.size(); i++) {
				inicio = this.etiquetado.get(i).getStart();
				fin = this.etiquetado.get(i).getEnd();
				etiqueta = this.etiquetado.get(i).getType();

				for (int j = inicio; j < fin; j++) {
					oracion += tokenPalabras[j];
					if (j != fin-1 ) {
						oracion += " ";
					}
				}

				System.out.println(oracion + "*-*"+ etiqueta +" "+this.etiquetado.get(i).toString());
				oracion = "";
			}
		} else {
			System.out.println("conflicto");
		}
	}

	public HashMap<Integer, String> oracionEtiquetadaHash(String oracionEntrada) {
		HashMap<Integer, String> oracionEtiquetada = new HashMap<Integer, String>();

		if (!conflicto(generarEtiquetas(oracionEntrada))) {

			int inicio, fin;
			String etiqueta;

			for (int i = 0; i < this.etiquetado.size(); i++) {
				inicio = this.etiquetado.get(i).getStart();
				fin = this.etiquetado.get(i).getEnd();
				etiqueta = this.etiquetado.get(i).getType();

				for (int j = inicio; j < fin; j++) {
					oracionEtiquetada.put(j, etiqueta);
				}
			}
		} else {
			System.out.println("conflicto");
			return null;
		}

		return oracionEtiquetada;
	}

}
