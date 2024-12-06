package sistemaAutogestion;

public class BIBLIOTECAMAIN {

    public static void main(String[] args) {
        // TODO code application logic here
        Sistema s = new Sistema();
        Prueba p = new Prueba();
        juegodeprueba(s,p);
    }
    public static void juegodeprueba(Sistema s,Prueba p){
    
        //2.1. Crear Sistema de Gestión
        p.ver(s.crearSistemaDeGestion().resultado, Retorno.Resultado.OK, "se crea sistema");
        
        //2.2.Agregar Estudiante
        
        System.out.println("2.2.Agregar Estudiante: Agregamos 10 estudiantes ok");
        
        p.ver(s.agregarEstudiante("Luis", "Suarez", 1).resultado, Retorno.Resultado.OK, "OK: Se agrego a Luis Suarez");
        p.ver(s.agregarEstudiante("Edi", "Cavani", 2).resultado, Retorno.Resultado.OK, "OK: Se agrego a Edi Cavani");
        p.ver(s.agregarEstudiante("AndrES", "lIMA", 3).resultado, Retorno.Resultado.OK, "OK: Andres Lima ");
        p.ver(s.agregarEstudiante("Darwin", "Nuñez", 4).resultado, Retorno.Resultado.OK, "OK: Se agrego a Darwin Nuñez");
        p.ver(s.agregarEstudiante("Hugo", "Olivera", 5).resultado, Retorno.Resultado.OK, "OK: Se agrego a Hugo Olivera");
        p.ver(s.agregarEstudiante("Carlos", "Soca", 6).resultado, Retorno.Resultado.OK, "OK: Se agrego a Carlos Soca");
        p.ver(s.agregarEstudiante("Diego", "Maradona", 7).resultado, Retorno.Resultado.OK, "OK: Se agrego a Diego Maradona");
        p.ver(s.agregarEstudiante("Leo", "Messi", 8).resultado, Retorno.Resultado.OK, "OK: Se agrego a Leo Messi");
        p.ver(s.agregarEstudiante("Roberto", "Carlos", 9).resultado, Retorno.Resultado.OK, "OK: Se agrego a Roberto Carlos");
        p.ver(s.agregarEstudiante("Diego", "Polenta", 10).resultado, Retorno.Resultado.OK, "OK: se agrego a Diego Polenta");
      
       //--------------------------------------------------------------------------        
       //Controlamos que esten los estudiantes agregados.
        System.out.println("Listado de estudiantes agregados OK");
        System.out.println(s.listarEstudiantes().valorString);
       //--------------------------------------------------------------------------
        
        System.out.println("2.2.Agregar Estudiante: Agregamos estudiante con error1 error 2 y error 3");

        p.ver(s.agregarEstudiante("", "Polenta", 11).resultado, Retorno.Resultado.ERROR_1, "ERROR 1 : Falta primer parametro");
        p.ver(s.agregarEstudiante("Carlos", "", 12).resultado, Retorno.Resultado.ERROR_1, "ERROR 1 : Falta segundo parametro");
        p.ver(s.agregarEstudiante("", "", 12).resultado, Retorno.Resultado.ERROR_1, "ERROR 1 : Faltan los 2 parametros");
        p.ver(s.agregarEstudiante("Diego", "Aguirre", -1).resultado, Retorno.Resultado.ERROR_2, "ERROR 2 : Numero de estudiante fuera de rango");
        p.ver(s.agregarEstudiante("Luis", "Suarez", 1).resultado, Retorno.Resultado.ERROR_3, "Error 3: Se intento agregar un estudiante existente");
 
       //-------------------------------------------------------------------------- ----------------------------------       
       //Controlamos que esten los estudiantes agregados y no se hayan agregado los que tienen error.
        System.out.println("Listado de estudiantes agregados para ver que no hayan agregado los que tienen error");
        System.out.println(s.listarEstudiantes().valorString);
       //--------------------------------------------------------------------------------------------------------------
                
//2.3.Obtener Estudiante

        p.ver(s.obtenerEstudiante(1).resultado, Retorno.Resultado.OK, "OK: se obtuvo estudiante");
        p.ver(s.obtenerEstudiante(-1).resultado, Retorno.Resultado.ERROR_1, "Error 1: numero estudiante fuera de rango");
        p.ver(s.obtenerEstudiante(20).resultado, Retorno.Resultado.ERROR_2, "Error 2 - el estudiante 20 no existe");

//2.4. Eliminar Estudiante

        System.out.println("2.3.Eliminar Estudiante: ok error 1 error 2");

        p.ver(s.eliminarEstudiante(10).resultado, Retorno.Resultado.OK, "OK: Se elimina estudiante 10");
        p.ver(s.eliminarEstudiante(-10).resultado, Retorno.Resultado.ERROR_1, "Error 1: Se intenta eliminar estudiante con nro fuera de rango)");
        p.ver(s.eliminarEstudiante(20).resultado, Retorno.Resultado.ERROR_2, "Error 2 - Se intenta eliminar un estudiante que no existe ");
        
        
        // se agrega un libro y se presta a un estudiante
        p.ver(s.agregarLibro("Libro 0", "0", "Categoria 0", 3).resultado, Retorno.Resultado.OK, "Ok se agrega libro 0");
        p.ver(s.prestarLibro("0", 1).resultado, Retorno.Resultado.OK, "OK se agrega prestamo a estudiante 1");
        
        p.ver(s.eliminarEstudiante(1).resultado, Retorno.Resultado.ERROR_3, "Error 3 - Se intenta eliminar estudiante que tiene prestamos");

        
       //-------------------------------------------------------------------------- ----------------------------------       
       //Controlamos que esten los estudiantes agregados y no se hayan eliminar  los que tienen error.
        System.out.println("Listado de estudiantes luego de eliminar estudiante 10 ok, estudiante 1 con error ");
        System.out.println(s.listarEstudiantes().valorString);
       //--------------------------------------------------------------------------------------------------------------
          
        
// 2.5.Agregar Libro

        p.ver(s.agregarLibro("Libro 1", "1", "Categoria 1", 10).resultado, Retorno.Resultado.OK, "ok : se agrega libro 1 con 10 ejemplares");
        p.ver(s.agregarLibro("Libro 2", "2", "Categoria 1", 5).resultado, Retorno.Resultado.OK, "ok : se agrega libro 2 con 5 ejemplares");
        p.ver(s.agregarLibro("Libro 3", "3", "Categoria 1", 3).resultado, Retorno.Resultado.OK, "ok : se agrega libro 3 con 2 ejemplares");
        p.ver(s.agregarLibro("Libro 4", "4", "Categoria 2", 2).resultado, Retorno.Resultado.OK, "ok : se agrega libro 4 con 2 ejemplares");
        p.ver(s.agregarLibro("Libro 5", "5", "Categoria 2", 2).resultado, Retorno.Resultado.OK, "ok : se agrega libro 5 con 2 ejemplares");
        p.ver(s.agregarLibro("Libro 6", "6", "Categoria 3", 1).resultado, Retorno.Resultado.OK, "ok : se agrega libro 6 con 1 ejemplares");
        p.ver(s.agregarLibro("Libro 7", "7", "Categoria 3", 1).resultado, Retorno.Resultado.OK, "ok : se agrega libro 7 con 1 ejemplares");
        p.ver(s.agregarLibro("Libro 8", "8", "Categoria 3", 1).resultado, Retorno.Resultado.OK, "ok : se agrega libro 8 con 1 ejemplares");
        p.ver(s.agregarLibro("Libro 9", "9", "Categoria 3", 1).resultado, Retorno.Resultado.OK, "ok : se agrega libro 9 con 1 ejemplares");      
        p.ver(s.agregarLibro("Libro 10", "10", "Categoria 3",1).resultado, Retorno.Resultado.OK, "ok : se agrega libro 10 con 1 ejemplares");
 
       //-------------------------------------------------------------------------- ----------------------------------       
       //Controlamos que esten los libros agregados.
        System.out.println("Listado de Libros agregados OK par ver que no hayan agregado ");
        System.out.println(s.listarLibros().valorString);
       //--------------------------------------------------------------------------------------------------------------
          
        
        p.ver(s.agregarLibro("", "11", " ategoria 3",1).resultado, Retorno.Resultado.ERROR_1, "Error 1 : se intenta agretgar libro con falta de parametros");
        p.ver(s.agregarLibro("Libro 12", "10", "Categoria 3",1).resultado, Retorno.Resultado.ERROR_2, "Error 2 : Se intenta agregar libro existente");
        p.ver(s.agregarLibro("Libro 13", "ABC111", "Categoria 3",-1).resultado, Retorno.Resultado.ERROR_3, "Error 3 : se intenta agregar libro con cantidad de ejemplares incorrecta");
        p.ver(s.agregarLibro("Libro 14", "14", "Categoria 2", 1).resultado, Retorno.Resultado.OK, "ok : se agrega libro 14 con 1 ejemplare");

        
       //-------------------------------------------------------------------------- ----------------------------------       
       //Controlamos que esten los libros agregados y no se hayan agregado los libros con error.
        System.out.println("Listado de Libros OK  y ver que no se hayan agregado los libros con error ");
        System.out.println(s.listarLibros().valorString);
       //--------------------------------------------------------------------------------------------------------------
         
        
 //2.6. Prestar Libro
 
       p.ver(s.prestarLibro("3", 1).resultado, Retorno.Resultado.OK , "OK se presto Libro 3 ejemplar 1 ");
       p.ver(s.prestarLibro("3", 2).resultado, Retorno.Resultado.OK, "OK se presto Libro 3 ejemplar 2");
       p.ver(s.prestarLibro("10", 1).resultado, Retorno.Resultado.OK, "OK se presto Libro 10 ejemplar 1"); // queda con stock 0
       
       //-------------------------------------------------------------------------- ----------------------------------       
       //Controlamos libros prestados 
        System.out.println("Listado de Libros prestados por categoria ");
        System.out.println(s.prestamosXCategoría().valorString);
       //--------------------------------------------------------------------------------------------------------------
        
       
       p.ver(s.prestarLibro("", 1).resultado, Retorno.Resultado.ERROR_1, "Error 1 Isbn vacio");
       p.ver(s.prestarLibro("25", 1).resultado, Retorno.Resultado.ERROR_2, "Error 2 no existe libro con ese isbn");
       p.ver(s.prestarLibro("3", -1).resultado, Retorno.Resultado.ERROR_3 , "Error 3 numero de estudiante fuera de rango");
       p.ver(s.prestarLibro("3", 31).resultado, Retorno.Resultado.ERROR_4 , "Error 4 no Existe ese estudiante");
       
       p.ver(s.prestarLibro("10", 1).resultado, Retorno.Resultado.ERROR_5 , "Error 5 Libro sin stock");
       p.ver(s.prestarLibro("3", 1).resultado, Retorno.Resultado.ERROR_6 , "Error 6 ya existe ese prestamo para ese estudiante libro");
     
       // completo los 8 prestamos de libros al estudiante 1
       p.ver(s.prestarLibro("1", 1).resultado, Retorno.Resultado.OK , "OK se presto Libro 1 ejemplar 1 libro 1");     
       p.ver(s.prestarLibro("2", 1).resultado, Retorno.Resultado.OK , "OK se presto Libro 2 ejemplar 1 libro 2");
       p.ver(s.prestarLibro("4", 1).resultado, Retorno.Resultado.OK , "OK se presto Libro 2 ejemplar 1 libro 4");
       p.ver(s.prestarLibro("5", 1).resultado, Retorno.Resultado.OK , "OK se presto Libro 2 ejemplar 1 libro 5");
       p.ver(s.prestarLibro("6", 1).resultado, Retorno.Resultado.OK , "OK se presto Libro 2 ejemplar 1 libro 6");
     
       p.ver(s.prestarLibro("9", 1).resultado, Retorno.Resultado.ERROR_6 , "Error 6 ya tient 8 prestamos activos");

      //-------------------------------------------------------------------------- ----------------------------------       
       //Controlamos libros prestados 
        System.out.println("Listado de Libros prestados por categoria ");
        System.out.println(s.prestamosXCategoría().valorString);
       
        System.out.println(s.listarPrestamos(1).valorString);

       //--------------------------------------------------------------------------------------------------------------
        
       
       
        //2.7. Reservar Libro
        p.ver(s.reservarLibro("10", 1).resultado, Retorno.Resultado.OK, "OK Se reserva libro 10 con stock =0 "); // stock 0-se puede reservar
        p.ver(s.reservarLibro("", 1).resultado, Retorno.Resultado.ERROR_1, "Error 1 isbn es null");
        p.ver(s.reservarLibro("33", 1).resultado, Retorno.Resultado.ERROR_2, "Error 2 no existe ese libro");
        p.ver(s.reservarLibro("3", -1).resultado, Retorno.Resultado.ERROR_3, "Error 3 numero de estudiante fuera de rango"); 
        p.ver(s.reservarLibro("3", 22).resultado, Retorno.Resultado.ERROR_4, "Error 4 no existe ese numero de estudiante");
        p.ver(s.reservarLibro("1", 1).resultado, Retorno.Resultado.ERROR_5, "Error5 Se reserva libro 1 y tiene stock >0");

 //2.8.Devolver Libro
        p.ver(s.devolverLibro("1", 1).resultado, Retorno.Resultado.OK, "OK se devuelve libro 1");
        p.ver(s.devolverLibro("", 1).resultado, Retorno.Resultado.ERROR_1, "Error 1 isbn vacio");
        p.ver(s.devolverLibro("100", 1).resultado, Retorno.Resultado.ERROR_2, "Error 2 no existe ese libro");
        p.ver(s.devolverLibro("1", -1).resultado, Retorno.Resultado.ERROR_3, "Error 3 numero fuera de rango");
        p.ver(s.devolverLibro("1", 100).resultado, Retorno.Resultado.ERROR_4, "Error 4 no existe el estudiante");
        p.ver(s.devolverLibro("3", 5).resultado, Retorno.Resultado.ERROR_5, "Error 5 no existe un prestamo para ese estudiate");

//2.9. Eliminar Libro
        p.ver(s.eliminarLibro("14").resultado, Retorno.Resultado.OK, "ok Se elimino libro 14");
        p.ver(s.eliminarLibro("").resultado, Retorno.Resultado.ERROR_1, "Error 1 falta parametro");
        p.ver(s.eliminarLibro("1").resultado, Retorno.Resultado.ERROR_2, "Error 2 hay prestamos de libro 1");
 
//--------------------------------------------------------------        
 // listados de control
 //-------------------------------------------------------------
//3.1. Listar Estudiantes
   System.out.println("Listado final de estudiantes: "+ s.listarEstudiantes().valorString);
//3.2. Listar Libros   
   System.out.println("Listado final de libros: "+s.listarLibros().valorString);
   
//3.3. Listar Libros por categoría   
   System.out.println("Listado final de libros de la categoria 1: " + s.listarLibros("Categoria 1").valorString);
//3.4. Listar prestamos de un estudiante
    System.out.println("Listado final de prestamos: "+s.listarPrestamos(1).valorString);

//3.5. Libros más prestados    
    System.out.println("Listado de libros mas prestados: "+ s.librosMasPrestados().valorString);

//3.6. Deshacer las últimas n eliminaciones de libros
    p.ver(s.deshacerEliminaciones(-6).resultado, Retorno.Resultado.ERROR_1, "Error 1 parametro fuera de rango");
    p.ver(s.deshacerEliminaciones(2).resultado, Retorno.Resultado.OK, "Deshacer 2 eliminaciones");
//3.7. Cantidad prestamos activos
    System.out.println("Cantidad de prestamos activos 1: " + s.cantidadPrestamosActivos("1").valorString);
    
//3.8. Ranking de categorías
    System.out.println("Ranking por categoria : " + s.prestamosXCategoría().valorString);

    p.imprimirResultadosPrueba();
    }
}
