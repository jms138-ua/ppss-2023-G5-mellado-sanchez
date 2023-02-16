package ppss;

import org.junit.jupiter.api.Test;
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
}