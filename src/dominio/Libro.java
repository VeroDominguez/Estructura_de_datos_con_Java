package dominio;

import tads.Lista;
import java.time.LocalDateTime;
import tads.Cola;

public class Libro implements Comparable<Libro> {

    private String nombreLibro;
    private String ISBN;
    private String categoria;
    private int stock;
    private int prestamos; //agregado para contar los prestamos de un libro
    private Lista<Prestamo> listaPrestamosLibro;
    private Cola<Reserva> colaReservaLibros;

    //Constructor
    public Libro(String nombreLibro, String ISBN, String categoria, int stock) {
        this.nombreLibro = nombreLibro;
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.stock = stock;
        this.listaPrestamosLibro = new Lista();
        this.colaReservaLibros = new Cola<Reserva>();
        this.prestamos = 0;
    }

    //Constructor con ISBN 
    public Libro(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public int getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(int prestamos) {
        this.prestamos = prestamos;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Lista<Prestamo> getListaPrestamosLibro() {
        return listaPrestamosLibro;
    }

    public Cola<Reserva> getColaReservaLibros() {
        return colaReservaLibros;
    }

    public void setColaReservaLibros(Cola<Reserva> colaReservaLibros) {
        this.colaReservaLibros = colaReservaLibros;
    }

    @Override
    public int compareTo(Libro o) {
        return this.getISBN().compareTo(o.ISBN);
    }

    @Override
    public String toString() {
        String datos = this.nombreLibro + "#" + this.ISBN + "#" + this.categoria;
        return datos;
    }

}
