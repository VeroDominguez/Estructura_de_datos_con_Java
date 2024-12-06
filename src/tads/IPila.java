package tads;


public interface IPila <T extends Comparable <T>> {
        
    public boolean esVacia();
        
    public void apilar(T dato);
    
    public void desapilar();
       
    
}
