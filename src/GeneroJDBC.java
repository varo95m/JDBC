import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneroJDBC implements GenerosDAO{
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String propiedades = "src/propiedades.xml";
	ConexionBD conexion = new ConexionBD(propiedades);
	Genero genero = null;
		
	@Override
	public Genero obtenerGenero(int idGenero) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta y obtención de resultados en un
			rs = stmt.executeQuery("SELECT * FROM Generos WHERE IDGenero = " + idGenero);
			if(rs.next()) {
				genero = new Genero(rs.getInt(1),rs.getString(2), rs.getString(3));
			}else {
				System.out.println("No hay genero con ese id");
				return genero;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return genero;
	}
	
	public Genero obtenerGeneroPorNombre(String nombreGenero) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta y obtención de resultados en un
			rs = stmt.executeQuery("SELECT * FROM Generos WHERE Nombre = '" + nombreGenero + "'");
			if(rs.next()) {
				genero = new Genero(rs.getInt(1),rs.getString(2), rs.getString(3));
			}else {
				System.out.println("No hay genero con ese nombre");
				return genero;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return genero;
	}

	@Override
	public Genero editarGenero(Genero generoPelicula) {
		String nombre, descripcion;
		Scanner teclado = new Scanner(System.in);
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			System.out.println("Introduzca nuevo nombre: ");
			nombre = teclado.nextLine();
			System.out.println("Introduzca nueva descripcion: ");
			descripcion = teclado.nextLine();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			stmt.execute("UPDATE generos SET Nombre='"+nombre+"', Descripcion='"+descripcion+"' WHERE IDGenero="+generoPelicula.getIdGenero());
			generoPelicula = obtenerGenero(generoPelicula.getIdGenero());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return generoPelicula;
	}

	@Override
	public List<Genero> obtenerGeneros() {
		List<Genero> generos = new ArrayList<Genero>();
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta y obtención de resultados en un
			rs = stmt.executeQuery("SELECT * FROM Generos");
			while(rs.next()) {
				genero = new Genero(rs.getInt(1),rs.getString(2), "hola");
				generos.add(genero);
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return generos;
	}

	@Override
	public Genero crearGenero(String nombreGenero, String descripcionGenero) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			stmt.execute("INSERT INTO Generos(Nombre,Descripcion) VALUES('" + nombreGenero + "' ,'" + descripcionGenero + "')");	
			rs = stmt.executeQuery("SELECT * FROM Generos ORDER BY IDGenero DESC limit 1");
			rs.next();
			genero = new Genero(rs.getInt(1),rs.getString(2), rs.getString(3));
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return genero;
	}

	@Override
	public Boolean eliminarGenero(int idGenero) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			stmt.executeUpdate("DELETE FROM generos WHERE IDGenero=" + idGenero);
		} catch (SQLException sqle) {
			System.out.println(sqle);
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
