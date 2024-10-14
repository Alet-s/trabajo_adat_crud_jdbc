package usuarioDAO;

/**@author Alejandro Serrano */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class UsuarioDAO {
	/**
	 * Realiza un nuevo registro en la tabla Usuarios de la base de datos.
	 * 
	 * @param nombre nombre de usuario a registrar.
	 * @param email  email del usuario a registrar.
	 */
	public void crearUsuario(String nombre, String email) throws SQLException {
		String sql = "INSERT INTO usuarios (nombre, email) VALUES (?,?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, nombre);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Consulta todos los datos de la tabla Usuarios.
	 * 
	 * @return el resultSet de la consulta.
	 * @throws SQLException
	 */
	public ResultSet obtenerUsuariosResultSet() throws SQLException {
		String sql = "SELECT * FROM usuarios";
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}

	/**
	 * Actualiza los datos de una tupla correspondiente a un usuario de la tabla
	 * Usuarios.
	 * 
	 * @param id     el número identificador del usuario en el registro que se desea
	 *               cambiar.
	 * @param nombre el nombre ya actualizado del usuario (el nombre que tendrá).
	 * @param email  la dirección de correo actualizada del usuario (el email que
	 *               tendrá).
	 * 
	 * @throws SQLException
	 */
	public void actualizarUsuarios(int id, String nombre, String email) throws SQLException {
		String sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, nombre);
			pstmt.setString(2, email);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Elimina la tupla completa de un usuario identificado por su id.
	 * 
	 * @param id número de id del usuario cuyo registro se desea eliminar.
	 */
	public void eliminarUsuario(int id) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

}
