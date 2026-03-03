package puzzle24;

import java.util.*;

public class Puzzle24 {
    public static void main(String[] args) {
        // Genera el estado inicial aplicando 60 movimientos aleatorios desde el objetivo
        int[] estadoInicial = generarEstadoDesdeObjetivo(60);
        Estado24 inicial = new Estado24(estadoInicial);

        System.out.println("24-PUZZLE:");
        System.out.println("Estado inicial (generado con 60 movimientos desde el objetivo):");
        inicial.imprimir();
        System.out.println("Objetivo:");
        new Estado24(Estado24.OBJETIVO).imprimir();

        
        System.out.println("\n IDA* con Distancia de Manhattan ");
        IDAStar idaManhattan = new IDAStar(inicial, false);
        long t1 = System.currentTimeMillis();
        boolean exito1 = idaManhattan.buscar();
        long tiempo1 = System.currentTimeMillis() - t1;
        int nodos1 = idaManhattan.getNodosExpandidos();
        List<Integer> movs1 = idaManhattan.getSolucionMovimientos();
        int pasos1 = (movs1 != null) ? movs1.size() : -1;

        System.out.println("Tiempo: " + tiempo1 + " ms");
        System.out.println("Nodos expandidos: " + nodos1);
        System.out.println("Longitud de la solución: " + pasos1);


        System.out.println("\n Conflicto Lineal ");
        IDAStar idaConflicto = new IDAStar(inicial, true);
        long t2 = System.currentTimeMillis();
        boolean exito2 = idaConflicto.buscar();
        long tiempo2 = System.currentTimeMillis() - t2;
        int nodos2 = idaConflicto.getNodosExpandidos();
        List<Integer> movs2 = idaConflicto.getSolucionMovimientos();
        int pasos2 = (movs2 != null) ? movs2.size() : -1;

        System.out.println("Tiempo: " + tiempo2 + " ms");
        System.out.println("Nodos expandidos: " + nodos2);
        System.out.println("Longitud de la solución: " + pasos2);

   
        System.out.println("\n=== TABLA COMPARATIVA ===");
        System.out.println("Heurística\t\tNodos Expandidos\tTiempo (ms)\tPasos");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Manhattan\t\t" + nodos1 + "\t\t\t" + tiempo1 + "\t\t" + pasos1);
        System.out.println("Manhattan+Conflicto\t" + nodos2 + "\t\t\t" + tiempo2 + "\t\t" + pasos2);

        // Muestra la secuencia de pasos de la mejor solucion 
        if (exito2) {
            System.out.println("\n--- Secuencia de movimientos (espacio: ↑ ↓ ← →) ---");
            imprimirSecuencia(inicial, movs2);
        } else if (exito1) {
            System.out.println("\n--- Secuencia de movimientos (espacio: ↑ ↓ ← →) ---");
            imprimirSecuencia(inicial, movs1);
        }
    }


    private static int[] generarEstadoDesdeObjetivo(int pasos) {
        Random rand = new Random();
        Estado24 estado = new Estado24(Estado24.OBJETIVO);
        for (int i = 0; i < pasos; i++) {
            Estado24[] sucesores = estado.generarSucesores();
            estado = sucesores[rand.nextInt(sucesores.length)];
        }
        return estado.getTablero();
    }

    private static void imprimirSecuencia(Estado24 inicial, List<Integer> movimientos) {
        Estado24 actual = inicial;
        System.out.println("Paso 0:");
        actual.imprimir();
        for (int i = 0; i < movimientos.size(); i++) {
            int dir = movimientos.get(i);
            String dirStr = "";
            switch (dir) {
                case Estado24.ARRIBA:    dirStr = "↑"; break;
                case Estado24.ABAJO:  dirStr = "↓"; break;
                case Estado24.IZQUIERDA:  dirStr = "←"; break;
                case Estado24.DERECHA: dirStr = "→"; break;
            }
            actual = actual.aplicarMovimiento(dir);
            System.out.println("Paso " + (i+1) + " (movimiento " + dirStr + "):");
            actual.imprimir();
        }
    }
}