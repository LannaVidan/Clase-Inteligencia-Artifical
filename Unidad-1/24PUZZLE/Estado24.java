package puzzle24;

import java.util.Arrays;

public class Estado24 {
    public static final int N = 5;
    public static final int SIZE = N * N;
    public static final int[] OBJETIVO = new int[SIZE];
    public static final int ARRIBA = 0, ABAJO = 1, IZQUIERDA = 2, DERECHA = 3;

    static {
        for (int i = 0; i < SIZE - 1; i++) OBJETIVO[i] = i + 1;
        OBJETIVO[SIZE - 1] = 0;
    }

    private int[] tablero;
    private int espacio;
    private int manhattan;

    public Estado24(int[] tablero) {
        this.tablero = tablero.clone();
        for (int i = 0; i < SIZE; i++) {
            if (tablero[i] == 0) {
                espacio = i;
                break;
            }
        }
        this.manhattan = calcularManhattan();
    }

    public int getEspacio() { return espacio; }
    public int[] getTablero() { return tablero; }
    public int getManhattan() { return manhattan; }

    private int calcularManhattan() {
        int dist = 0;
        for (int i = 0; i < SIZE; i++) {
            int valor = tablero[i];
            if (valor == 0) continue;
            int filaObj = (valor - 1) / N;
            int colObj = (valor - 1) % N;
            int filaAct = i / N;
            int colAct = i % N;
            dist += Math.abs(filaAct - filaObj) + Math.abs(colAct - colObj);
        }
        return dist;
    }

    public int calcularManhattanConConflicto() {
        int dist = manhattan;
        dist += 2 * contarConflictosLineales();
        return dist;
    }

    private int contarConflictosLineales() {
        int conflictos = 0;
       
        for (int fila = 0; fila < N; fila++) {
            int[] piezas = new int[N];
            int[] pos = new int[N];
            int idx = 0;
            for (int col = 0; col < N; col++) {
                int valor = tablero[fila * N + col];
                if (valor != 0 && (valor - 1) / N == fila) {
                    piezas[idx] = valor;
                    pos[idx] = col;
                    idx++;
                }
            }
            for (int i = 0; i < idx; i++) {
                for (int j = i + 1; j < idx; j++) {
                    if (piezas[i] > piezas[j] && pos[i] < pos[j]) conflictos++;
                }
            }
        }
        
        for (int col = 0; col < N; col++) {
            int[] piezas = new int[N];
            int[] pos = new int[N];
            int idx = 0;
            for (int fila = 0; fila < N; fila++) {
                int valor = tablero[fila * N + col];
                if (valor != 0 && (valor - 1) % N == col) {
                    piezas[idx] = valor;
                    pos[idx] = fila;
                    idx++;
                }
            }
            for (int i = 0; i < idx; i++) {
                for (int j = i + 1; j < idx; j++) {
                    if (piezas[i] > piezas[j] && pos[i] < pos[j]) conflictos++;
                }
            }
        }
        return conflictos;
    }

    public Estado24[] generarSucesores() {
        int[] dirs = { -N, N, -1, 1 };
        int count = 0;
        for (int d : dirs) {
            int nuevoEsp = espacio + d;
            if (d == -1 && espacio % N == 0) continue;
            if (d == 1 && espacio % N == N - 1) continue;
            if (nuevoEsp >= 0 && nuevoEsp < SIZE) count++;
        }
        Estado24[] sucesores = new Estado24[count];
        int idx = 0;
        for (int d : dirs) {
            int nuevoEsp = espacio + d;
            if (d == -1 && espacio % N == 0) continue;
            if (d == 1 && espacio % N == N - 1) continue;
            if (nuevoEsp >= 0 && nuevoEsp < SIZE) {
                int[] nuevoTab = tablero.clone();
                nuevoTab[espacio] = nuevoTab[nuevoEsp];
                nuevoTab[nuevoEsp] = 0;
                sucesores[idx++] = new Estado24(nuevoTab);
            }
        }
        return sucesores;
    }

    public Estado24 aplicarMovimiento(int dir) {
        int nuevoEsp = espacio;
        switch (dir) {
            case ARRIBA:    nuevoEsp = espacio - N; break;
            case ABAJO:  nuevoEsp = espacio + N; break;
            case IZQUIERDA:  nuevoEsp = espacio - 1; break;
            case DERECHA: nuevoEsp = espacio + 1; break;
            default: return null;
        }
        if (nuevoEsp < 0 || nuevoEsp >= SIZE) return null;
        if (dir == IZQUIERDA && espacio % N == 0) return null;
        if (dir == DERECHA && espacio % N == N - 1) return null;
        int[] nuevoTab = tablero.clone();
        nuevoTab[espacio] = nuevoTab[nuevoEsp];
        nuevoTab[nuevoEsp] = 0;
        return new Estado24(nuevoTab);
    }

    public boolean esObjetivo() {
        return Arrays.equals(tablero, OBJETIVO);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Estado24)) return false;
        Estado24 otro = (Estado24) obj;
        return Arrays.equals(tablero, otro.tablero);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tablero);
    }

    public void imprimir() {
        for (int i = 0; i < SIZE; i++) {
            if (tablero[i] == 0) {
                System.out.print("   ");
            } else {
                System.out.printf("%2d ", tablero[i]);
            }
            if ((i + 1) % N == 0) System.out.println();
        }
        System.out.println();
    }
}