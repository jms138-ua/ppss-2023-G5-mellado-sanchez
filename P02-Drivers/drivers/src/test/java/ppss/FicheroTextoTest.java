package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

class FicheroTextoTest {

    @Test
    void contarCaracteresC1() {
        String nombreFichero = "ficheroC1.txt";

        String resultadoEsperado = "ficheroC1.txt (No existe el archivo o el directorio)";

        FicheroTexto ficheroTexto = new FicheroTexto();

        FicheroException exc = assertThrows(
                FicheroException.class,
                ()->ficheroTexto.contarCaracteres(nombreFichero));

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void contarCaracteresC2(){
        String nombreFichero = "src/test/resources/ficheroCorrecto.txt";

        int resultadoEsperado = 3;

        FicheroTexto ficheroTexto = new FicheroTexto();

        assertDoesNotThrow(
                ()->assertEquals(resultadoEsperado, ficheroTexto.contarCaracteres(nombreFichero))
        );
    }

    @Test
    @Tag("excluido")
    void contarCaracteresC3(){
        fail();
    }

    @Test
    @Tag("excluido")
    void contarCaracteresC4(){
        fail();
    }
}