package Programas;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ArbolDeBusqueda {
    private final Nodo raiz;
    public int nodosVisitados;

    public ArbolDeBusqueda(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo busquedaAnchura(String estadoObjetivo) {
        if (raiz == null) return null;

        HashSet<String> visitados = new HashSet<>();
        Queue<Nodo> cola = new LinkedList<>();
    
        visitados.add(raiz.estado);
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            if (actual.estado.equals(estadoObjetivo)) {
                nodosVisitados = visitados.size();
                return actual;
            }
            
            for (String s : actual.getSucesores(actual.estado)) {
                if (!visitados.contains(s)) {
                    visitados.add(s); 
                    Nodo nodoHijo = new Nodo(s);
                    nodoHijo.padre = actual;
                    nodoHijo.nivel = actual.nivel + 1;
                    cola.add(nodoHijo);
                }
            }
        }
        nodosVisitados = visitados.size(); 
        return null;
    }
    
    public Nodo busquedaProfundidad(String estadoObjetivo) {
        if (raiz == null) return null;
        
        HashSet<String> visitados = new HashSet<>();
        Stack<Nodo> pila = new Stack<>();
        
        visitados.add(raiz.estado);
        pila.push(raiz);
        
        while (!pila.isEmpty()) {
            Nodo actual = pila.pop();
            
            if (actual.estado.equals(estadoObjetivo)) {
                nodosVisitados = visitados.size();
                return actual;
            }
            
            for (String s : actual.getSucesores(actual.estado)) {
                if (!visitados.contains(s)) {
                    visitados.add(s);
                    Nodo nodoHijo = new Nodo(s);
                    nodoHijo.padre = actual;
                    nodoHijo.nivel = actual.nivel + 1;
                    nodoHijo.setCostos(actual.getCostoReal() + 1, 0);
                    pila.push(nodoHijo);
                }
            }
        }
        nodosVisitados = visitados.size();
        return null;
    }

    public Nodo busquedaCostoUnitario(String estadoObjetivo) {
        if (raiz == null) return null;

        PriorityQueue<Nodo> frontera = new PriorityQueue<>(new NodoComparator());
        HashSet<String> visitados = new HashSet<>();

        raiz.setCostos(0, 0); // h = 0 siempre para Costo Unitario
        frontera.add(raiz);

        while (!frontera.isEmpty()) {
            Nodo actual = frontera.poll(); 
            
            if (actual.estado.equals(estadoObjetivo)) {
                nodosVisitados = visitados.size();
                return actual;
            }

            if (visitados.contains(actual.estado)) continue;
            visitados.add(actual.estado);

            for (String s : actual.getSucesores(actual.estado)) {
                if (!visitados.contains(s)) {
                    Nodo hijo = new Nodo(s);
                    hijo.padre = actual;
                    hijo.nivel = actual.nivel + 1;
                    
                    // El costo real (g) aumenta en 1, el heurístico (h) es 0
                    hijo.setCostos(actual.getCostoReal() + 1, 0);
                    frontera.add(hijo);
                }
            }
        }
        nodosVisitados = visitados.size();
        return null;
    }

    // Algoritmo A*
    public Nodo busquedaHeuristica(String estadoObjetivo) {
        if (raiz == null) return null;

        PriorityQueue<Nodo> frontera = new PriorityQueue<>(new NodoComparator());
        HashSet<String> visitados = new HashSet<>();

        raiz.setCostos(0, calcularHeuristica(raiz.estado, estadoObjetivo));
        frontera.add(raiz);

        while (!frontera.isEmpty()) {
            Nodo actual = frontera.poll(); 
            
            if (actual.estado.equals(estadoObjetivo)) {
                nodosVisitados = visitados.size();
                return actual;
            }

            if (visitados.contains(actual.estado)) continue;
            visitados.add(actual.estado);

            for (String s : actual.getSucesores(actual.estado)) {
                if (!visitados.contains(s)) {
                    Nodo hijo = new Nodo(s);
                    hijo.padre = actual;
                    hijo.nivel = actual.nivel + 1;

                    int g = actual.getCostoReal() + 1;
                    int h = calcularHeuristica(s, estadoObjetivo);
                    
                    hijo.setCostos(g, h);
                    frontera.add(hijo);
                }
            }
        }
        nodosVisitados = visitados.size();
        return null;
    }

    // Heurística de piezas fuera de lugar
    private int calcularHeuristica(String actual, String objetivo) {
        int piezasFuera = 0;
        for (int i = 0; i < actual.length(); i++) {
            if (actual.charAt(i) != ' ' && actual.charAt(i) != objetivo.charAt(i)) {
                piezasFuera++;
            }
        }
        return piezasFuera;
    }
}