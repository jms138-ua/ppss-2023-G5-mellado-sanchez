package ppss;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {

    @Test
    void deleteC1() {
        int datos[] = {1,3,5,7};
        int elementoABorrar = 5;

        int resultadoEsperadoColeccion[] = {1,3,7};
        int resultadoEsperadoNumElem = 3;

        DataArray dataArray = new DataArray(datos);
        assertDoesNotThrow(
                ()->dataArray.delete(elementoABorrar)
        );
        int resultadoColeccion[] = dataArray.getColeccion();
        int resultadoNumElem = dataArray.size();

        assertAll(
                ()->assertArrayEquals(resultadoEsperadoColeccion, resultadoColeccion),
                ()->assertEquals(resultadoEsperadoNumElem, resultadoNumElem)
        );
    }

    @Test
    void deleteC2() {
        int datos[] = {1,3,3,5,7};
        int elementoABorrar = 3;

        int resultadoEsperadoColeccion[] = {1,3,5,7};
        int resultadoEsperadoNumElem = 4;

        DataArray dataArray = new DataArray(datos);
        assertDoesNotThrow(
                ()->dataArray.delete(elementoABorrar)
        );
        int resultadoColeccion[] = dataArray.getColeccion();
        int resultadoNumElem = dataArray.size();

        assertAll(
                ()->assertArrayEquals(resultadoEsperadoColeccion, resultadoColeccion),
                ()->assertEquals(resultadoEsperadoNumElem, resultadoNumElem)
        );
    }

    @Test
    void deleteC3() {
        int datos[] = {1,2,3,4,5,6,7,8,9,10};
        int elementoABorrar = 4;

        int resultadoEsperadoColeccion[] = {1,2,3,5,6,7,8,9,10};
        int resultadoEsperadoNumElem = 9;

        DataArray dataArray = new DataArray(datos);
        assertDoesNotThrow(
                ()->dataArray.delete(elementoABorrar)
        );
        int resultadoColeccion[] = dataArray.getColeccion();
        int resultadoNumElem = dataArray.size();

        assertAll(
                ()->assertArrayEquals(resultadoEsperadoColeccion, resultadoColeccion),
                ()->assertEquals(resultadoEsperadoNumElem, resultadoNumElem)
        );
    }

    @Test
    void deleteC4() {
        int datos[] = {};
        int elementoABorrar = 8;

        String resultadoEsperado = "No hay elementos en la colección";

        DataArray dataArray = new DataArray(datos);

        DataException exc = assertThrows(
                DataException.class,
                ()->dataArray.delete(elementoABorrar)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void deleteC5() {
        int datos[] = {1,3,5,7};
        int elementoABorrar = -5;

        String resultadoEsperado = "El valor a borrar debe ser > 0";

        DataArray dataArray = new DataArray(datos);

        DataException exc = assertThrows(
                DataException.class,
                ()->dataArray.delete(elementoABorrar)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void deleteC6() {
        int datos[] = {};
        int elementoABorrar = 0;

        String resultadoEsperado = "Colección vacía. Y el valor a borrar debe ser > 0";

        DataArray dataArray = new DataArray(datos);

        DataException exc = assertThrows(
                DataException.class,
                ()->dataArray.delete(elementoABorrar)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void deleteC7() {
        int datos[] = {1,3,5,7};
        int elementoABorrar = 8;

        String resultadoEsperado = "Elemento no encontrado";

        DataArray dataArray = new DataArray(datos);

        DataException exc = assertThrows(
                DataException.class,
                ()->dataArray.delete(elementoABorrar)
        );

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @ParameterizedTest
    @MethodSource("testParametrizadoC4C7")
    @Tag("parametrizado")
    @Tag("conExcepciones")
    void testParametrizadoC8(int datos[], int elementoABorrar, String resultadoEsperado){
        DataArray dataArray = new DataArray(datos);

        DataException exc = assertThrows(
                DataException.class,
                ()->dataArray.delete(elementoABorrar));

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    private static Stream<Arguments> testParametrizadoC4C7(){
        return Stream.of(
                Arguments.of(new int[0], 8, "No hay elementos en la colección"),
                Arguments.of(new int[]{1,3,5,7}, -5, "El valor a borrar debe ser > 0"),
                Arguments.of(new int[0], 0, "Colección vacía. Y el valor a borrar debe ser > 0"),
                Arguments.of(new int[]{1,3,5,7}, 8, "Elemento no encontrado")
        );
    }

    @ParameterizedTest
    @MethodSource("testParametrizadoC1C3")
    @Tag("parametrizado")
    void testParametrizadoC9(int datos[], int elementoABorrar, int resultadoEsperadoColeccion[], int resultadoEsperadoNumElem){
        DataArray dataArray = new DataArray(datos);
        assertDoesNotThrow(
                ()->dataArray.delete(elementoABorrar)
        );
        int resultadoColeccion[] = dataArray.getColeccion();
        int resultadoNumElem = dataArray.size();

        assertAll(
                ()->assertArrayEquals(resultadoEsperadoColeccion, resultadoColeccion),
                ()->assertEquals(resultadoEsperadoNumElem, resultadoNumElem)
        );
    }

    private static Stream<Arguments> testParametrizadoC1C3(){
        return Stream.of(
                Arguments.of(new int[]{1,3,5,7}, 5, new int[]{1,3,7}, 3),
                Arguments.of(new int[]{1,3,3,5,7}, 3, new int[]{1,3,5,7}, 4),
                Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 4, new int[]{1,2,3,5,6,7,8,9,10}, 9)
        );
    }
}