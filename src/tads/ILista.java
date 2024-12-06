
package tads;

public interface ILista<T extends Comparable<T>> {
    
    //Pos: Retorna true sii el dato pasado como parámetro pertenece a la lista.
    public boolean existeElemento (T x);
    
    //Pre: El elemento x pasado como parámetro pertenece a la lista.
    //Pos: Elimina de la lista la primer ocurrencia del elemento x.
    public void eliminarElemento (T x);
    
    //Pos: Retorna la cantidad de elementos de la lista.
    public int cantidadElementos ();

    //Pos: Inserta el dato pasado como parámetro al final de la lista.
    public void agregarFinal (T x); 
    
     
    public boolean esVacia();
    
    //Pos: Inserta el dato pasado como parámetro al inicio de la lista.
    public void agregarInicio (T x);
    
    public void mostrar(); 
    
    public void vaciar();
    
    //Pos: se elimina el primer elemento de la lista
    public void eliminarInicio();
    
    //Pos: se elimina el último elemento de la lista
    public void eliminarFinal();
    
    //---------------------------------
    
    /*----------------------------
    Otros Métodos (iterativos)
     -----------------------------*/
    //PRE: Lista ordenada => mantiena orden
    //POS: Inserta nuevo elemento en orden ascendente
    //Lista vacía o primer elemento es mayor o igual => agrego al inicio
    //Último elemento es menor o igual => agrego al final
    public void agregarOrd(Nodo<T> nodo);
    
    //PRE: Se recibe un dato a buscar en la lista
    //POS: Retorna un puntero al nodo que contiene el dato
    //Si el elemento no se encuentra retorna null
    public Nodo obtenerElemento(T dato);
    
    //PRE: Toma una lista != null
    //POS: Concatena los datos de la lista en un string
    public String ListarDatos();
   
    //PRE: Toma una lista != null
    //POS: Muestra los datos de la lista en un string de forma recursiva
    public String ListarDatosREC();

}
