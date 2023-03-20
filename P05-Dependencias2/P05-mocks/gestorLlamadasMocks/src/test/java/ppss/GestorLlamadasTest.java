package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import static org.easymock.EasyMock.partialMockBuilder;

class GestorLlamadasTest{
    IMocksControl control;
    Calendario mock;
    GestorLlamadas sut;

    @BeforeEach
    void crearMocks(){
        control = EasyMock.createStrictControl();
        mock = control.mock(Calendario.class);
        sut = partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(control);
    }

    @Test
    void calculaConsumoC1() {
        int minutos = 22;
        int horas = 10;

        double resultadoEsperado = 457.6;

        EasyMock.expect(sut.getCalendario()).andReturn(mock);
        assertDoesNotThrow(
                ()->EasyMock.expect(mock.getHoraActual()).andReturn(horas)
        );

        control.replay();
        double resultado = sut.calculaConsumo(minutos);
        control.verify();

        assertEquals(resultadoEsperado,resultado);
    }

    @Test
    void calculaConsumoC2(){
        int minutos = 13;
        int horas = 21;

        double resultadoEsperado = 136.5;

        EasyMock.expect(sut.getCalendario()).andReturn(mock);
        assertDoesNotThrow(
                ()->EasyMock.expect(mock.getHoraActual()).andReturn(horas)
        );

        control.replay();
        double resultado = sut.calculaConsumo(minutos);
        control.verify();

        assertEquals(resultadoEsperado,resultado);
    }
}