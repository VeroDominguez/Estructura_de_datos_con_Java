package tads;

public interface IListaSimple {

    public void agregarInicio(int dato);
//Pos: Inserta el dato pasado como parámetro al inicio de la lista. 

    public void mostrar();
//Pos: muestra los elementos de la lista. 

    public int cantidadElementos();
//Pos: Retorna la cantidad de elementos de la lista. 

    public boolean existeElemento(int dato);
//Pos: Retorna true si el dato pasado como parámetro pertenece a la lista. 

    public nodoSimple obtenerElemento(int dato);
//Pos: retorna el primer nodo que contiene el elemento x y null si no lo encuentra. 

    public void eliminarElemento(int dato);
//Pre: El elemento x pasado como parámetro pertenece a la lista. Pos: 
//Elimina de la lista la primera ocurrencia del elemento x. 

    public void agregarFinal(int x);
//Pos: Inserta el dato pasado como parámetro al final de la lista. 

    public boolean esVacia();
//Pos: retorna true si la lista está vacía y false en otro caso. 

    public void vaciar();
//Pos: vacía la lista. 

    public void eliminarInicio();
//Pos: se elimina el primer elemento de la lista 

    public void eliminarFinal();
//Pos: se elimina el último elemento de la lista  

//------------------------------------------------------
// FUNCIONALIDADES RECURSIVAS
//------------------------------------------------------
    public void mostrarRec();

    public ListaSimple invertir();
//Pos: Retorna una nueva lista, resultado de invertir el orden de los elementos de la lista original. 

    public boolean estaOrdenada();
// Pos: Retorna true sii la lista está ordenada. 

    public void insertarOrdenado(int elem);
//Pre: La lista está ordenada ascendentemente. 

//Pos: Inserta el elemento pasado como parámetro de forma ordenada en la lista. 
    public int contar(int elem);
//Pos: Retorna la cantidad de veces que aparece el elemento pasado como parámetro en la lista. 

    public int maximo();
//Pre: La lista no es vacía. 
//Pos: Retorna el máximo elemento de la lista. 

    public int promedio();
//Pre: La lista no es vacía. 
//Pos: Retorna el promedio de los elementos de la lista. 

    public int tomar_n(int n);
//Pre: El largo de la lista es mayor o igual que n. 
//Pos: Retorna el elemento que se encuentra en la posición n, contando a partir de 1. 

    public ListaSimple cambiar(int n, int m);
//Pos: Retorna la lista resultado de cambiar todas las ocurrencias de n por el elemento m. 

//------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
    public boolean esIgual(ListaSimple p);
//Pos: Retorna true si la lista es igual a la lista p. Dos listas son iguales si son vacías o si tienen los 
//mismos elementos y en el mismo orden.

    public ListaSimple intercalar(ListaSimple p);
//Pre: Las listas están ordenadas.
//Pos: Retorna una nueva lista resultado de intercalar ordenadamente los elementos de p y 
//Algoritmos y Estructuras de Datos 1 R. Cohen 
//actual.

    public ListaSimple concatenar(ListaSimple p);
//Pos: Retorna una nueva lista, resultado de concatenar la list p al final de la lista actual.

    public boolean estaIncluida(ListaSimple p);
//Pos: Retorna true si una lista está incluida en una lista p (si es posible encontrar una sub-­­
//secuencia de elementos de p igual a la secuencia de elementos de la lista).

}
