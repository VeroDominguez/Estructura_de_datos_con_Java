package dominio;

import java.time.LocalDateTime;

public class Prestamo implements Comparable<Prestamo> {

    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevolucion;
    private String estadoPrestamo; //Estados: "Activo" o "Devuelto"
    private Libro libro;
    private Estudiante estudiante;

    //Constructor
    public Prestamo(LocalDateTime fechaPrestamo, Libro libro, Estudiante estudiante) {

        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null;
        this.estadoPrestamo = "Activo";
        this.libro = libro;
        this.estudiante = estudiante;
    }

    //Constructor para test de entrega 1 - Metodo eliminarEstudiante: Error3 
    public Prestamo(Estudiante estudiante) {

        this.estudiante = estudiante;
    }

    public Prestamo(Libro libro) {
        this.libro = libro;
    }

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public int compareTo(Prestamo o) {
        return o.fechaPrestamo.compareTo(this.fechaPrestamo);
    }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        } else {
            Prestamo prestamo = (Prestamo) obj;

            // Comparar ISBN y estado del préstamo
            return this.libro.getISBN().equals(prestamo.libro.getISBN())
                    && this.estadoPrestamo.equals("Activo");
        }
    }

    @Override
    public String toString() {
        String datos = this.getLibro().getNombreLibro() 
                + "#" + this.getLibro().getISBN() 
                + "#" + this.getLibro().getCategoria() 
                + "#" + this.getEstadoPrestamo();
        return datos;
    }

    
}
