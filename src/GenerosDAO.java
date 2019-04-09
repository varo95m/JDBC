import java.util.List;

public interface GenerosDAO {
	
	Genero obtenerGenero(int idGenero);

	Genero editarGenero(Genero generoPelicula);

	List<Genero> obtenerGeneros();
	
	Genero crearGenero(String nombreGenero, String descripcionGenero);

	Boolean eliminarGenero(int idGenero);

}
