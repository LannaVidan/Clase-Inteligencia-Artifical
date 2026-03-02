package Programas;

import java.util.Comparator;

public class NodoComparator implements Comparator<Nodo> {

    @Override
    public int compare(Nodo x, Nodo y) {
        if (x.getTotalCosto() < y.getTotalCosto()) {
            return -1;
        }
        if (x.getTotalCosto() > y.getTotalCosto()) {
            return 1;
        }
        return 0;
    }
}