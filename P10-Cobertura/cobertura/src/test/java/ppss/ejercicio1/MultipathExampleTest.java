package ppss.ejercicio1;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class MultipathExampleTest{

    @Test
    public void multiPath1C1(){
        int a = 6;
        int b = 6;
        int c = 0;

        int resultado = new MultipathExample().multiPath1(a, b, c);

        int resultadoEsperado = 12;

        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void multiPath1C2(){
        int a = 3;
        int b = 3;
        int c = 0;

        int resultado = new MultipathExample().multiPath1(a, b, c);

        int resultadoEsperado = 0;

        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void multiPath1C3(){
        int a = 3;
        int b = 6;
        int c = 2;

        int resultado = new MultipathExample().multiPath1(a, b, c);

        int resultadoEsperado = 8;

        assertEquals(resultadoEsperado, resultado);
    }

    @ParameterizedTest
    @MethodSource("testParametrizadoMultiPath2")
    public void multiPath2(int a, int b, int c, int resultadoEsperado){
        int resultado = new MultipathExample().multiPath2(a,b,c);
        assertEquals(resultadoEsperado, resultado);
    }
    private static Stream<Arguments> testParametrizadoMultiPath2(){
        return Stream.of(
                Arguments.of(6,3,6,15),
                Arguments.of(3,6,3,3),
                Arguments.of(6,6,6,12)
        );
    }

    @ParameterizedTest
    @MethodSource("testParametrizadoMultiPath3")
    public void MultiPath3(int a, int b, int c, int resultadoEsperado){
        int resultado = new MultipathExample().multiPath3(a,b,c);
        assertEquals(resultadoEsperado, resultado);
    }
    private static Stream<Arguments> testParametrizadoMultiPath3(){
        return Stream.of(
                Arguments.of(6,3,6,15),
                Arguments.of(3,6,3,3)
        );
    }
}