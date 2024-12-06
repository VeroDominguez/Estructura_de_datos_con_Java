
package dominio;

public class Reserva implements Comparable <Reserva> {
    
    private String ISBN;
    private int numeroEstudiante;

    public Reserva(String ISBN, int numeroEstudiante) {
        this.ISBN = ISBN;
        this.numeroEstudiante = numeroEstudiante;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNumeroEstudiante() {
        return numeroEstudiante;
    }

    public void setNumeroEstudiante(int numeroEstudiante) {
        this.numeroEstudiante = numeroEstudiante;
    }

    @Override
    public int compareTo(Reserva o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
