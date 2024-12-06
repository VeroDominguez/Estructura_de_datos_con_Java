package tads;

public class Cola <T extends Comparable<T>> implements ICola<T> {

    int cantidadnodos;
    Nodo<T> frente;
    Nodo<T> fondo;

    public Cola() {
        this.cantidadnodos = 0;
        this.frente = null;
        this.fondo = null;
    }

    public int getCantidadnodos() {
        return cantidadnodos;
    }

    public void setCantidadnodos(int cantidadnodos) {
        this.cantidadnodos = cantidadnodos;
    }
 

    public Nodo<T> getFrente() {
        return frente;
    }

    public void setFrente(Nodo<T> frente) {
        this.frente = frente;
    }

    public Nodo<T> getFondo() {
        return fondo;
    }

    public void setFondo(Nodo<T> fondo) {
        this.fondo = fondo;
    }
    
     @Override
    public Nodo<T> fondo() {
        return fondo;
    }

    @Override
    public Nodo<T> frente() {
        return frente; 
    }
    
//-----------------------------------------------
// implementacion de metodos abstractos
//-----------------------------------------------

    @Override
    public boolean esVacia() {
        return this.cantidadnodos == 0;
    }
  

    @Override
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo(dato);
        if (this.esVacia()) {
            this.setFondo(nuevo);
            this.setFrente(nuevo);
            this.cantidadnodos++;
        } else {            
                this.fondo.setSiguiente(nuevo);//Verificar si fuciona 
                this.fondo = nuevo;
                this.cantidadnodos++;            
        }    
    }

    @Override
    public void desencolar() {
        if (!this.esVacia()){
            if (this.frente==this.fondo){
                this.frente=null;
                this.fondo=null;
                this.cantidadnodos=0;
            }else{
                this.frente = this.frente.getSiguiente();
                this.cantidadnodos--;
            }
        }else{
            System.out.println("Cola vacia no hay elementos para desencolar ");
        }
    }

   
   

}
