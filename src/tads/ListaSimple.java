package tads;

public class ListaSimple implements IListaSimple {

    nodoSimple primero;
    nodoSimple ultimo;
    int cantnodos;

    public ListaSimple() {
        this.primero = null;
        this.ultimo = null;
        this.cantnodos = 0;
    }

    public nodoSimple getPrimero() {
        return primero;
    }

    public void setPrimero(nodoSimple primero) {
        this.primero = primero;
    }

    public nodoSimple getUltimo() {
        return ultimo;
    }

    public void setUltimo(nodoSimple ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantnodos() {
        return cantnodos;
    }

    public void setCantnodos(int cantnodos) {
        this.cantnodos = cantnodos;
    }

//------------------------------------------------------------------
// LISTA DE SIMPLE ENLACE    
//------------------------------------------------------------------
 
    @Override
    public void agregarInicio(int dato) {
        nodoSimple nuevo = new nodoSimple(dato);

        if (this.esVacia()) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            nuevo.setSiguiente(this.getPrimero());
            this.setPrimero(nuevo);
        }
        this.cantnodos++;
    }

    @Override
    public void mostrar() {
        nodoSimple aux = this.getPrimero();
        while (aux != null) {
            System.out.print(aux.getDato() + " - ");
            aux = aux.siguiente;
        }
        System.out.println();
    }

    @Override
    public int cantidadElementos() {
        return this.cantnodos;
    }

    @Override
    public boolean existeElemento(int elemento) {
        boolean existe = false;
        nodoSimple aux = this.getPrimero();

        while (aux != null && !existe) {
            if (elemento == aux.getDato()) {
                existe = true;
            }
            aux = aux.siguiente;
        }
        return existe;
    }

    @Override
    public nodoSimple obtenerElemento(int elemento) {
        nodoSimple existe = null;
        nodoSimple aux = this.getPrimero();

        while (aux != null && existe == null) {
            if (elemento == aux.getDato()) {
                existe = aux;
            }
            aux = aux.siguiente;
        }
        return existe;

    }

    @Override
    public void eliminarElemento(int elemento) {
        if (!this.esVacia()) {
            nodoSimple aux = this.getPrimero();
            boolean borrado = false;
            while (aux != null && aux.getSiguiente() != null && !borrado) {
                if (aux.siguiente.getDato() == elemento) {
                    aux.siguiente = aux.siguiente.siguiente;
                    borrado = true;
                }
                aux = aux.siguiente;
                this.cantnodos--;
            }
        } else {
            System.out.println("Lista vacia, no hay elementos para borrar");
        }
    }

