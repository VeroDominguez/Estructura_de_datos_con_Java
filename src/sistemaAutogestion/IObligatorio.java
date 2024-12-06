package sistemaAutogestion;

public interface IObligatorio {

    /*
    **************** REGISTROS **************************************
     */
    //PRE:
    //POST: Se crea el sistema general vacío con un New Sistema
    public Retorno crearSistemaDeGestion();

    //PRE: nombre y apellido != null y no vacíos
    //No existe el numeroEstudiante ingresado
    //0 < numero <= 500000
    //POST: Retorna error1 si nombre y/o apellido son vacíos o nulos
    //Retorna error2 si numero < 0 || numero > 500000
    //Sino crea un estudiante y lo agrega a la listaEstudientes ordenado por numero 
    public Retorno agregarEstudiante(String nombre, String apellido, int numero);

    //PRE: 0 < numero <= 500000
    //Existe el numeroEstudiante solicitado
    //POST: Retorna error1 si numero < 0 || numero > 500000
    //Retorna error2 si no existe el estudiante
    //Sino busca estudiante en listaEstudiantes y devuelve los datos detallados en un String
    public Retorno obtenerEstudiante(int numero);

    //PRE: 0 < numero <= 500000
    //Existe el numeroEstudiante solicitado
    //Estudiante no debe tener prestamos 
    //POST: Elimina el estudiante indicado
    public Retorno eliminarEstudiante(int numero);

    //PRE: nombre == null y no vacío
    //apellido == null y no vacío
    //categoría == null y no vacía
    //No existe el ISBM ingresado
    //0 < cantidad 
    //POST: Retorna error1 si nombre,apellido y/o caegoria son vacíos o nulos
    //Retorna error2 si ya existe un libro con el ISBN ingresado
    //Sino crea un objeto libro y lo agrega a la listaLibros ordenado por ISBN   
    public Retorno agregarLibro(String nombre, String ISBN, String categoría, int cantidad);

    //pre:      post:
    public Retorno eliminarLibro(String ISBN);

    /*
    **************** GESTIÓN DE PRESTAMOS **************************************
     */
    //pre:      post:
    public Retorno prestarLibro(String ISBN, int numero);
    //pre:      post:

    public Retorno reservarLibro(String ISBN, int numero);

    //pre:      post:
    Retorno devolverLibro(String ISBN, int numero);

    /*
    **************** REPORTES Y CONSULTAS **************************************
     */
    //PRE: listaEstudiantes != null
    //POST: Retorna ok y lista los estudiantes en un String, ordenados por numeroEstudiante
    public Retorno listarEstudiantes();

    //PRE: listaLibros != null
    //POST: Retorna ok y lista los libros en un String, ordenados por ISBN
    public Retorno listarLibros();

    //PRE: listaLibros != null
    //categoría != null y no vacía
    //POST: Retorna error1 si la categoría es vacía o null
    //Retorna ok y lista los libros filtrada por la categoria ingresada,
    //ordenados por ISBN en un String
    public Retorno listarLibros(String categoria);
    //pre:      post: 

    public Retorno listarPrestamos(int numero);

    //pre:      post: 
    public Retorno librosMasPrestados();

    //pre:      post: 
    public Retorno deshacerEliminaciones(int n);
    //pre:      post:

    public Retorno cantidadPrestamosActivos(String ISBN);

    //pre:      post:
    public Retorno prestamosXCategoría();

    /**
     * ***
     * Metodo agregado para test
     */
    //Método creado para probar el Error3 del punto 2.4 Eliminar Estudiante
//    public void prestarLibroTestEstudiante(int numero);

    /*-----------------
    Métodos auxiliares
    -------------------*/
    public int cantidadPrestamosActivos (int numeroEstudiante); 
    
}
