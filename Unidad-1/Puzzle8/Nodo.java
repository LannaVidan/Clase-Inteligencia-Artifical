package Programas;
public class Nodo {
    public String estado;
    public Nodo padre;
    public int nivel; 
    private int costo;         // g(n)
    private int costoEstimado; // h(n)
    private int costoTotal;    // f(n)

    public Nodo(String estado) {
        this.estado = estado;
    }

    public int getTotalCosto() {
        return costoTotal;
    }

    // Método para actualizar los valores
   public void setCostos(int g, int h) {
        this.costo = g;
        this.costoEstimado = h;
        this.costoTotal = this.costo + this.costoEstimado; 
    }
    public int getCostoEstimado() {
        return costoEstimado;
    }

    public int getCostoReal() {
        return costo;
    }

    public String[] getSucesores(String estadoActual) {
        return Puzzle8.generarSucesores(estadoActual);
    }


    public void imprimirTablero() {
        for (int i = 0; i < estado.length(); i++) {
            System.out.print(estado.charAt(i) + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

}