    @Override
    public void agregarFinal(int dato) {
        nodoSimple nuevo = new nodoSimple(dato);
        if (this.esVacia()) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
            this.cantnodos++;
        } else {
            this.ultimo.siguiente = nuevo;
            this.ultimo = nuevo;
            this.cantnodos++;
        }
    }

    @Override
    public boolean esVacia() {
        return this.primero == null;
    }

    @Override
    public void vaciar() {
        this.primero = null;
        this.ultimo = null;
        this.cantnodos = 0;
    }

    @Override
    public void eliminarInicio() {
        if (!this.esVacia()) {
            if (this.cantnodos == 1) {
                this.primero = null;
                this.ultimo = null;
                this.cantnodos = 0;

            } else {
                this.setPrimero(this.primero.getSiguiente());
                this.cantnodos--;
            }

        } else {
            System.out.println("La lista esta vacia, no hay elementos para borrar");
        }
    }

    @Override
    public void eliminarFinal() {
        if (!this.esVacia()) {
            if (this.cantnodos == 1) {
                this.eliminarInicio();
            } else {
                nodoSimple aux = this.getPrimero();
                while (aux.siguiente.siguiente != null) {
                    aux = aux.getSiguiente();
                }
                aux.siguiente = null;
                this.setUltimo(aux);
                this.cantnodos--;
            }
        } else {
            System.out.println("La lista esta vacia, no hay elementos para borrar");
        }
    }

    @Override
    public void mostrarRec() {
        nodoSimple primero = this.getPrimero();
        nodoSimple ultimo = this.getUltimo();
        System.out.println("muestro del primero al ultimo");
        System.out.println(mostrarRecAsc(primero, ultimo));

        System.out.println("Muestro del ultimo al primero");
        System.out.println(mostrarRecDsc(primero, ultimo));

    }

    public String mostrarRecAsc(nodoSimple primero, nodoSimple ultimo) {
        if (this.esVacia()) {
            return " ";
        }
        if (primero == ultimo) {
            return " " + ultimo.getDato();
        } else {
            return primero.getDato() + " - " + mostrarRecAsc(primero.getSiguiente(), ultimo);

        }

    }

    public String mostrarRecDsc(nodoSimple primero, nodoSimple ultimo) {
        if (this.esVacia()) {
            return " ";
        }
        if (primero == ultimo) {
            return " " + ultimo.getDato();
        } else {
            return mostrarRecDsc(primero.getSiguiente(), ultimo) + " - " + primero.getDato();

        }
    }
  
    

    @Override
    public ListaSimple invertir() {
        ListaSimple listainvertida = new ListaSimple();

        nodoSimple aux = this.getPrimero();
        while (aux != null) {
            listainvertida.agregarInicio(aux.getDato());
            aux = aux.siguiente;
        }
        return listainvertida;
    }

    @Override
    public boolean estaOrdenada() {
        boolean ordenada = true;
        nodoSimple aux = this.getPrimero();

        while (aux != null && aux.siguiente != null && ordenada == true) {
            if (aux.getDato() > aux.siguiente.getDato()) {
                ordenada = false;
            }

            aux = aux.getSiguiente();
        }
        return ordenada;
    }

    @Override
    public void insertarOrdenado(int dato) {
        nodoSimple nuevo = new nodoSimple(dato);
        if (this.esVacia() || dato < this.getPrimero().getDato()) {
            this.agregarInicio(dato);
        } else {
            if (dato > this.getUltimo().getDato()) {
                this.agregarFinal(dato);
            } else {
                nodoSimple actual = this.getPrimero();
                while (actual.siguiente != null && dato > actual.siguiente.dato) {
                    actual = actual.siguiente;
                }
                nuevo.setSiguiente(actual.siguiente);
                actual.siguiente = nuevo;

            }

        }

    }

    @Override
    public int contar(int elem) {
        int contador = 0;
        nodoSimple actual = this.getPrimero();
        while (actual != null) {
            if (actual.getDato() == elem) {
                contador++;
            }
            actual = actual.siguiente;
        }

        return contador;
    }

    // precondicion : la lista no esta ordenada
    @Override
    public int maximo() {
        int max = Integer.MIN_VALUE;
        nodoSimple aux = this.getPrimero();
        while (aux != null) {
            if (aux.getDato() > max) {
                max = aux.getDato();
            }
            aux = aux.siguiente;
        }
        return max;
    }

    @Override
    public int promedio() {
        nodoSimple actual = this.getPrimero();
        int suma = 0;
        int cant = 0;
        while (actual != null) {
            suma += actual.getDato();
            actual = actual.getSiguiente();
            cant++;
        }
        return suma / cant;
    }

    @Override
    public int tomar_n(int n) {
        int cant = 1;
        nodoSimple actual = this.getPrimero();
        while (actual != null && cant < n) {

            actual = actual.siguiente;
            cant++;
        }
        return actual.getDato();

    }

    @Override
    public ListaSimple cambiar(int n, int m) {
        nodoSimple actual = this.getPrimero();
        while (actual != null) {
            if (actual.getDato() == n) {
                actual.setDato(m);
            }
            actual = actual.getSiguiente();
        }
        return this;
    }

  
    @Override
    public boolean esIgual(ListaSimple p) {
        boolean iguales = true;
        if (this.cantnodos == p.cantnodos) {
            nodoSimple actual = this.getPrimero();
            nodoSimple actualP = p.getPrimero();

            while (actual != null && actualP != null && iguales == true) {

                if (actual.getDato() != actualP.getDato()) {
                    iguales = false;
                }
                actual = actual.getSiguiente();
                actualP = actualP.getSiguiente();
            }

        } else {
            iguales = false;
        }

        return iguales;

    }

    @Override
    public ListaSimple intercalar(ListaSimple p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListaSimple concatenar(ListaSimple p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean estaIncluida(ListaSimple p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
