import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetodosSeries {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String propiedades = "src/propiedades.xml";
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
	
	public List<Serie> obtenerSeries() {
		List<Serie> nombreSeries = new ArrayList<Serie>();
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta y obtención de resultados en un
			rs = stmt.executeQuery("SELECT IDSerie, Nombre, Descripcion, IDGenero FROM Series");
			while(rs.next()) {
				serie = new Serie(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
				nombreSeries.add(serie);
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		} finally {
			liberarRecursos();
		}
		return nombreSeries;
	}
	
	public Serie crearSerie(String nombre, String descripcion, int IDGenero) {
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
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

	public boolean eliminarSerie(int idSerie) {
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

	public int obtenerNumeroDeSeries() {
		int contador = 0;
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			rs = stmt.executeQuery("SELECT IDSerie FROM Series");
			while(rs.next()) {
				contador++;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			liberarRecursos();
		}
		return contador;
	}

	public List <Serie> obtenerCatalogoSeries (int index, int count){
		List<Serie> nombreSeries = new ArrayList<Serie>();
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			rs = stmt.executeQuery("SELECT Nombre FROM Series LIMIT " + index + "," + count);
			while(rs.next()) {
				serie = new Serie(rs.getString(1));
				nombreSeries.add(serie);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			liberarRecursos();
		}
		return nombreSeries;
	}

	public List <Serie> buscarSeriesPorNombre (String nombre, int count){
		List<Serie> nombreSeries = new ArrayList<Serie>();
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			rs = stmt.executeQuery("SELECT Nombre FROM Series WHERE Nombre LIKE '%"+nombre+"%' LIMIT " + count);
			while(rs.next()) {
				serie = new Serie(rs.getString(1));
				nombreSeries.add(serie);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			liberarRecursos();
		}
		return nombreSeries;
	}

	public List <Serie> obtenerSeriesPorGenero (double idGenero, int index, int count){
		List<Serie> nombreSeries = new ArrayList<Serie>();
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			rs = stmt.executeQuery("SELECT Nombre FROM Series WHERE idGenero = "+idGenero+" LIMIT " + index + "," + count);
			while(rs.next()) {
				serie = new Serie(rs.getString(1));
				nombreSeries.add(serie);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			liberarRecursos();
		}
		return nombreSeries;
	}

	public Serie editarSerie (Serie serie) {
		Serie serieEditada = null;
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		String nombre, descripcion;
		int idGenero;
		try {
			// Realizamos la conexion
			ConexionBD conexion = new ConexionBD(propiedades);
			con = conexion.getConnection();
			System.out.println("Introduzca nuevo nombre: ");
			nombre = teclado.nextLine();
			System.out.println("Introduzca nueva descripcion: ");
			descripcion = teclado.nextLine();
			System.out.println("Introduzca nuevo Genero");
			idGenero = teclado.nextInt();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta
			stmt.execute("UPDATE Series SET Nombre='"+nombre+"', Descripcion='"+descripcion+"', IDGenero="+idGenero +" WHERE IDSerie="+serie.getIdSerie());
			serieEditada = obtenerSerie(serie.getIdSerie());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			liberarRecursos();
		}
		return serieEditada;
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
