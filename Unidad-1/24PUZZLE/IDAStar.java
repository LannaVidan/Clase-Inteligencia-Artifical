package puzzle24;

import java.util.*;

public class IDAStar {
    private Estado24 inicial;
    private boolean usarConflictoLineal;
    private int nodosExpandidos;
    private List<Integer> solucionMovimientos; 

    public IDAStar(Estado24 inicial, boolean usarConflictoLineal) {
        this.inicial = inicial;
        this.usarConflictoLineal = usarConflictoLineal;
        this.nodosExpandidos = 0;
        this.solucionMovimientos = null;
    }

    public int getNodosExpandidos() { return nodosExpandidos; }
    public List<Integer> getSolucionMovimientos() { return solucionMovimientos; }

    public boolean buscar() {
        int limite = heuristica(inicial);
        Stack<Integer> pila = new Stack<>();
        while (true) {
            int resultado = busquedaProfundidad(inicial, null, 0, limite, pila);
            if (resultado == -1) {
                return true;
            }
            if (resultado == Integer.MAX_VALUE) return false;
            limite = resultado;
            System.out.println("  Nuevo límite: " + limite); 
        }
    }

    private int heuristica(Estado24 estado) {
        if (usarConflictoLineal) {
            return estado.calcularManhattanConConflicto();
        } else {
            return estado.getManhattan();
        }
    }

    private int busquedaProfundidad(Estado24 actual, Estado24 padre, int g, int limite, Stack<Integer> pila) {
        int f = g + heuristica(actual);
        if (f > limite) return f;
        if (actual.esObjetivo()) {
            solucionMovimientos = new ArrayList<>(pila);
            return -1;
        }
        int min = Integer.MAX_VALUE;
        Estado24[] sucesores = actual.generarSucesores();
        nodosExpandidos++;
        for (Estado24 hijo : sucesores) {
            if (hijo.equals(padre)) continue; // si tiene el mismo estado lo ignora
            
            // determina la dirección del movimiento
            int dir;
            if (hijo.getEspacio() == actual.getEspacio() - Estado24.N) dir = Estado24.ARRIBA;
            else if (hijo.getEspacio() == actual.getEspacio() + Estado24.N) dir = Estado24.ABAJO;
            else if (hijo.getEspacio() == actual.getEspacio() - 1) dir = Estado24.IZQUIERDA;
            else dir = Estado24.DERECHA;
            pila.push(dir);
            int resultado = busquedaProfundidad(hijo, actual, g + 1, limite, pila);
            if (resultado == -1) {
                return -1;
            }
            pila.pop();
            if (resultado < min) min = resultado;
        }
        return min;
    }
}
