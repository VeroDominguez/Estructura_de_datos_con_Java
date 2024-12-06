package tads;

public interface ICola <T extends Comparable <T>> {

    public boolean esVacia();
    
    public void encolar(T dato);
    
    public void desencolar();
    
    public Nodo<T> fondo();
    
    public Nodo<T> frente();
    
}
