package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import static org.easymock.EasyMock.partialMockBuilder;

class ReservaMockTest {
    IMocksControl control;
    FactoriaBOs mockFactoria;
    IOperacionBO mockOperacion;
    Reserva sut;

    @BeforeEach
    void crearMocks(){
        control = EasyMock.createStrictControl();
        mockFactoria = control.mock(FactoriaBOs.class);
        mockOperacion = control.mock(IOperacionBO.class);
        sut = partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").mock(control);
    }

    @Test
    void realizaReservaC1() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String[] isbns = {"33333"};

        String resultadoEsperado="ERROR de permisos; ";

        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(false)
        );

        control.replay();
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );
        control.verify();

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC2() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"22222","33333"};

        sut.fd = mockFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion)
        );
        assertDoesNotThrow(
                ()->mockOperacion.operacionReserva(socio, isbns[0])
        );
        EasyMock.expectLastCall();
        assertDoesNotThrow(
                ()->mockOperacion.operacionReserva(socio, isbns[1])
        );
        EasyMock.expectLastCall();

        control.replay();
        assertDoesNotThrow(
                ()->sut.realizaReserva(login, password, socio, isbns)
        );
        control.verify();
    }

    @Test
    void realizaReservaC3() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"11111","22222","55555"};

        String resultadoEsperado = "ISBN invalido:11111; ISBN invalido:55555; ";

        sut.fd = mockFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion)
        );
        //nose

        control.replay();
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );
        control.verify();

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC4() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = {"22222"};

        String resultadoEsperado = "SOCIO invalido; ";

        sut.fd = mockFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion)
        );
        assertDoesNotThrow(
                ()->mockOperacion.operacionReserva(socio, isbns[0])
        );
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        control.replay();
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );
        control.verify();

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC5() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"11111","22222","33333"};

        String resultadoEsperado = "ISBN invalido:11111; CONEXION invalida; ";

        sut.fd = mockFactoria;
        assertDoesNotThrow(
                ()->EasyMock.expect(sut.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true)
        );
        assertDoesNotThrow(
                ()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion)
        );
        assertDoesNotThrow(
                ()->mockOperacion.operacionReserva(socio, isbns[0])
        );
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(
                ()->mockOperacion.operacionReserva(socio,isbns[1])
        );
        assertDoesNotThrow(
                ()->mockOperacion.operacionReserva(socio,isbns[2])
        );
        EasyMock.expectLastCall().andThrow(new JDBCException());

        control.replay();
        ReservaException exc = assertThrows(ReservaException.class,
                ()->sut.realizaReserva(login, password, socio, isbns)
        );
        control.verify();

        assertEquals(resultadoEsperado, exc.getMessage());
    }
}