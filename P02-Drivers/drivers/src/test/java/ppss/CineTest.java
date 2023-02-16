package ppss;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class CineTest {

    @Test
    void reservaButacasC1() throws ButacasException {
        boolean asientos[] = new boolean[0];
        int solicitado= 3;

        boolean resultadoEsperado = false;
        boolean resultadoEsperadoAsientos[] = new boolean[0];

        Cine cine = new Cine();
        boolean resultado = cine.reservaButacasV1(asientos, solicitado);

        assertAll(
                ()->assertEquals(resultadoEsperado,resultado),
                ()->assertArrayEquals(resultadoEsperadoAsientos,asientos)
        );
    }

    @Test
    void reservaButacasC2() throws ButacasException {
        boolean asientos[] = new boolean[0];
        int solicitado= 0;

        boolean resultadoEsperado = false;
        boolean resultadoEsperadoAsientos[] = new boolean[0];

        Cine cine = new Cine();
        //boolean resultado = cine.reservaButacasV1(asientos, solicitado);
        boolean resultado = cine.reservaButacasV2(asientos, solicitado);

        assertAll(
                ()->assertEquals(resultadoEsperado,resultado),
                ()->assertArrayEquals(resultadoEsperadoAsientos,asientos)
        );
    }

    @Test
    void reservaButacasC3() throws ButacasException {
        boolean asientos[] = {false,false,false,true,true};
        int solicitado= 2;

        boolean resultadoEsperado = true;
        boolean resultadoEsperadoAsientos[] = {true,true,false,true,true};

        Cine cine = new Cine();
        boolean resultado = cine.reservaButacasV1(asientos, solicitado);

        assertAll(
                ()->assertEquals(resultadoEsperado,resultado),
                ()->assertArrayEquals(resultadoEsperadoAsientos,asientos)
        );
    }

    @Test
    void reservaButacasC4() throws ButacasException {
        boolean asientos[] = {true,true,true};
        int solicitado= 1;

        boolean resultadoEsperado = false;
        boolean resultadoEsperadoAsientos[] = {true,true,true};

        Cine cine = new Cine();
        boolean resultado = cine.reservaButacasV1(asientos, solicitado);

        assertAll(
                ()->assertEquals(resultadoEsperado,resultado),
                ()->assertArrayEquals(resultadoEsperadoAsientos,asientos)
        );
    }

    @ParameterizedTest
    @MethodSource("testParametrizado")
    @Tag("parametrizado")
    void reservaButacasC5(boolean asientos[], int solicitado, boolean resultadoEsperado, boolean resultadoEsperadoAsientos[]){
        Cine cine = new Cine();
        boolean resultado = cine.reservaButacasV2(asientos,solicitado);

        assertAll(
                ()->assertEquals(resultadoEsperado,resultado),
                ()->assertArrayEquals(resultadoEsperadoAsientos,asientos)
        );
    }

    private static Stream<Arguments> testParametrizado(){
        return Stream.of(
                Arguments.of(new boolean[0], 3, false, new boolean[0]),
                Arguments.of(new boolean[0], 0, false, new boolean[0]),
                Arguments.of(new boolean[]{false, false, false, true, true}, 2, true, new boolean[]{true,true,false,true,true}),
                Arguments.of(new boolean[]{true,true,true}, 1, false, new boolean[]{true,true,true})
        );
    }
}