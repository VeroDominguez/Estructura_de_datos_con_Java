
package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IObligatorioTest {

    private IObligatorio miSistema;

    public IObligatorioTest() {
        miSistema = new Sistema();
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    @Test
    public void testAgregarEstudianteOk() {
        Retorno ret = miSistema.agregarEstudiante("Nombre", "Apellido", 1111);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testAgregarEstudianteError1() {
        Retorno ret = miSistema.agregarEstudiante("", "Apellido", 1111);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarEstudiante(null, "Apellido", 1111);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarEstudiante("Nombre", "", 1111);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarEstudiante("Nombre", null, 1111);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarEstudiante("", "", 1111);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.agregarEstudiante(null, null, 1111);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

    }

    @Test
    public void testAgregarEstudianteError2() {
        Retorno ret = miSistema.agregarEstudiante("Nombre", "Apellido", 0);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        ret = miSistema.agregarEstudiante("Nombre", "Apellido", -5);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        ret = miSistema.agregarEstudiante("Nombre", "Apellido", 500001);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        ret = miSistema.agregarEstudiante("Nombre", "Apellido", 500050);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    @Test
    public void testAgregarEstudianteError3() {
        miSistema.agregarEstudiante("Nombre", "Apellido", 1111);
        Retorno ret = miSistema.agregarEstudiante("Nombre", "Apellido", 1111);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }

    @Test
    public void testObtenerEstudianteOk() {
        miSistema.agregarEstudiante("Nombre", "Apellido", 1111);
        Retorno ret = miSistema.obtenerEstudiante(1111);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Nombre#Apellido#1111", ret.valorString);
    }

    @Test
    public void testObtenerEstudianteError1() {
        Retorno ret = miSistema.obtenerEstudiante(-2);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.obtenerEstudiante(0);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.obtenerEstudiante(500001);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

        ret = miSistema.obtenerEstudiante(500500);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testObtenerEstudianteError2() {
        Retorno ret = miSistema.obtenerEstudiante(1111);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        ret = miSistema.obtenerEstudiante(1);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

        ret = miSistema.obtenerEstudiante(500000);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

  
    @Test
    public void testListarEstudiantesVacio() {
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("", ret.valorString);
    }

    @Test
    public void testListarEstudiantesUnElemento() {
        miSistema.agregarEstudiante("Nombre", "Apellido", 1111);
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Nombre#Apellido#1111", ret.valorString);

    }

    @Test
    public void testListarEstudiantesIngresoOrdenado() {
        miSistema.agregarEstudiante("Nombre1", "Apellido1", 1111);
        miSistema.agregarEstudiante("Nombre2", "Apellido2", 2222);
        miSistema.agregarEstudiante("Nombre3", "Apellido3", 3333);
        miSistema.agregarEstudiante("Nombre4", "Apellido4", 4444);
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Nombre1#Apellido1#1111|Nombre2#Apellido2#2222|Nombre3#Apellido3#3333|Nombre4#Apellido4#4444", ret.valorString);
    }

    @Test
    public void testListarEstudiantesIngresoNoOrdenado() {
        miSistema.agregarEstudiante("Nombre2", "Apellido2", 2222);
        miSistema.agregarEstudiante("Nombre1", "Apellido1", 1111);
        miSistema.agregarEstudiante("Nombre4", "Apellido4", 4444);
        miSistema.agregarEstudiante("Nombre3", "Apellido3", 3333);
        Retorno ret = miSistema.listarEstudiantes();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Nombre1#Apellido1#1111|Nombre2#Apellido2#2222|Nombre3#Apellido3#3333|Nombre4#Apellido4#4444", ret.valorString);
    }


    @Test
    public void testListarLibrosVacio() {
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals(ret.valorString, "");
    }

    @Test
    public void testListarLibrosUnElemento() {
        miSistema.agregarLibro("NombreLibro", "ISBN", "Categoria", 150);
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("NombreLibro#ISBN#Categoria", ret.valorString);
    }

    @Test
    public void testListarLibrosIngresoOrdenado() {
        miSistema.agregarLibro("NombreLibro1", "ISBN1", "Categoria", 150);
        miSistema.agregarLibro("NombreLibro2", "ISBN2", "Categoria", 250);
        miSistema.agregarLibro("NombreLibro3", "ISBN3", "Categoria", 350);
        miSistema.agregarLibro("NombreLibro4", "ISBN4", "Categoria", 450);
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("NombreLibro1#ISBN1#Categoria|NombreLibro2#ISBN2#Categoria|NombreLibro3#ISBN3#Categoria|NombreLibro4#ISBN4#Categoria", ret.valorString);
    }

    @Test
    public void testListarLibrosIngresoNoOrdenado() {
        miSistema.agregarLibro("NombreLibro2", "ISBN2", "Categoria", 250);
        miSistema.agregarLibro("NombreLibro1", "ISBN1", "Categoria", 150);
        miSistema.agregarLibro("NombreLibro4", "ISBN4", "Categoria", 450);
        miSistema.agregarLibro("NombreLibro3", "ISBN3", "Categoria", 350);
        Retorno ret = miSistema.listarLibros();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("NombreLibro1#ISBN1#Categoria|NombreLibro2#ISBN2#Categoria|NombreLibro3#ISBN3#Categoria|NombreLibro4#ISBN4#Categoria", ret.valorString);
    }

}