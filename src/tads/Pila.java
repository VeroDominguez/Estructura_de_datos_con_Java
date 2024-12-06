package tads;

public class Pila<T extends Comparable<T>> implements IPila<T> {

    private int cantidadnodos;
    private Nodo<T> cima;
    private Nodo<T> fondo;

    //Constructor
    public Pila() {
        this.cantidadnodos = 0;
        this.cima = null;
        this.fondo = null;
    }

    public int getCantidadnodos() {
        return cantidadnodos;
    }

    public void setCantidadnodos(int cantidadnodos) {
        this.cantidadnodos = cantidadnodos;
    }

    public Nodo<T> getCima() {
        return cima;
    }

    public void setCima(Nodo<T> cima) {
        this.cima = cima;
    }

    public Nodo<T> getFondo() {
        return fondo;
    }

    public void setFondo(Nodo<T> fondo) {
        this.fondo = fondo;
    }

    //----------------------------------------------------------------  
    // metodos abstractos    
    //------------------------------------------------------------------    
    @Override
    public boolean esVacia() {
        return this.cantidadnodos == 0;
    }
 

    @Override
    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo(dato);

        if (this.cima == null) {
            this.setCima(nuevo);
        } else {
            nuevo.setSiguiente(this.getCima());
            this.setCima(nuevo);
        }
        this.cantidadnodos++;
    }

    @Override
    public void desapilar() {

        if (!this.esVacia()) {
            if (this.cantidadnodos == 1) {
                this.cima = null;
                this.cantidadnodos = 0;
            } else {
                this.cima = this.cima.getSiguiente();//Verificar metodo, Vero lo tiene distinto 
                this.cantidadnodos--;
            }
        } else {
            System.out.println("Pila vacia");
        }
    }
 
  
}
