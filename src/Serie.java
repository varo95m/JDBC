
public class Serie {
	public int idSerie;
	public String nombre;
	public String descrpcion;
	public int idGenero;
	public Serie(int idSerie, String nombre, String descrpcion, int idGenero) {
		super();
		this.idSerie = idSerie;
		this.nombre = nombre;
		this.descrpcion = descrpcion;
		this.idGenero = idGenero;
	}
	
	public Serie(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdSerie() {
		return idSerie;
	}
	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescrpcion() {
		return descrpcion;
	}
	public void setDescrpcion(String descrpcion) {
		this.descrpcion = descrpcion;
	}
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	@Override
	public String toString() {
		return "ID=" + idSerie + " NOMBRE=" + nombre + " DESCRIPCION=" + descrpcion + " IDGENERO="
				+ idGenero;
	}
	
}
