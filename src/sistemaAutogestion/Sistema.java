package sistemaAutogestion;

import dominio.Estudiante;
import dominio.Libro;
import dominio.Prestamo;
import dominio.Reserva;
import java.time.LocalDateTime;
import tads.Cola;
import tads.Lista;
import tads.Nodo;
import tads.Pila;

public class Sistema implements IObligatorio {

    private Lista<Estudiante> listaEstudiantes;
    private Lista<Libro> listaLibros;
    private Lista<Prestamo> listaPrestamos;
    private Pila<Libro> pilaLibrosEliminados;
    private Cola<Reserva> colaReservas;
    private Lista<String> listaCategorias; //agrego

    public static Sistema sistema;

    //El sistema inicializa todas las listas
    public Sistema() {
        listaEstudiantes = new Lista();
        listaLibros = new Lista();
        listaPrestamos = new Lista();
        pilaLibrosEliminados = new Pila();
        listaCategorias = new Lista();
    }

    //Se crea el sistema general vacío con un New Sistema
    @Override
    public Retorno crearSistemaDeGestion() {
        Sistema.sistema = new Sistema();
        return Retorno.ok();
    }

    @Override
    public Retorno agregarEstudiante(String nombre, String apellido, int numero) {
        Estudiante unEstudiante = new Estudiante(nombre, apellido, numero);
        Nodo<Estudiante> nodoEstudiante = new Nodo(unEstudiante);

        //Error1: Verificar nombre y apellido != null y vacío
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) {
            return Retorno.error1();
        } else {
            //Error2: Verifica numeroEstudiante
            if (numero <= 0 || numero > 500000) {
                return Retorno.error2();
            } else {
                //Error3: Verifica si ya existe el estudiante    
                if (listaEstudiantes.existeElemento(unEstudiante)) {
                    return Retorno.error3();
                } else {
                    listaEstudiantes.agregarOrd(nodoEstudiante);
                    return Retorno.ok();
                }
            }
        }
    }

    @Override
    public Retorno obtenerEstudiante(int numero) {

        if (numero <= 0 || numero > 500000) {
            return Retorno.error1();
        }
        Estudiante aux = new Estudiante(numero);

        if (!listaEstudiantes.existeElemento(aux)) {
            return Retorno.error2();
        } else {
            Nodo<Estudiante> nodoEstudiante = listaEstudiantes.obtenerElemento(aux);
            Estudiante estudiante = nodoEstudiante.getDato();
            String retorno = estudiante.toString();
            return Retorno.ok(retorno);
        }
    }

    @Override
    public Retorno eliminarEstudiante(int numero) {

        //Error1: Verifica el número de estudiante
        if (numero <= 0 || numero > 500000) {
            return Retorno.error1();
        }
        Estudiante aux = new Estudiante(numero);

        //Error2: Verifica que el estudiante exista
        if (!listaEstudiantes.existeElemento(aux)) {
            return Retorno.error2();
        } else {
            Nodo<Estudiante> nodoEstudiante = listaEstudiantes.obtenerElemento(aux);
            Estudiante estudiante = nodoEstudiante.getDato();

            //Error3: Verifica que el estudiante nunca realizó prestamos
            if (estudiante.getListaPrestamosEst().cantidadElementos() == 0) {
                listaEstudiantes.eliminarElemento(aux);
                return Retorno.ok();
            } else {
                return Retorno.error3();
            }
        }
    }

    @Override
    public Retorno agregarLibro(String nombre, String ISBN, String categoria, int cantidad) {

        Libro unLibro = new Libro(nombre, ISBN, categoria, cantidad);
        Nodo<Libro> nodoLibro = new Nodo(unLibro);

        //Error1: Verificar que los atributos sean != null o vacío
        if (nombre == null || nombre.trim().isEmpty()
                || ISBN == null || ISBN.trim().isEmpty()
                || categoria == null || categoria.trim().isEmpty()) {
            return Retorno.error1();
        }

        //Error3: Verifica si el stock ingresado es > 0
        if (cantidad <= 0) {
            return Retorno.error3();
        } else {
            //Error2: Verifica si ya existe un libro con el ISBN ingresado
            if (listaLibros.existeElemento(unLibro)) {
                return Retorno.error2();
            } else {
                listaLibros.agregarOrd(nodoLibro);
                
                //agrego tambien a la lista de categorias la categoria del libro si ya no existe en esa lista
                if (!listaCategorias.existeElemento(categoria)) {
                    listaCategorias.agregarOrd(new Nodo<String>(categoria));
                }
                
                return Retorno.ok();
            }
        }
    }

    @Override
    public Retorno eliminarLibro(String ISBN) {
        if (ISBN == null || ISBN.isEmpty()) {
            return Retorno.error1();
        }
        //recorro la lista de libros para ubicar ese libro y me fijo si tien una lista de prestamos
        //si el count da >0, entonces tuvo prestamos y no se puede borrar
        Libro libroAEliminar = new Libro(ISBN);
        Nodo<Libro> nodoLibro = listaLibros.obtenerElemento(libroAEliminar);
        Libro libro = nodoLibro.getDato();

        //Preguntar que error retorna si el libro no existe 
        if (libro.getListaPrestamosLibro().cantidadElementos() > 0) {
            return Retorno.error2();
        }

        listaLibros.eliminarElemento(libro);
        pilaLibrosEliminados.apilar(libro);
        return Retorno.ok();
    }

    @Override
    public Retorno prestarLibro(String ISBN, int numero) {

        //Error1: ISBN vacío o null
        if (ISBN == null || ISBN.trim().isEmpty()) {
            return Retorno.error1();
        }

        //Error2: Verifica si existe un libro con el ISBN ingresado
        Libro libroIngresado = new Libro(ISBN);
        if (!listaLibros.existeElemento(libroIngresado)) {
            return Retorno.error2();
        }

        //Error3: Verifica el número de estudiante
        if (numero <= 0 || numero > 500000) {
            return Retorno.error3();
        }

        //Error4: Verifica si existe el estudiante
        Estudiante estudianteIngresado = new Estudiante(numero);
        if (!listaEstudiantes.existeElemento(estudianteIngresado)) {
            return Retorno.error4();
        }

        //Error5: Stock de libros != 0 
        Nodo<Libro> nodoCompletoLibro = listaLibros.obtenerElemento(libroIngresado);
        Libro libroCompleto = nodoCompletoLibro.getDato();

        if (nodoCompletoLibro.getDato().getStock() == 0) {

            reservarLibro(ISBN, numero);
            return Retorno.error5();
            //Todo: Ver si acá hay que ya crear la reserva del libro  
            //Reservar Libro > Sumarlo a la cola de reserva de libros 
        }

        //Error6: Prestamo activo del mismo libro || 8 < prestamos activos
        Nodo<Estudiante> nodoEstudiante = listaEstudiantes.obtenerElemento(estudianteIngresado);
        Estudiante estudiante = nodoEstudiante.getDato();
        int cantidadPrestamosActivos = estudiante.getPrestamosActivos();

        boolean tienePrestamoActivoDelMismoLibro = false;

        Lista<Prestamo> prestamosEstudiante = estudiante.getListaPrestamosEst();
        Nodo<Prestamo> actual = prestamosEstudiante.getInicio();

        while (actual != null) {
            Prestamo prestamo = actual.getDato();
            if (prestamo.equals(new Prestamo(libroCompleto))) {
                tienePrestamoActivoDelMismoLibro = true;
                break;
            }
            actual = actual.getSiguiente();
        }

        if (cantidadPrestamosActivos >= 8 || tienePrestamoActivoDelMismoLibro) {
            return Retorno.error6();
        }

        //Caso de éxito
        //Buscar el libro > nodoCompletoLibro Lo traje para ver el Stock
        //Buecar el estudiante > estudiante - Lo traje para ver la lista de prestamos activos
        //Setear la fecha actual > En el constructor
        //Crear un nuevo prestamo
        //Registrar prestamo en lista de prestamos activos estudiante
        //Agregar prestamo a lista de Prestamos del Libro 
        //Quitar 1 de stock de libros
        //Agregar prestamo a lista de Prestamos General
        Libro libro = nodoCompletoLibro.getDato(); //Para el alta
        Prestamo prestamoNuevo = new Prestamo(LocalDateTime.now(), libro, estudiante);
        Nodo<Prestamo> nodoPrestamo = new Nodo(prestamoNuevo);

        //Se agrega prestamo a la lista de prestamos del Libro
        libro.getListaPrestamosLibro().agregarInicio(prestamoNuevo);
        //Se disminuye el Stock del libro
        int Stock = libro.getStock();
        libro.setStock(Stock - 1);
        libro.setPrestamos(libro.getPrestamos() + 1); //aumento la cantidad de prestamos del libro

        //Se agrega prestamo a la lista de prestamos del estudiante (agregado cronologicamente)
        estudiante.getListaPrestamosEst().agregarOrd(nodoPrestamo);
//        int prestamosActivosEstudiante = estudiante.getPrestamosActivos();
//        prestamosActivosEstudiante++;

        estudiante.setPrestamosActivos(cantidadPrestamosActivos + 1);

        //Se agrega prestamo a la lista de prestamos general
        listaPrestamos.agregarInicio(prestamoNuevo);

        return Retorno.ok();

    }

    /*-----------------
    Métodos auxiliares
    -------------------*/
    @Override
    public int cantidadPrestamosActivos(int numeroEstudiante) {
        Estudiante estudianteIngresado = new Estudiante(numeroEstudiante);
        Nodo<Estudiante> nodoEstudiante = listaEstudiantes.obtenerElemento(estudianteIngresado);
        Estudiante estudiante = nodoEstudiante.getDato();
        Lista<Prestamo> prestamosEstudiante = estudiante.getListaPrestamosEst();

        int cantidadPrestamosActivos = 0;

        Nodo<Prestamo> aux = prestamosEstudiante.getInicio();
        while (aux != null && aux.getSiguiente() != null) {

            if (aux.getDato().getEstadoPrestamo() == "Activo") {
                cantidadPrestamosActivos++;
            }
            aux = aux.getSiguiente();
        }

        return cantidadPrestamosActivos;
    }

    @Override
    public Retorno reservarLibro(String ISBN, int numero) {

        //Error1: ISBN vacío o null
        if (ISBN == null || ISBN.trim().isEmpty()) {
            return Retorno.error1();
        }

        //Error2: Verifica si existe un libro con el ISBN ingresado
        Libro libroIngresado = new Libro(ISBN);
        if (!listaLibros.existeElemento(libroIngresado)) {
            return Retorno.error2();
        }

        //Error3: Verifica el número de estudiante
        if (numero <= 0 || numero > 500000) {
            return Retorno.error3();
        }

        //Error4: Verifica si existe el estudiante
        Estudiante estudianteIngresado = new Estudiante(numero);
        if (!listaEstudiantes.existeElemento(estudianteIngresado)) {
            return Retorno.error4();
        }

        //Error5: Stock de libros > 0 
        Nodo<Libro> nodoCompletoLibro = listaLibros.obtenerElemento(libroIngresado);
        if (nodoCompletoLibro.getDato().getStock() > 0) {
            return Retorno.error5();
        }

        //Caso de éxito
        //Hacer una cola inversa para recorrer menos
        //Traer el libro para agregarle a la cola de reservas al final que ya lo tengo
        //
        Reserva reservaNueva = new Reserva(ISBN, numero);
        Libro libro = nodoCompletoLibro.getDato();
        Cola<Reserva> colaReservaLibro = libro.getColaReservaLibros();
        colaReservaLibro.encolar(reservaNueva);

        return Retorno.ok();

    }

    @Override
    public Retorno devolverLibro(String ISBN, int numero) {

        //Error1: ISBN vacío o null
        if (ISBN == null || ISBN.trim().isEmpty()) {
            return Retorno.error1();
        }

        //Error2: Verifica si existe un libro con el ISBN ingresado
        Libro libroIngresado = new Libro(ISBN);
        if (!listaLibros.existeElemento(libroIngresado)) {
            return Retorno.error2();
        }

        //Error3: Verifica el número de estudiante
        if (numero <= 0 || numero > 500000) {
            return Retorno.error3();
        }

        //Error4: Verifica si existe el estudiante
        Estudiante estudianteIngresado = new Estudiante(numero);
        if (!listaEstudiantes.existeElemento(estudianteIngresado)) {
            return Retorno.error4();
        }
        Nodo<Estudiante> nodoEstudiante = listaEstudiantes.obtenerElemento(estudianteIngresado);
        Estudiante estudiante = nodoEstudiante.getDato();

        //Error5: Virifica que exista un prestamo activo del libro ingresado
        //para el estudiante ingresado
        Nodo<Libro> nodoLibro = listaLibros.obtenerElemento(libroIngresado);
        Libro libro = nodoLibro.getDato();

        Nodo<Prestamo> prestamoEstudiante = estudiante.getListaPrestamosEst().buscarPrestamoPorISBNYEstudiante(ISBN, estudiante);

        boolean tienePrestamoActivoDelMismoLibro = false;

        Nodo<Prestamo> actual = estudiante.getListaPrestamosEst().getInicio();
        while (actual != null) {
            Prestamo prestamo = actual.getDato();
            if (prestamo.equals(new Prestamo(libro))) {
                tienePrestamoActivoDelMismoLibro = true;
                break;
            }
            actual = actual.getSiguiente();
        }

        if (!tienePrestamoActivoDelMismoLibro) {
            return Retorno.error5();
        }

        //Caso de éxito
        //Pasa el estado del prestamo a "Finalizado" -
        //Pasar el prestamo de activo a finalizado en la lista
        ////Prestamo de estudiante -
        ////Prestamos general-
        ////Prestamos del libro-
        //Verifica si hay reservas de ese libro
        //Resta 1 de prestamosActivosEstudiante
        //Traigo los nodos de las listas de prestamo
        Nodo<Prestamo> prestamoLibro = libro.getListaPrestamosLibro().buscarPrestamoPorISBNYEstudiante(ISBN, estudiante);
        Nodo<Prestamo> prestamoGeneral = listaPrestamos.buscarPrestamoPorISBNYEstudiante(ISBN, estudiante);

        //Cambio el estado del prestamo a "Finalizado"
        prestamoLibro.getDato().setEstadoPrestamo("Finalizado");
        prestamoEstudiante.getDato().setEstadoPrestamo("Finalizado");
        prestamoGeneral.getDato().setEstadoPrestamo("Finalizado");

        //Verifica si hay reservas del libro
        Cola<Reserva> reservasLibro = libro.getColaReservaLibros();
        int cantidadReservas = reservasLibro.getCantidadnodos();

        if (cantidadReservas != 0) {
            //Resta reserva y presta el libro al primer estudiante que lo pidio
            Nodo<Reserva> ultimaReserva = reservasLibro.getFrente();
            int numeroEstudiante = ultimaReserva.getDato().getNumeroEstudiante();

            prestarLibro(ISBN, numeroEstudiante);
            //Desencolamos el inicio para no recorrer toda la cola
            reservasLibro.desencolar();
            return Retorno.ok();
        } else {
            //Suma 1 a stock 
            int stockLibro = libro.getStock();
            libro.setStock(stockLibro + 1);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno listarEstudiantes() {
        String retorno = listaEstudiantes.ListarDatos();
        return Retorno.ok(retorno);
    }

    @Override
    public Retorno listarLibros() {
        String retorno = listaLibros.ListarDatos();
        return Retorno.ok(retorno);
    }

    @Override
    public Retorno listarLibros(String categoria) {

        String retorno = "";
        //Error1: Verifica que la categoría no sea vacía y null 
        if (categoria == null || categoria.trim().isEmpty()) {
            return Retorno.error1();
        } else {
            Lista<Libro> listaAuxLibro = new Lista();
            Nodo<Libro> nodoLibro = listaLibros.getInicio();

            while (nodoLibro != null) {
                if (nodoLibro.getDato().getCategoria() == categoria) {
                    listaAuxLibro.agregarFinal(nodoLibro.getDato());
                }
                nodoLibro = nodoLibro.getSiguiente();
            }
            //Se Lista recursivo
            if (listaAuxLibro.cantidadElementos() != 0) {
                retorno = listaAuxLibro.ListarDatosREC();
            }

            return Retorno.ok(retorno);
        }
    }

    @Override
    public Retorno listarPrestamos(int numero) {
        
         //Error1: Verifica el número de estudiante
        if (numero <= 0 || numero > 500000) {
            return Retorno.error1();
        }

        //Error2: Verifica si existe el estudiante
        Estudiante estudianteIngresado = new Estudiante(numero);
        if (!listaEstudiantes.existeElemento(estudianteIngresado)) {
            return Retorno.error2();
        }

        Nodo<Estudiante> nodoEstudiante = listaEstudiantes.obtenerElemento(estudianteIngresado);
        Estudiante estudiante = nodoEstudiante.getDato();

        Lista<Prestamo> listaPrestamosEstudiante = estudiante.getListaPrestamosEst();
        String retorno = "";

        Lista<Prestamo> listaAux = new Lista();
        Nodo<Prestamo> nodoPrestamo = listaPrestamosEstudiante.getInicio();

        while (nodoPrestamo != null) {

            listaAux.agregarFinal(nodoPrestamo.getDato());

            nodoPrestamo = nodoPrestamo.getSiguiente();
        }
        
        retorno = listaAux.ListarDatos();
        
        return Retorno.ok(retorno);
    }

    @Override
    public Retorno librosMasPrestados() {
        Lista<Libro> masPrestados = new Lista<Libro>();
        String retorno = "";
        int cantMaxima = 0;
        Nodo<Libro> nodoLibro = listaLibros.getInicio();
        while (nodoLibro != null) {
            if (nodoLibro.getDato().getPrestamos() > cantMaxima) {
                cantMaxima = nodoLibro.getDato().getPrestamos();
                retorno = "";
                masPrestados.vaciar();
                masPrestados.agregarFinal(nodoLibro.getDato());
                retorno += nodoLibro.getDato().getNombreLibro() + "#" + nodoLibro.getDato().getISBN() + "#" + nodoLibro.getDato().getCategoria() + "#" + nodoLibro.getDato().getPrestamos();
            } else if (nodoLibro.getDato().getPrestamos() == cantMaxima) {
                masPrestados.agregarFinal(nodoLibro.getDato());
                retorno += "|" + nodoLibro.getDato().getNombreLibro() + "#" + nodoLibro.getDato().getISBN() + "#" + nodoLibro.getDato().getCategoria() + "#" + nodoLibro.getDato().getPrestamos();
            }
            nodoLibro = nodoLibro.getSiguiente();
        }

        return Retorno.ok(retorno);
    }

    @Override
    public Retorno deshacerEliminaciones(int n) {
        if (n <= 0) {
            return Retorno.error1();
        }
        // lista vacia de libros
        //defino el nodovacio de libro
        //declaro un while para que desapile n veces
        //nodo= pila.cima
        //String retorno = listaLibros.ListarDatos();
        //return Retorno.ok(retorno);
        String retorno = "";
        Lista<Libro> listaDeshacerEliminados = new Lista();

        while (n > 0 && pilaLibrosEliminados.getCantidadnodos() > 0) { ///chequear con e system 

            Nodo<Libro> nodoLibroEliminado = pilaLibrosEliminados.getCima();
            pilaLibrosEliminados.desapilar();
            listaDeshacerEliminados.agregarInicio((nodoLibroEliminado.getDato()));
            listaLibros.agregarOrd(new Nodo<Libro>(nodoLibroEliminado.getDato()));
            n--;
        }
        retorno = listaDeshacerEliminados.ListarDatos();
        System.out.println(retorno);
        return Retorno.ok(retorno);
    }

    @Override
    public Retorno cantidadPrestamosActivos(String ISBN) {

        //Error1: ISBN vacío o null
        if (ISBN == null || ISBN.trim().isEmpty()) {
            return Retorno.error1();
        }

        Libro libroIngresado = new Libro(ISBN);
        Nodo<Libro> nodoLibro = listaLibros.obtenerElemento(libroIngresado);
        Libro libro = nodoLibro.getDato();
        Lista<Prestamo> prestamosLibro = libro.getListaPrestamosLibro();

        int cantidadPrestamosActivos = 0;

        Nodo<Prestamo> aux = prestamosLibro.getInicio();
        while (aux != null && aux.getSiguiente() != null) {
            if (aux.getDato().getEstadoPrestamo() == "Activo") {
                cantidadPrestamosActivos++;
            }
            aux = aux.getSiguiente();
        }

        if (aux.getDato().getEstadoPrestamo() == "Activo") {
            cantidadPrestamosActivos++;
        }
        //Creo una variable String para castear la cantidad
        String retorno = String.valueOf(cantidadPrestamosActivos);
        return Retorno.ok(retorno);
        //return cantidadPrestamosActivos;
    }

    @Override
    public Retorno prestamosXCategoría() {
        //Lista<Integer> cantidades = new Lista();
        String retorno = "";
        Nodo<String> nodoCategoria = listaCategorias.getInicio();
        while (nodoCategoria != null) {
            int cantidadDeLaCategoria = 0;
            Nodo<Libro> nodoLibro = listaLibros.getInicio();
            while (nodoLibro != null) {
                if (nodoLibro.getDato().getCategoria() == nodoCategoria.getDato()) {
                    cantidadDeLaCategoria++;
                }
                nodoLibro = nodoLibro.getSiguiente();
            }
            
            retorno += nodoCategoria.getDato() + "#" + cantidadDeLaCategoria;
            if(nodoCategoria.getSiguiente() != null)
            {
                retorno += "|";
            }
            nodoCategoria = nodoCategoria.getSiguiente();
        }
        
        return Retorno.ok(retorno);
    }

}
