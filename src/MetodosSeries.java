import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodosSeries {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String propiedades = "D:\\Programacion\\JDBC\\src\\propiedades.xml";
	ConexionBD conexion = new ConexionBD(propiedades);
	Serie serie = null;
	public Serie obtenerSerie(int idSerie) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta y obtención de resultados en un
			rs = stmt.executeQuery("SELECT IDSerie, Nombre, Descripcion, IDGenero FROM Series WHERE IDSerie = " + idSerie);
			if(rs.next()) {
				serie = new Serie(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
			}else {
				System.out.println("No hay series con ese id");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return serie;
	}

	public Serie crearSerie(String nombre, String descripcion, int IDGenero) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD("/datos/usuarios/alumnos/alvaro.monterocarmena/Escritorio/Programacion/JDBC/src/propiedades.xml");
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			stmt.execute("INSERT INTO Series(Nombre,Descripcion,IDGenero) VALUES('" + nombre + "' ,'" + descripcion + "' ," + IDGenero +")");	
			rs = stmt.executeQuery("SELECT * FROM Series ORDER BY IDSerie DESC limit 1");
			rs.next();
			serie = new Serie(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return serie;
	}
	
	public boolean borrarSerie(int idSerie) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			stmt.executeQuery("DELETE FROM series WHERE IDSerie="+ idSerie);
		} catch (SQLException sqle) {
			return false;
		} finally {
			liberarRecursos();
		}
		return true;
	}

	public void liberarRecursos() {
		try {
			// Liberamos todos los recursos pase lo que pase
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				ConexionBD.closeConnection(con);
			}
		} catch (SQLException sqle) {
		}
	}
}
