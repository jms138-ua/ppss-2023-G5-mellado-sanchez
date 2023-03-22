package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.easymock.EasyMock.*;

class ReservaStubTest {
    FactoriaBOs stubFactoria;
    IOperacionBO stubOperacion;
    Reserva sut;

    @BeforeEach
    void crearMocks(){
        stubFactoria = EasyMock.niceMock(FactoriaBOs.class);
        stubOperacion = EasyMock.niceMock(IOperacionBO.class);
        sut = partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").niceMock();
    }

    @Test
    void realizaReservaC1() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String[] isbns = {"33333"};

        String resultadoEsperado="ERROR de permisos; ";

        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andStubReturn(false)
        );

        EasyMock.replay(sut, stubFactoria, stubOperacion);
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC2() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"22222","33333"};

        sut.fd = stubFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(stubFactoria.getOperacionBO()).andStubReturn(stubOperacion)
        );
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall();
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall();

        EasyMock.replay(sut, stubFactoria, stubOperacion);
        assertDoesNotThrow(
                ()->sut.realizaReserva(login, password, socio, isbns)
        );
    }

    @Test
    void realizaReservaC3() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"11111","22222","55555"};

        String resultadoEsperado = "ISBN invalido:11111; ISBN invalido:55555; ";

        sut.fd = stubFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(stubFactoria.getOperacionBO()).andStubReturn(stubOperacion)
        );
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall();
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());

        EasyMock.replay(sut, stubFactoria, stubOperacion);
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC4() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = {"22222"};

        String resultadoEsperado = "SOCIO invalido; ";

        sut.fd = stubFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(stubFactoria.getOperacionBO()).andStubReturn(stubOperacion)
        );
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        EasyMock.replay(sut, stubFactoria, stubOperacion);
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC5() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"11111","22222","33333"};

        String resultadoEsperado = "ISBN invalido:11111; CONEXION invalida; ";

        sut.fd = stubFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(stubFactoria.getOperacionBO()).andStubReturn(stubOperacion)
        );
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        assertDoesNotThrow(
                ()->stubOperacion.operacionReserva(anyString(),anyString())
        );
        EasyMock.expectLastCall().andThrow(new JDBCException());

        EasyMock.replay(sut, stubFactoria, stubOperacion);
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }
}