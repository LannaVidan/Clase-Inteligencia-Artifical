package Programas;

import java.util.Stack;

public class Main {
    
    public static void main(String[] args) {
        Puzzle8 puzzle = new Puzzle8();
        String objetivo = puzzle.estadoObjetivo;

        //Anchura
        ArbolDeBusqueda arbolAnchura = new ArbolDeBusqueda(new Nodo(puzzle.estadoInicial));
        long t1 = System.currentTimeMillis();
        Nodo solAnchura = arbolAnchura.busquedaAnchura(objetivo);
        long tiempoAnchura = System.currentTimeMillis() - t1;

        // Profundidad 
        ArbolDeBusqueda arbolProfundidad = new ArbolDeBusqueda(new Nodo(puzzle.estadoInicial));
        long t2 = System.currentTimeMillis();
        Nodo solProfundidad = arbolProfundidad.busquedaProfundidad(objetivo);
        long tiempoProfundidad = System.currentTimeMillis() - t2;

        // Costo Unitario 
        ArbolDeBusqueda arbolUnitario = new ArbolDeBusqueda(new Nodo(puzzle.estadoInicial));
        long t3 = System.currentTimeMillis();
        Nodo solUnitario = arbolUnitario.busquedaCostoUnitario(objetivo);
        long tiempoUnitario = System.currentTimeMillis() - t3;

        // Heurística 
        ArbolDeBusqueda arbolHeuristica = new ArbolDeBusqueda(new Nodo(puzzle.estadoInicial));
        long t4 = System.currentTimeMillis();
        Nodo solHeuristica = arbolHeuristica.busquedaHeuristica(objetivo);
        long tiempoHeuristica = System.currentTimeMillis() - t4;

        // pasos de la solución 
        if (solHeuristica != null) {
            System.out.println("--------------------------------------");
            System.out.println("SOLUCIÓN A*:");
            System.out.println("--------------------------------------");
            imprimirPasos(solHeuristica);
            
            System.out.println("\nNúmero de transiciones para llegar al estado objetivo desde el estado inicial: " + solHeuristica.nivel);
            System.out.println("** Número de estados visitados: " + arbolHeuristica.nodosVisitados);
            System.out.println("** Costo total para esta solución: " + solHeuristica.getCostoReal()); 

        } else {
            System.out.println("\nNo se encontró solución.");
        }

        System.out.println("\n--------------------------------------");
        System.out.println("TABLA COMPARATIVA FINAL:");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Algoritmo\t\tNodos Visitados\t\tTiempo(ms)\t\tPasos(Movimientos)");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Anchura \t\t" + arbolAnchura.nodosVisitados + "\t\t\t" + tiempoAnchura + "\t\t\t" + (solAnchura != null ? solAnchura.nivel : "N/A"));
        System.out.println("Profundidad\t\t" + arbolProfundidad.nodosVisitados + "\t\t\t" + tiempoProfundidad + "\t\t\t" + (solProfundidad != null ? solProfundidad.nivel : "N/A"));
        System.out.println("Costo Unitario\t\t" + arbolUnitario.nodosVisitados + "\t\t\t" + tiempoUnitario + "\t\t\t" + (solUnitario != null ? solUnitario.nivel : "N/A"));
        System.out.println("A*   \t\t\t" + arbolHeuristica.nodosVisitados + "\t\t\t" + tiempoHeuristica + "\t\t\t" + (solHeuristica != null ? solHeuristica.nivel : "N/A"));
    }

   public static void imprimirPasos(Nodo solucion) {
        Stack<Nodo> camino = new Stack<>();
        Nodo actual = solucion;
        
        while (actual != null) {
            camino.push(actual);
            actual = actual.padre;
        }

        int p = 0;
        while (!camino.isEmpty()) {
            Nodo nodo = camino.pop();
            System.out.println("PASO: " + p );
            nodo.imprimirTablero();
            p++;
        }
    }
}