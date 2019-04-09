
public class Genero {
	int idGenero;
	String nombreGenero;
	String descripcionGenero;

	public Genero(int idGenero, String nombreGenero, String descripcionGenero) {
		super();
		this.idGenero = idGenero;
		this.nombreGenero = nombreGenero;
		this.descripcionGenero = descripcionGenero;
	}

	public Genero() {
		// TODO Auto-generated constructor stub
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}
	
	@Override
	public String toString() {
		return "Genero [idGenero=" + idGenero + ", nombreGenero=" + nombreGenero + ", descripcionGenero="
				+ descripcionGenero + "]";
	}

}
