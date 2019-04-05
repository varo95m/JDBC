import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		int opcionUno=0, opcionDos=0, idSerie, idGenero;
		String nombre, descripcion;
		MetodosSeries series = new MetodosSeries();
		Scanner teclado = new Scanner(System.in);
		Serie serie;
		while(opcionUno!=3) {
			System.out.println("Con que desea trabajar:\n 1. Series.\n 2. Generos.\n 3. Salir.");
			opcionUno = teclado.nextInt();
			if(opcionUno==1) {
				while(opcionDos!=10) {
					System.out.println("Que desea hacer: \n 1. Obtener serie mediante id. \n 2. Crear serie.\n 2. Borrar serie. \n 10. Volver");
					opcionDos=teclado.nextInt();
					switch (opcionDos) {
					case 1:
						System.out.print("Introduzca id de la serie: ");
						idSerie = teclado.nextInt();
						serie = series.obtenerSerie(idSerie);
						System.out.println(serie.toString());
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
					case 3:
						
					default:
						break;
					}
				}
			}
		}
	}
}
