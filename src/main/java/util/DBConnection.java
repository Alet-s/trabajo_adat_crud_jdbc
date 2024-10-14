package util;

/**@author Alejandro Serrano*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Conexión con la base de datos "ejemplo2_db" */
public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/ejemplo2_db";// Dirección url
	private static final String USER = "root";// Nombrte de usuario de la base de datos
	private static final String PASSWORD = "1234";

	/**
	 * Conexión con los drivers de la base de datos.
	 * 
	 * @return la conexión con la base de datos utilizando la dirección de la BBDD,
	 *         el nombre de usuario del administrador de la misma y su contraseña.
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
