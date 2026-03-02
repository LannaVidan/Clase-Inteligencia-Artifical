package diagnostico;

public class Arbol {
    Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    public boolean vacio() {
        return raiz == null;
    }

    public Nodo buscarNodo(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Nodo buscarRec(Nodo nodo, String nombre) {
        if (nodo == null) return null;
        if (nodo.nombre.equals(nombre)) return nodo;
        Nodo encontrado = buscarRec(nodo.izquierdo, nombre);
        if (encontrado != null) return encontrado;
        return buscarRec(nodo.derecho, nombre);
    }

    public void insertar(String nombre) {
        raiz = insertarRec(raiz, nombre);
    }

    private Nodo insertarRec(Nodo nodo, String nombre) {
        if (nodo == null) return new Nodo(nombre);
        if (nombre.compareTo(nodo.nombre) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, nombre);
        } else if (nombre.compareTo(nodo.nombre) > 0) {
            nodo.derecho = insertarRec(nodo.derecho, nombre);
        }
        // si el nombre existe ya no se inserta 
        return nodo;
    }
}