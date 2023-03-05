package ppss.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorLlamadasTest{

    @Test
    void calculaConsumoC1(){
        int minutos = 10;
        int hora = 15;

        double resulatdoEsperado = 208;

        GestorLlamadasTestable doble = new GestorLlamadasTestable();
        doble.setResult(hora);
        double resultado = doble.calculaConsumo(minutos);

        assertEquals(resulatdoEsperado,resultado);
    }

    @Test
    void calculaConsumoC2(){
        int minutos = 10;
        int hora = 22;

        double resulatdoEsperado = 105;

        GestorLlamadasTestable doble = new GestorLlamadasTestable();
        doble.setResult(hora);
        double resultado = doble.calculaConsumo(minutos);

        assertEquals(resulatdoEsperado,resultado);
    }
}