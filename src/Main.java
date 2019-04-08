import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		int opcionUno=0, opcionDos=0, idSerie, idGenero, cantidad, contadorPaginacion = 0;
		String opcion, nombre, descripcion;
		MetodosSeries series = new MetodosSeries();
		Scanner teclado = new Scanner(System.in);
		Serie serie;
		List<Serie> numeroSeries = new ArrayList<Serie>();
		while(opcionUno!=3) {
			System.out.println("Con que desea trabajar:\n 1. Series.\n 2. Generos.\n 3. Salir.");
			opcionUno = teclado.nextInt();
			if(opcionUno==1) {
				while(opcionDos!=10) {
					System.out.println("Que desea hacer: \n 1. Obtener serie mediante id. \n 2. Crear serie.\n 3. Borrar serie.\n "
							+ "4. Mostrar total series.\n 5. Mostrar series por nombre.\n 6. Mostrar series por genero. \n 10. Volver");
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
						System.out.println(series.borrarSerie(idSerie));
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
						int index = 0;
						System.out.println("Introduzca genero: ");
						idGenero = teclado.nextInt();
						numeroSeries = series.obtenerSeriesPorGenero(idGenero, index, 5);
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
								numeroSeries = series.obtenerSeriesPorGenero(idGenero, index, 5);
								for(int i = 0; i<numeroSeries.size(); i++) {
									System.out.println(numeroSeries.get(i).getNombre());
								}
							}else if(opcion.equals("d")) {
								index+=5;
								numeroSeries = series.obtenerSeriesPorGenero(idGenero, index, 5);
								for(int i = 0; i<numeroSeries.size(); i++) {
									System.out.println(numeroSeries.get(i).getNombre());
								}
							}else {
								System.out.println("No es una opcion valida");
							}
							System.out.println("Introduzca opcion");
							opcion = teclado.nextLine();
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
