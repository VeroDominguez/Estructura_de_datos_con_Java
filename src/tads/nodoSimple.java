
package tads;

public class nodoSimple {
    int dato;
    nodoSimple siguiente;

    public nodoSimple(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public nodoSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nodoSimple siguiente) {
        this.siguiente = siguiente;
    }
    
    
}

