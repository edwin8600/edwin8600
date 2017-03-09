package apc.com.supervicioncompraspublicas;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import apc.com.supervicioncompraspublicas.etiquetado.EtiquetadoNER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class EtiquetadoController
{
	private static final Logger log = LoggerFactory.getLogger(EtiquetadoController.class);

	@FXML private TextFlow display;
	@FXML private Button evaluar;
	@FXML private TextField textoEvaluar;

	@FXML
	void evaluar(ActionEvent event) {

		// Limpieza
		if (textoEvaluar.getText().length() > 0) {

			display.getChildren().clear();

			String tokenPalabras[] = textoEvaluar.getText().split(" ");
			String etiqueta;
			Text texto;

			// Etiquetado
			log.info("Inicio etiquetado");
			EtiquetadoNER etiquetado = new EtiquetadoNER();
			HashMap<Integer, String> mapaEtiquetas = etiquetado.oracionEtiquetadaHash(textoEvaluar.getText());

			// Existe etiqueta
			if (mapaEtiquetas != null) {
				for (int key = 0; key < tokenPalabras.length; key++) {
					etiqueta = mapaEtiquetas.get(key);
					texto = new Text(tokenPalabras[key]);

					if (etiqueta != null) {
						System.out.println(etiqueta);
						if (etiqueta.contentEquals("relacion")) {
							texto.setFill(Color.BLUE);
							texto.setFont(Font.font(20));
 							Tooltip.install(texto, new Tooltip("relacion"));
 							
						} else if (etiqueta.contentEquals("organizacion")) {
							texto.setFill(Color.BLUEVIOLET);
							texto.setFont(Font.font(20));
							Tooltip.install(texto, new Tooltip("organizacion"));
							
						} else if (etiqueta.contentEquals("software")) {
							texto.setFill(Color.BROWN);
							texto.setFont(Font.font(20));
							Tooltip.install(texto, new Tooltip("software"));
							
						} else if (etiqueta.contentEquals("tipo")) {
							texto.setFill(Color.CHOCOLATE);
							texto.setFont(Font.font(20));
							Tooltip.install(texto, new Tooltip("tipo"));
						}
					}

					if (key == tokenPalabras.length-1) {
						display.getChildren().add(texto);

					} else {	
						display.getChildren().add(texto);
						display.getChildren().add(new Text(" "));
					}

				}
			}

		} else {
			System.out.println("Vacio");
		}

	}
}
