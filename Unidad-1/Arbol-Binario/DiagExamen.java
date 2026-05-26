package diagnostico;
import java.util.Scanner;

public class DiagExamen {
	  public static void main(String[] args) {
		  Scanner scanner = new Scanner(System.in);
	      Arbol arbol = new Arbol();
	      int opcion;

	     do {
	           System.out.println("\n--- MENÚ ÁRBOL BINARIO ---");
	           System.out.println("1. Verificar si el árbol está vacío");
	           System.out.println("2. Buscar un nodo por nombre");
	           System.out.println("3. Insertar un nodo");
	           System.out.println("4. Salir");
	           System.out.print("Elige una opción: ");
	           opcion = scanner.nextInt();
	           scanner.nextLine(); // Limpiar el buffer

	           switch (opcion) {
	               case 1:
	                   if (arbol.vacio()) {
	                       System.out.println("El árbol está vacío.");
	                   } else {
	                       System.out.println("El árbol NO está vacío.");
	                   }
	                   break;

	               case 2:
	                   System.out.print("Introduce el nombre a buscar: ");
	                   String nombreBuscar = scanner.nextLine();
	                   Nodo encontrado = arbol.buscarNodo(nombreBuscar);
	                   if (encontrado != null) {
	                       System.out.println("Nodo encontrado: " + encontrado.nombre);
	                   } else {
	                       System.out.println("Nodo no encontrado.");
	                   }
	                   break;

	               case 3:
	                   System.out.print("Introduce el nombre a insertar: ");
	                   String nombreInsertar = scanner.nextLine();
	                   arbol.insertar(nombreInsertar);
	                   System.out.println("Nodo insertado (si no existía).");
	                   break;

	               case 4:
	                   System.out.println("Saliendo del programa...");
	                   break;

	               default:
	                   System.out.println("Opción no válida. Intenta de nuevo.");
	          }
	     } while (opcion != 4);
	     scanner.close();
	 }
}
