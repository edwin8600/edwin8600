package apc.com.supervisioncompraspublicas.entrada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import apc.com.supervisioncompraspublicas.etiquetado.EtiquetadoNER;
import apc.com.supervisioncompraspublicas.persistencia.Persistencia;

public class EntradaDatos {

	public JSONArray archivoDatos(String direccionArchivo) {
		FileReader archivo;
		String informacion = "";
		try {
			archivo = new FileReader(new File(direccionArchivo));
			BufferedReader buffer = new BufferedReader(archivo);
			String aux;
			
			while ((aux = buffer.readLine()) != null) {
				informacion += aux;
			}
			
			archivo.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONArray json = new JSONArray(informacion);
		return json;
	}
	
	public boolean llenarBaseDeDatos(JSONArray datos) {
		Connection conn = Persistencia.instancia();
		String insercion = "INSERT INTO CATALOGO "
				+ "(ide, descripcion, software, relacion, precioReferencial, tipo, licencia, entidad, fechaCompra, lugar) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		try {
			PreparedStatement sentenciaSQL = conn.prepareStatement(insercion);
			JSONObject compra = null;
			EtiquetadoNER etiquetado = new EtiquetadoNER();
			HashMap<String, String> mapaEtiquetas = null;
			String descripcion = "";
			
			for (int i = 0; i < datos.length(); i++) {
				compra = datos.getJSONObject(i);
				
				descripcion =  StringEscapeUtils.unescapeJava(compra.getString("d"));
				mapaEtiquetas = etiquetado.etiquetaOracion(descripcion);
				if (mapaEtiquetas == null) {
					continue;
				}
				sentenciaSQL.setString(1, compra.getString("c"));
				sentenciaSQL.setString(2, descripcion);
				sentenciaSQL.setString(3, mapaEtiquetas.get("software"));
				sentenciaSQL.setString(4, mapaEtiquetas.get("relacion"));				
				sentenciaSQL.setDouble(5, compra.getDouble("p"));
				sentenciaSQL.setString(6, mapaEtiquetas.get("tipo"));
				sentenciaSQL.setString(7, mapaEtiquetas.get("licencia"));
				sentenciaSQL.setString(8, StringEscapeUtils.unescapeJava(compra.getString("r")));
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = formatter.parse(compra.getString("f"));
				sentenciaSQL.setDate(9, new java.sql.Date(date.getTime()));
				
				sentenciaSQL.setString(10, StringEscapeUtils.unescapeJava(compra.getString("s")));
				sentenciaSQL.addBatch();
			}
			
			sentenciaSQL.executeBatch();
			conn.commit();
			Persistencia.cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
}


