package tads;

import dominio.Estudiante;
import dominio.Prestamo;

public class Lista<T extends Comparable<T>> implements ILista<T> {

    private Nodo<T> inicio;

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public Lista() {
        inicio = null;
    }

    @Override
    public boolean existeElemento(T x) {
        Nodo actual = getInicio();
        boolean existe = false;

        while (actual != null && !existe) {
            if (actual.getDato().compareTo(x) == 0) {
                existe = true;
            }
            actual = actual.getSiguiente();
        }

        return existe;
    }

    @Override
    public void eliminarElemento(T x) {
        //Evaluo el primero 
        if (inicio != null) {

            if (inicio.getDato() == x) {
                inicio = inicio.getSiguiente();
            } else {
                Nodo actual = inicio;
                while (actual.getSiguiente() != null && actual.getSiguiente().getDato().compareTo(x) != 0) {
                    actual = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    Nodo aBorrar = actual.getSiguiente();
                    actual.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                }
            }
        }
    }

    @Override
    public void vaciar() {
        this.inicio = null;
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Nodo borrar = inicio;
            inicio = inicio.getSiguiente();
            borrar.setSiguiente(null);
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                this.vaciar();
            } else {

                Nodo actual = inicio;

                while (actual.getSiguiente().getSiguiente() != null) {
                    actual = actual.getSiguiente();

                }
                actual.setSiguiente(null);
            }
        }
    }

    @Override
    public int cantidadElementos() {
        Nodo actual = inicio;
        int cant = 0;

        while (actual != null) {
            cant++;
            actual = actual.getSiguiente();
        }
        return cant;
    }

    @Override
    public void agregarFinal(T x) {
        Nodo nuevo = new Nodo(x);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevo);
        }
    }

    @Override
    public void agregarInicio(T n) {
        Nodo nuevo = new Nodo(n);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
    }

    @Override
    public void mostrar() {

        Nodo mostrar = inicio;

        while (mostrar != null) {
            System.out.println(mostrar.getDato());
            mostrar = mostrar.getSiguiente();
        }
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    /*----------------------------
    Otros métodos
     -----------------------------*/
    @Override
    public void agregarOrd(Nodo<T> nodo) {

        if (this.esVacia()) {
            this.agregarInicio(nodo.getDato());
            return;
        }
        if (this.getInicio().getDato().compareTo(nodo.getDato()) >= 0) {
            this.agregarInicio(nodo.getDato());
            return;
        }

        Nodo aux = this.getInicio();
        while (aux != null && aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(nodo.getDato()) < 0) {
            aux = aux.getSiguiente();
        }
        nodo.setSiguiente(aux.getSiguiente());
        aux.setSiguiente(nodo);
    }

    @Override
    public Nodo obtenerElemento(T dato) {
        if (this.esVacia()) {
            return null;
        }
        Nodo aux = this.getInicio();
        while (aux != null && aux.getDato().compareTo(dato) != 0) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            return aux;
        }
        return aux;
    }

    @Override
    public String ListarDatos() {
        String retornoStr = "";
        Nodo aux = this.getInicio();

        if (aux != null) {
            while (aux != null && aux.getSiguiente() != null) {
                retornoStr = retornoStr + aux.getDato().toString() + "|";
                aux = aux.getSiguiente();
            }

            retornoStr = retornoStr + aux.getDato().toString();
        }
        return retornoStr;
    }

    @Override
    public String ListarDatosREC() {

        Nodo primero = this.getInicio();
        int cantidad = this.cantidadElementos();

        String retorno = "";

        retorno = ListarDatosREC(primero, cantidad);
        return retorno;
    }

    public String ListarDatosREC(Nodo<T> primero, int cantidad) {

        if (primero != null && cantidad == 1) {
            return primero.getDato().toString();
        } else {
            cantidad--;
            return primero.getDato().toString() + "|" + ListarDatosREC(primero.getSiguiente(), cantidad);
        }
    }

     //Método auxiliar
    public Nodo<Prestamo> buscarPrestamoPorISBNYEstudiante(String ISBN, Estudiante estudiante) {
        Nodo<Prestamo> actual = this.getInicio(); // Suponiendo que `this` es tu lista de préstamos
        while (actual != null) {
            Prestamo prestamo = actual.getDato();
            if (prestamo.getLibro().getISBN().equals(ISBN) && prestamo.getEstudiante().equals(estudiante)
                    && prestamo.getEstadoPrestamo().equals("Activo")) {
                return actual; // Devolvemos el nodo si cumple las condiciones
            }
            actual = actual.getSiguiente();
        }
        return null; // Si no se encuentra, retornamos null
    }
    
    
}
