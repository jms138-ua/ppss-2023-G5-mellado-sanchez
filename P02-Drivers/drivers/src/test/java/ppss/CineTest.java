package ppss;

import org.junit.jupiter.api.Test;
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
}