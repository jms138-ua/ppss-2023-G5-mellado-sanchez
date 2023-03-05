package ppss.ejercicio2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest{

    @Test
    void calculaConsumoC1(){
        int minutos = 10;
        int hora = 15;
        int resulatdoEsperado = 208;

        CalendarioStub doble = new CalendarioStub();
        doble.setResult(hora);

        GestorLlamadasTestable claseTestable = new GestorLlamadasTestable();
        claseTestable.setCalendario(doble);

        double resultado = claseTestable.calculaConsumo(minutos);

        assertEquals(resulatdoEsperado,resultado);
    }

    @Test
    void calculaConsumoC2(){
        int minutos = 10;
        int hora = 22;
        int resulatdoEsperado = 105;

        CalendarioStub doble = new CalendarioStub();
        doble.setResult(hora);

        GestorLlamadasTestable claseTestable = new GestorLlamadasTestable();
        claseTestable.setCalendario(doble);

        double resultado = claseTestable.calculaConsumo(minutos);

        assertEquals(resulatdoEsperado,resultado);
    }
}