package sistemaAutogestion;

import dominio.Estudiante;
import dominio.Libro;
import dominio.Prestamo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IObligatorioTestCreados {

    private IObligatorio miSistema;

    public IObligatorioTestCreados() {
        miSistema = new Sistema();
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    //**************
    //Test creados
    //**************
    //--------------
    //Agregar libro
    //--------------
    @Test
    public void testAgregarLibroOk() {
        Retorno ret = miSistema.agregarLibro("Libro1", "ISBN1", "Ficción", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testAgregarLibroError1() {
        // Verifica si el nombre del libro es vacío o nulo
        Retorno ret = miSistema.agregarLibro("", "ISBN2", "Ficción", 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarLibro(null, "ISBN2", "Ficción", 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Verifica si el ISBN es vacío o nulo
        ret = miSistema.agregarLibro("Libro1", "", "Ficción", 30);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarLibro("Libro1", null, "Ficción", 2);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Verifica si la categoría es vacía o nula
        ret = miSistema.agregarLibro("Libro3", "ISBN3", "", 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarLibro("Libro3", "ISBN3", null, 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Verifica si nombre, ISBN y categoría son vacíos o nulos
        ret = miSistema.agregarLibro("", "", "", 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarLibro(null, null, null, 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

    }

    @Test
    public void testAgregarLibroError2() {
        // Verifica si ya existe un libro con el mismo ISBN
        miSistema.agregarLibro("Libro1", "ISBN1", "Ficción", 10);
        Retorno ret = miSistema.agregarLibro("Libro2", "ISBN1", "Drama", 5);
        
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    @Test
    public void testAgregarLibroError3() {
        // Verifica si el stock es menor o igual a cero
        Retorno ret = miSistema.agregarLibro("Libro4", "ISBN4", "Ficción", 0);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);

        ret = miSistema.agregarLibro("Libro5", "ISBN5", "Ficción", -5);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }

    //-------------------
    //Agregar Estudiante
    //-------------------
    @Test
    public void testAgregarEstudianteOk() {
        Retorno ret = miSistema.agregarEstudiante("Juan", "Pérez", 1234);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.agregarEstudiante("María", "González", 5678);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.agregarEstudiante("Carlos", "Rodríguez", 9101);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testAgregarEstudianteError1() {
        // Nombre vacío
        Retorno ret = miSistema.agregarEstudiante("", "Pérez", 1234);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Nombre null
        ret = miSistema.agregarEstudiante(null, "Pérez", 1234);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Apellido vacío
        ret = miSistema.agregarEstudiante("Juan", "", 1234);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Apellido null
        ret = miSistema.agregarEstudiante("Juan", null, 1234);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Ambos nombre y apellido vacíos
        ret = miSistema.agregarEstudiante("", "", 1234);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Ambos nombre y apellido null
        ret = miSistema.agregarEstudiante(null, null, 1234);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testAgregarEstudianteError2() {
        // Número de estudiante inválido (0)
        Retorno ret = miSistema.agregarEstudiante("Ana", "López", 0);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        // Número de estudiante negativo
        ret = miSistema.agregarEstudiante("Ana", "López", -50);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        // Número de estudiante mayor a 500000
        ret = miSistema.agregarEstudiante("Ana", "López", 500001);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        ret = miSistema.agregarEstudiante("Ana", "López", 600000);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    @Test
    public void testAgregarEstudianteError3() {
        // Agregamos un estudiante
        miSistema.agregarEstudiante("Lucas", "Martínez", 2222);

        // Intentamos agregar el mismo estudiante con el mismo número de estudiante
        Retorno ret = miSistema.agregarEstudiante("Lucas", "Martínez", 2222);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }

    //-------------------
    //Obtener Estudiante
    //-------------------
    // Test para obtener un estudiante correctamente
    @Test
    public void testObtenerEstudianteOk() {
        miSistema.agregarEstudiante("Ana", "Perez", 123123);
        Retorno ret = miSistema.obtenerEstudiante(123123);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Ana#Perez#123123", ret.valorString);
    }

    // Test para el error 1: número de estudiante menor o igual a cero o mayor a 500000
    @Test
    public void testObtenerEstudianteError1() {
        // Número menor a 0
        Retorno ret = miSistema.obtenerEstudiante(-1);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Número igual a 0
        ret = miSistema.obtenerEstudiante(0);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Número mayor a 500000
        ret = miSistema.obtenerEstudiante(500001);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    // Test para el error 2: Estudiante no encontrado (no existe en la lista)
    @Test
    public void testObtenerEstudianteError2() {
        // Estudiante no agregado previamente
        Retorno ret = miSistema.obtenerEstudiante(123124);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    // Test con varios estudiantes para verificar que se obtiene el correcto
    @Test
    public void testObtenerEstudianteVarios() {
        miSistema.agregarEstudiante("Carlos", "Garcia", 111111);
        miSistema.agregarEstudiante("Laura", "Martinez", 222222);
        miSistema.agregarEstudiante("Luis", "Fernandez", 333333);

        // Verificar que se obtiene el correcto
        Retorno ret = miSistema.obtenerEstudiante(222222);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Laura#Martinez#222222", ret.valorString);
    }

    //-------------------
    //Eliminar Estudiante
    //-------------------
    @Test
    public void testEliminarEstudianteOk() {
        miSistema.agregarEstudiante("Juan", "Perez", 1122);

        // Simulación de que el estudiante no tiene préstamos activos
        Retorno ret = miSistema.eliminarEstudiante(1122);

        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testEliminarEstudianteError1() {
        Retorno ret = miSistema.eliminarEstudiante(0);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.eliminarEstudiante(-5);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Fuera del rango permitido
        ret = miSistema.eliminarEstudiante(500001);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.eliminarEstudiante(999999);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testEliminarEstudianteError2() {
        // El estudiante no existe en la lista de estudiantes
        Retorno ret = miSistema.eliminarEstudiante(2222);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    @Test
    public void testEliminarEstudianteError3() {
        // Primero agregamos el estudiante
        miSistema.agregarEstudiante("Romina", "Perez", 3322);



        // Simulación de que el estudiante tiene préstamos 
        Retorno ret = miSistema.eliminarEstudiante(3322);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);

        // Intentamos eliminar el estudiante, esto debería devolver ERROR_3
        ret = miSistema.eliminarEstudiante(3322);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }

    //------------------
    //Listar Estudiantes
    //------------------    
    @Test
    public void testListarEstudiantesVacio() {
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("", ret.valorString);
    }

    @Test
    public void testListarEstudiantesUnElemento() {
        miSistema.agregarEstudiante("Carlos", "González", 1254);
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Carlos#González#1254", ret.valorString);
    }

    @Test
    public void testListarEstudiantesIngresoOrdenado() {
        miSistema.agregarEstudiante("Lucía", "Pérez", 1563);
        miSistema.agregarEstudiante("Martín", "Sosa", 2354);
        miSistema.agregarEstudiante("Ana", "Fernández", 3332);
        miSistema.agregarEstudiante("Pedro", "Rodríguez", 4411);
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Lucía#Pérez#1563|Martín#Sosa#2354|Ana#Fernández#3332|Pedro#Rodríguez#4411", ret.valorString);
    }

    @Test
    public void testListarEstudiantesIngresoNoOrdenado() {
        miSistema.agregarEstudiante("Gabriel", "López", 3422);
        miSistema.agregarEstudiante("Natalia", "Silva", 1245);
        miSistema.agregarEstudiante("Diego", "Martínez", 5423);
        miSistema.agregarEstudiante("Laura", "García", 3241);
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Natalia#Silva#1245|Laura#García#3241|Gabriel#López#3422|Diego#Martínez#5423", ret.valorString);
    }

    //-------------------
    //Listar Libros
    //-------------------
    @Test
    public void testListarLibrosVacio() {
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals(ret.valorString, "");
    }

    @Test
    public void testListarLibrosUnElemento() {
        miSistema.agregarLibro("Cien años de soledad", "ISBN100", "Realismo Mágico", 25);
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Cien años de soledad#ISBN100#Realismo Mágico", ret.valorString);
    }

    @Test
    public void testListarLibrosIngresoOrdenado() {
        miSistema.agregarLibro("El Principito", "ISBN101", "Fábula", 30);
        miSistema.agregarLibro("Don Quijote de la Mancha", "ISBN102", "Clásico", 45);
        miSistema.agregarLibro("1984", "ISBN103", "Distopía", 50);
        miSistema.agregarLibro("Matar a un ruiseñor", "ISBN104", "Ficción", 40);
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("El Principito#ISBN101#Fábula|Don Quijote de la Mancha#ISBN102#Clásico|1984#ISBN103#Distopía|Matar a un ruiseñor#ISBN104#Ficción", ret.valorString);
    }

    @Test
    public void testListarLibrosIngresoNoOrdenado() {
        miSistema.agregarLibro("1984", "ISBN103", "Distopía", 50);
        miSistema.agregarLibro("El Principito", "ISBN101", "Fábula", 30);
        miSistema.agregarLibro("Matar a un ruiseñor", "ISBN104", "Ficción", 40);
        miSistema.agregarLibro("Don Quijote de la Mancha", "ISBN102", "Clásico", 45);
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("El Principito#ISBN101#Fábula|Don Quijote de la Mancha#ISBN102#Clásico|1984#ISBN103#Distopía|Matar a un ruiseñor#ISBN104#Ficción", ret.valorString);
    }

    //---------------------------------
    //Listar Libros por categoría (REC)
    //---------------------------------
    @Test
    public void testListarLibrosCategoriaVacia() {
        Retorno ret = miSistema.listarLibros("");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado); // Suponiendo que ERROR_1 es el retorno para categoría vacía
    }

    @Test
    public void testListarLibrosCategoriaNull() {
        Retorno ret = miSistema.listarLibros(null);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado); // Suponiendo que ERROR_1 es el retorno para categoría null
    }

    @Test
    public void testListarLibrosCategoriaSinElementos() {
        miSistema.agregarLibro("Cien años de soledad", "ISBN100", "Realismo Mágico", 25);
        Retorno ret = miSistema.listarLibros("Ciencia Ficción");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("", ret.valorString); // No hay libros en la categoría "Ciencia Ficción"
    }

    @Test
    public void testListarLibrosCategoriaConUnElemento() {
        miSistema.agregarLibro("1984", "ISBN101", "Distopía", 30);
        Retorno ret = miSistema.listarLibros("Distopía");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("1984#ISBN101#Distopía", ret.valorString);
    }

    @Test
    public void testListarLibrosCategoriaConVariosElementosOrdenados() {
        miSistema.agregarLibro("Fahrenheit 451", "ISBN102", "Distopía", 25);
        miSistema.agregarLibro("1984", "ISBN101", "Distopía", 30);
        Retorno ret = miSistema.listarLibros("Distopía");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("1984#ISBN101#Distopía|Fahrenheit 451#ISBN102#Distopía", ret.valorString); // Listado ordenado por ISBN
    }

    @Test
    public void testListarLibrosCategoriasVarias() {

        miSistema.agregarLibro("Dune", "ISBN106", "Ciencia Ficción", 35);
        miSistema.agregarLibro("El hobbit", "ISBN105", "Fantasía", 40);
        miSistema.agregarLibro("Neuromancer", "ISBN107", "Ciencia Ficción", 30);
        miSistema.agregarLibro("El hobbit 2", "ISBN111", "Fantasía", 40);
        miSistema.agregarLibro("Dune 2", "ISBN121", "Ciencia Ficción", 35);
        miSistema.agregarLibro("Neuromancer 2", "ISBN190", "Ciencia Ficción", 30);
        miSistema.agregarLibro("Harry Potter y la camara secreta", "ISBN180", "Fantasía", 45);
        miSistema.agregarLibro("Harry Potter y el prisionero de Algoritmos", "ISBN181", "Fantasía", 45);
        miSistema.agregarLibro("El señor de los anillos", "ISBN103", "Fantasía", 50);
        miSistema.agregarLibro("Harry Potter y la piedra filosofal", "ISBN104", "Fantasía", 45);

        Retorno ret = miSistema.listarLibros("Fantasía");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("El señor de los anillos#ISBN103#Fantasía|Harry Potter y la piedra filosofal#ISBN104#Fantasía|El hobbit#ISBN105#Fantasía|El hobbit 2#ISBN111#Fantasía|Harry Potter y la camara secreta#ISBN180#Fantasía|Harry Potter y el prisionero de Algoritmos#ISBN181#Fantasía", ret.valorString);
    }

    @Test
    public void testListarLibrosCategoriaSinLibros() {
        miSistema.agregarLibro("Neuromancer", "ISBN107", "Ciencia Ficción", 30);
        Retorno ret = miSistema.listarLibros("Fantasía");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("", ret.valorString); // No hay libros en la categoría "Fantasía"
    }

    //*************************
    //Test creados 2da entrega
    //*************************
    //--------------
    //Prestar Libro 
    //--------------
    @Test
    public void testPrestarLibroError1() {
        Retorno ret = miSistema.prestarLibro("", 123123);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.prestarLibro(null, 123123);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testPrestarLibroError2() {
        //Verifica si existe el libro
        Retorno ret = miSistema.prestarLibro("ISBN200", 123123);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    @Test
    public void testPrestarLibroError3() {
        //Verifica el número de estudiante
        Retorno ret = miSistema.agregarLibro("Dune", "ISBN100", "Ciencia Ficción", 35);
                
        miSistema.prestarLibro("ISBN100", -1);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);

        ret = miSistema.prestarLibro("ISBN100", 500001);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }

    @Test
    public void testPrestarLibroError4() {
        //Error4: Verifica si existe el estudiante
        miSistema.agregarLibro("Dune", "ISBN106", "Ciencia Ficción", 35);
        miSistema.agregarLibro("El hobbit", "ISBN105", "Fantasía", 40);

        Retorno ret = miSistema.prestarLibro("ISBN106", 123456);
        assertEquals(Retorno.Resultado.ERROR_4, ret.resultado);
    }

    @Test
    public void testPrestarLibroError5() {
        //Implementar prestar libro para probar el test porque no se puede 
        //agregar un libro con stock 0 por punto 2.5 Error 3
        miSistema.agregarLibro("Estructuras de Datos", "ISBN100", "Programación", 1);
        miSistema.agregarEstudiante("Gabriel", "López", 3422);

        Retorno ret = miSistema.prestarLibro("ISBN100", 3422);
        assertEquals(Retorno.Resultado.ERROR_5, ret.resultado);
    }
    @Test
    public void testPrestarLibroError6() {
        miSistema.agregarEstudiante("Ana", "Pérez", 123123);
        miSistema.agregarLibro("Estructuras de Datos", "ISBN100", "Programación", 10);

        // Simulación de que el estudiante ya tiene el máximo de préstamos activos
        Retorno ret = miSistema.prestarLibro("ISBN100", 123123);
        assertEquals(Retorno.Resultado.ERROR_6, ret.resultado);
    }
    @Test
    public void testPrestarLibroOk() {
        // Configuración inicial: agregar un libro y un estudiante
        miSistema.agregarLibro("Estructuras de Datos", "ISBN100", "Programación", 5); // 5 ejemplares en stock
        miSistema.agregarEstudiante("Ana", "Pérez", 123123);

        // Realizar el préstamo
        Retorno ret = miSistema.prestarLibro("ISBN100", 123123);

        // Verificar que el resultado es exitoso
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    //----------------------------------
    // 3.7 Cantidad de prestamos activos
    //----------------------------------
    @Test
    public void testCantidadPrestamosActivosError1() {
        // Caso ISBN vacío
        Retorno ret = miSistema.cantidadPrestamosActivos("");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        // Caso ISBN null
        ret = miSistema.cantidadPrestamosActivos(null);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

//    @Test
//    public void testCantidadPrestamosActivosError2() {
//        // ISBN de un libro que no existe en el sistema
//        Retorno ret = miSistema.cantidadPrestamosActivos("ISBN404");
//        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
//    }

//    @Test
//    public void testCantidadPrestamosActivosOkSinPrestamos() {
//        // Agregar un libro sin préstamos activos
//        miSistema.agregarLibro("Estructuras de Datos", "ISBN100", "Programación", 5);
//
//        // Verificar que la cantidad de préstamos activos es 0
//        Retorno ret = miSistema.cantidadPrestamosActivos("ISBN100");
//        assertEquals(Retorno.Resultado.OK, ret.resultado);
//        assertEquals("0", ret.valorString);
//    }

//    @Test
//    public void testCantidadPrestamosActivosOkConPrestamos() {
//        // Configuración inicial: agregar libro y estudiantes
//        miSistema.agregarLibro("Estructuras de Datos", "ISBN100", "Programación", 5);
//        miSistema.agregarEstudiante("Ana", "Pérez", 123123);
//        miSistema.agregarEstudiante("Luis", "González", 234234);
//
//        // Realizar préstamos
//        miSistema.prestarLibro("ISBN100", 123123);
//        miSistema.prestarLibro("ISBN100", 234234);
//
//        // Verificar que la cantidad de préstamos activos es 2
//        Retorno ret = miSistema.cantidadPrestamosActivos("ISBN100");
//        assertEquals(Retorno.Resultado.OK, ret.resultado);
//        assertEquals("2", ret.valorString);
//    }

}
