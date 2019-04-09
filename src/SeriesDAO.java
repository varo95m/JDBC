import java.util.List;

public interface SeriesDAO {
	
	Serie obtenerSerie(int idSerie);
	
	List<Serie> obtenerSeries();
	
	Serie crearSerie(String nombre, String descripcion, int IDGenero);
	
	boolean eliminarSerie(int idSerie);
	
	int obtenerNumeroDeSeries();
	
	List <Serie> obtenerCatalogoSeries (int index, int count);
	
	List <Serie> buscarSeriesPorNombre(String nombre, int count);
	
	List <Serie> obtenerSeriesPorGenero (int idGenero, int index, int count);
	
	Serie editarSerie (Serie serie);


}
