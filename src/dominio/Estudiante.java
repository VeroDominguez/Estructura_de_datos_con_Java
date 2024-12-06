
package dominio;

import tads.Lista;


public class Estudiante implements Comparable<Estudiante> {
       
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private int numeroEstudiante;
    private Lista<Prestamo> listaPrestamosEst;
    int prestamosActivos;

    //Constructor
    public Estudiante(String nombreEstudiante, String apellidoEstudiante, int numeroEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.numeroEstudiante = numeroEstudiante;
        this.listaPrestamosEst = new Lista();
        int prestamosActivos = 0; 
    }
    
    public Estudiante(int numeroEstudiante) {
        this.nombreEstudiante = "";
        this.apellidoEstudiante = "";
        this.numeroEstudiante = numeroEstudiante;
        this.listaPrestamosEst = new Lista();
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }   

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public int getNumeroEstudiante() {
        return numeroEstudiante;
    }

    public void setNumeroEstudiante(int numeroEstudiante) {
        this.numeroEstudiante = numeroEstudiante;
    }
    
    public Lista<Prestamo> getListaPrestamosEst() {
        return listaPrestamosEst;
    }

    public int getPrestamosActivos() {
        return prestamosActivos;
    }

    public void setPrestamosActivos(int prestamosActivos) {
        this.prestamosActivos = prestamosActivos;
    }
    
    

    @Override
    public int compareTo(Estudiante o) {
        return Integer.compare(this.numeroEstudiante, o.getNumeroEstudiante());
    }        
    
    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        } else {
            Estudiante e =(Estudiante) obj;
            
        return e.numeroEstudiante == this.numeroEstudiante;
        }
    }
    
    @Override
    public String toString(){
        String datos = this.nombreEstudiante + "#" + this.apellidoEstudiante + "#" + this.numeroEstudiante;
        return datos ;
    }
    
       
}
