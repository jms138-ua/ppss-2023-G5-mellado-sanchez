package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import static org.easymock.EasyMock.partialMockBuilder;

import java.io.FileReader;
import java.io.IOException;

class FicheroTextoTest {
    IMocksControl control;
    FileReader mock;
    FicheroTexto sut;

    @BeforeEach
    void crearMocks() {
        control = EasyMock.createStrictControl();
        mock = control.mock(FileReader.class);
        sut = partialMockBuilder(FicheroTexto.class).addMockedMethod("crearFichero").mock(control);
    }

    @Test
    void contarCaracteresC1() {
        String nombreFichero = "src/test/resources/ficheroC1.txt";

        String resultadoEsperado = "src/test/resources/ficheroC1.txt (Error al leer el archivo)";

        assertDoesNotThrow(
                ()->EasyMock.expect(sut.crearFichero(nombreFichero)).andReturn(mock)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(mock.read()).andReturn((int)'a').andReturn((int)'b').andThrow(new IOException())
        );

        control.replay();
        FicheroException exc = assertThrows(FicheroException.class,
                ()->sut.contarCaracteres(nombreFichero));
        control.verify();

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void contarCaracteresC2() {
        String nombreFichero = "src/test/resources/ficheroC2.txt";

        String resultadoEsperado = "src/test/resources/ficheroC2.txt (Error al cerrar el archivo)";

        assertDoesNotThrow(
                ()->EasyMock.expect(sut.crearFichero(nombreFichero)).andReturn(mock)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(mock.read()).andReturn((int)'a').andReturn((int)'b').andReturn((int)'c').andReturn(-1)
        );
        assertDoesNotThrow(
                ()->mock.close()
        );
        EasyMock.expectLastCall().andThrow(new IOException());

        control.replay();
        FicheroException exc = assertThrows(FicheroException.class,
                ()->sut.contarCaracteres(nombreFichero));
        control.verify();

        assertEquals(resultadoEsperado, exc.getMessage());
    }
}