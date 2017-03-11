package apc.com.supervisioncompraspublicas.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Persistencia {

	static Connection conn = null;
	public Persistencia() {

	}
	
	public static Connection instancia () {
		
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db/catalogo");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void cerrarConexion () {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
