import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		int opcionUno=0, opcionDos=0, idSerie, idGenero, cantidad, index;
		String opcion, nombre, descripcion;
		SerieJDBC series = new SerieJDBC();
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		Serie serie, serieEditada;
		GeneroJDBC gen = new GeneroJDBC();
		Genero genero = new Genero();
		List<Serie> numeroSeries = new ArrayList<Serie>();
		List<Genero> generos = new ArrayList<Genero>();
		while(opcionUno!=3) {
			System.out.println("Con que desea trabajar:\n 1. Series.\n 2. Generos.\n 3. Salir.");
			opcionUno = teclado.nextInt();
			if(opcionUno==1) {
				while(opcionDos!=10) {
					System.out.println("Que desea hacer: \n 1. Obtener serie mediante id. \n 2. Crear serie.\n 3. Borrar serie.\n "
							+ "4. Mostrar total series.\n 5. Mostrar series por nombre.\n 6. Mostrar series por genero."
							+ "\n 7. Mostrar catálogo de series. \n 10. Volver");
					opcionDos=teclado.nextInt();
					switch (opcionDos) {
					case 1:
						System.out.print("Introduzca id de la serie: ");
						idSerie = teclado.nextInt();
						serie = series.obtenerSerie(idSerie);
						if(serie!=null) {
							System.out.println(serie.toString());
						}
						break;
					case 2:
						System.out.println("Introduzca nombre: ");
						teclado.nextLine();
						nombre = teclado.nextLine();
						System.out.println("Introduzca descripcion: ");
						descripcion = teclado.nextLine();
						System.out.println("Introduzca IDGenero: ");
						idGenero = teclado.nextInt();
						serie = series.crearSerie(nombre, descripcion, idGenero);
						System.out.println(serie.toString());
						break;
					case 3:
						System.out.print("Introduzca id de la serie: ");
						idSerie = teclado.nextInt();
						System.out.println(series.eliminarSerie(idSerie));
						break;
					case 4:
						System.out.println("Numero de series en la base de datos: " + series.obtenerNumeroDeSeries());
						break;
					case 5:
						System.out.println("Introduzca cadena: ");
						teclado.nextLine();
						nombre = teclado.nextLine();
						System.out.println("Introduzca cantidad: ");
						cantidad = teclado.nextInt();
						numeroSeries = series.buscarSeriesPorNombre(nombre, cantidad);
						for(int i = 0; i<numeroSeries.size(); i++) {
							System.out.println(numeroSeries.get(i).getNombre());
						}
						break;
					case 6:
						index = 0;
						System.out.println("Introduzca nombre del genero: ");
						teclado.nextLine();
						String nombreGenero = teclado.nextLine();
						genero = gen.obtenerGeneroPorNombre(nombreGenero);
						if(genero != null) {
						numeroSeries = series.obtenerSeriesPorGenero(genero.getIdGenero(), index, 5);
						for(int i = 0; i<numeroSeries.size(); i++) {
							System.out.println(numeroSeries.get(i).getNombre());
						}
						System.out.println("Introduzca a(anterior) d(siguiente) s(salir) ");
						teclado.nextLine();
						opcion = teclado.nextLine();
						while(!opcion.equals("s")) {
							if (opcion.equals("a")) {
								if(index!=0) {
									index-=5;
								}
								numeroSeries = series.obtenerSeriesPorGenero(genero.getIdGenero(), index, 5);
								for(int i = 0; i<numeroSeries.size(); i++) {
									System.out.println(numeroSeries.get(i).getNombre());
								}
							}else if(opcion.equals("d")) {
								index+=5;
								numeroSeries = series.obtenerSeriesPorGenero(genero.getIdGenero(), index, 5);
								for(int i = 0; i<numeroSeries.size(); i++) {
									System.out.println(numeroSeries.get(i).getNombre());
								}
							}else {
								System.out.println("No es una opcion valida");
							}
							System.out.println("Introduzca a(anterior) d(siguiente) s(salir) ");
							opcion = teclado.nextLine();
						}
						}
						break;
					case 7:
						index = 0;
						System.out.println("Introduzca cantidad de series por pagina: ");
						cantidad = teclado.nextInt();
						numeroSeries = series.obtenerCatalogoSeries(index, cantidad);
						for(int i = 0; i<numeroSeries.size(); i++) {
							System.out.println(numeroSeries.get(i).getNombre());
						}
						System.out.println("Introduzca a(anterior) d(siguiente) s(salir) ");
						teclado.nextLine();
						opcion = teclado.nextLine();
						while(!opcion.equals("s")) {
							if (opcion.equals("a")) {
								if(index!=0) {
									index-=cantidad;
								}
								numeroSeries = series.obtenerCatalogoSeries(index, cantidad);
								for(int i = 0; i<numeroSeries.size(); i++) {
									System.out.println(numeroSeries.get(i).getNombre());
								}
							}else if(opcion.equals("d")) {
								index+=cantidad;
								numeroSeries = series.obtenerCatalogoSeries(index, cantidad);
								for(int i = 0; i<numeroSeries.size(); i++) {
									System.out.println(numeroSeries.get(i).getNombre());
								}
							}else {
								System.out.println("No es una opcion valida");
							}
							System.out.println("Introduzca a(anterior) d(siguiente) s(salir) ");
							opcion = teclado.nextLine();
						}
						break;
					case 8:
						numeroSeries = series.obtenerSeries();
						for(int i = 0; i<numeroSeries.size(); i++) {
							System.out.println(numeroSeries.get(i).toString());
						}
						System.out.println("Introduzca id de la serie a editar: ");
						idSerie = teclado.nextInt();
						serie = series.obtenerSerie(idSerie);
						serieEditada = series.editarSerie(serie);
						System.out.println(serieEditada.toString());
						break;
					default:
						break;
					}
				}
			}else if(opcionUno==2) {
				while(opcionDos!=10) {
					System.out.println("Que desea hacer: \n 1. Obtener genero mediante. \n 2. Obtener todos los generos. \n 3. Crear genero. \n 4. Borrar genero. \n 5. Editar genero. \n ID 10. Volver");
					opcionDos=teclado.nextInt();
					switch (opcionDos) {
					case 1:
						System.out.print("Introduzca id del genero: ");
						idGenero = teclado.nextInt();
						genero = gen.obtenerGenero(idGenero);
						if(genero!=null) {
							System.out.println(genero.toString());
						}
						break;
					case 2:
						generos = gen.obtenerGeneros();
						for(int i = 0; i<generos.size(); i++) {
							System.out.println(generos.get(i).getNombreGenero());
						}
						break;
					case 3:
						System.out.println("Introduzca nombre: ");
						teclado.nextLine();
						nombre = teclado.nextLine();
						System.out.println("Introduzca descripcion: ");
						descripcion = teclado.nextLine();
						genero = gen.crearGenero(nombre, descripcion);
						System.out.println(genero.toString());
						break;
					case 4:
						System.out.println("Introduzca id del genero que quieres borrar: ");
						idGenero = teclado.nextInt();
						System.out.println(gen.eliminarGenero(idGenero));
						break;
					case 5:
						System.out.println("Introduzca id del genero que quieres modificar: ");
						idGenero = teclado.nextInt();
						genero = gen.obtenerGenero(idGenero);
						if(genero != null){
							genero = gen.editarGenero(genero);
							System.out.println(genero.toString());
						}
						break;
					default:
						break;
					}
				}
			}
		}
	}
}


