package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import static org.easymock.EasyMock.partialMockBuilder;

public class PremioTest {
    IMocksControl control;
    ClienteWebService mock;
    Premio sut;

    @BeforeEach
    void crearMocks(){
        control = EasyMock.createStrictControl();
        mock = control.mock(ClienteWebService.class);
        sut = partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(control);
        sut.cliente = mock;
    }

    @Test
    void compruebaPremioC1(){
        float generaNumero = 0.07f;
        String obtenerPremio = "entrada final Champions";

        String resultadoEsperado = "Premiado con entrada final Champions";

        EasyMock.expect(sut.generaNumero()).andReturn(generaNumero);
        assertDoesNotThrow(
                ()->EasyMock.expect(mock.obtenerPremio()).andReturn(obtenerPremio)
        );

        control.replay();
        String resultado = sut.compruebaPremio();
        control.verify();

        assertEquals(resultadoEsperado,resultado);
    }

    @Test
    void compruebaPremioC2(){
        float generaNumero = 0.03f;

        String resultadoEsperado = "No se ha podido obtener el premio";

        EasyMock.expect(sut.generaNumero()).andReturn(generaNumero);
        assertDoesNotThrow(
                ()->EasyMock.expect(mock.obtenerPremio()).andThrow(new ClienteWebServiceException())
        );

        control.replay();
        String resultado = sut.compruebaPremio();
        control.verify();

        assertEquals(resultadoEsperado,resultado);
    }

    @Test
    void compruebaPremioC3(){
        float generaNumero = 0.3f;

        String resultadoEsperado = "Sin premio";

        EasyMock.expect(sut.generaNumero()).andReturn(generaNumero);

        control.replay();
        String resultado = sut.compruebaPremio();
        control.verify();

        assertEquals(resultadoEsperado,resultado);
    }
}