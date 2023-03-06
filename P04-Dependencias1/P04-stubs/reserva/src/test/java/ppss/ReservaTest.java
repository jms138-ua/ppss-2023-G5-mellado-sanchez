package ppss;

import ppss.excepciones.ReservaException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    final ArrayList<String> sociosValidos = new ArrayList<String>(Arrays.asList("Luis"));
    final ArrayList<String> isbnsValidos = new ArrayList<String>(Arrays.asList("11111","22222"));

    @Test
    void realizaReservaC1(){
        //En este caso de prueba no se llega al codigo que utilizaria el stub
        // a si que no es necesario ponerlo
        boolean login = false;
        String username = "xxxx";
        String password = "xxxx";
        String socio = "Luis";
        String isbns[] = {"11111"};

        String resultadoEsperado = "ERROR de permisos; ";

        ReservaTestable reservaTestable = new ReservaTestable();
        reservaTestable.setPermiso(login); //Incorrect login

        ReservaException exc = assertThrows(
                ReservaException.class,
                () -> reservaTestable.realizaReserva(username,password,socio,isbns));

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC2(){
        boolean login = true;
        String username = "ppss";
        String password = "ppss";
        boolean conexion = true;
        String socio = "Luis";
        String isbns[] = {"11111","22222"};

        OperacionStub operacionStub = new OperacionStub();
        operacionStub.setResult(conexion, sociosValidos, isbnsValidos);
        IOperacionBOFactory.setOperacion(operacionStub);

        ReservaTestable reservaTestable = new ReservaTestable();
        reservaTestable.setPermiso(login);

        assertDoesNotThrow(
                () ->reservaTestable.realizaReserva(username,password,socio,isbns));
    }

    @Test
    void realizaReservaC3(){
        boolean login = true;
        String username = "ppss";
        String password = "ppss";
        boolean conexion = true;
        String socio = "Luis";
        String isbns[] = {"11111","33333","44444"};

        String resultadoEsperado = "ISBN invalido:33333; ISBN invalido:44444; ";

        OperacionStub operacionStub = new OperacionStub();
        operacionStub.setResult(conexion, sociosValidos, isbnsValidos);
        IOperacionBOFactory.setOperacion(operacionStub);

        ReservaTestable reservaTestable = new ReservaTestable();
        reservaTestable.setPermiso(login);

        ReservaException exc = assertThrows(
                ReservaException.class,
                () -> reservaTestable.realizaReserva(username,password,socio,isbns));

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC4(){
        boolean login = true;
        String username = "ppss";
        String password = "ppss";
        boolean conexion = true;
        String socio = "Pepe";
        String isbns[] = {"11111"};

        String resultadoEsperado = "SOCIO invalido; ";

        OperacionStub operacionStub = new OperacionStub();
        operacionStub.setResult(conexion, sociosValidos, isbnsValidos);
        IOperacionBOFactory.setOperacion(operacionStub);

        ReservaTestable reservaTestable = new ReservaTestable();
        reservaTestable.setPermiso(login);

        ReservaException exc = assertThrows(
                ReservaException.class,
                () -> reservaTestable.realizaReserva(username,password,socio,isbns));

        assertEquals(resultadoEsperado, exc.getMessage());
    }

    @Test
    void realizaReservaC5(){
        boolean login = true;
        String username = "ppss";
        String password = "ppss";
        boolean conexion = false;
        String socio = "Luis";
        String isbns[] = {"11111","22222"};

        String resultadoEsperado = "CONEXION invalida; ";

        OperacionStub operacionStub = new OperacionStub();
        operacionStub.setResult(conexion, sociosValidos, isbnsValidos);
        IOperacionBOFactory.setOperacion(operacionStub);

        ReservaTestable reservaTestable = new ReservaTestable();
        reservaTestable.setPermiso(login);

        ReservaException exc = assertThrows(
                ReservaException.class,
                () -> reservaTestable.realizaReserva(username,password,socio,isbns));

        assertEquals(resultadoEsperado, exc.getMessage());
    }
}