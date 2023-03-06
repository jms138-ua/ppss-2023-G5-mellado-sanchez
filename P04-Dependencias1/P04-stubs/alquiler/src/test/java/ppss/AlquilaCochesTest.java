package ppss;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest{

    @Test
    void calculaPrecioC1(){
        TipoCoche coche = TipoCoche.TURISMO;
        LocalDate inicio = LocalDate.of(2023, 5, 18);
        int dias = 10;

        float resultadoEsperado = 75;

        ServicioStub servicioStub = new ServicioStub();
        servicioStub.setResult(10);
        CalendarioStub calendarioStub = new CalendarioStub();

        AlquilaCochesTestable alquilaCochesTestable = new AlquilaCochesTestable();
        alquilaCochesTestable.setServicio(servicioStub);
        alquilaCochesTestable.calendario = calendarioStub;

        Ticket ticket = assertDoesNotThrow(
                () -> alquilaCochesTestable.calculaPrecio(coche,inicio,dias));

        float resultado = ticket.getPrecio_final();

        assertEquals(resultadoEsperado,resultado);
    }

    @Test
    void calculaPrecioC2(){
        TipoCoche coche = TipoCoche.CARAVANA;
        LocalDate inicio = LocalDate.of(2023, 6, 19);
        int dias = 7;
        ArrayList<LocalDate>diasfestivos = new ArrayList<>();
        diasfestivos.add(LocalDate.of(2023, 6, 20));
        diasfestivos.add(LocalDate.of(2023, 6, 24));

        float resultadoEsperado = 62.5f;

        ServicioStub servicioStub = new ServicioStub();
        servicioStub.setResult(10);
        CalendarioStub calendarioStub = new CalendarioStub();
        calendarioStub.setResult(diasfestivos, new ArrayList<LocalDate>());

        AlquilaCochesTestable alquilaCochesTestable = new AlquilaCochesTestable();
        alquilaCochesTestable.setServicio(servicioStub);
        alquilaCochesTestable.calendario = calendarioStub;

        Ticket ticket = assertDoesNotThrow(
                () -> alquilaCochesTestable.calculaPrecio(coche,inicio,dias));

        float resultado = ticket.getPrecio_final();

        assertEquals(resultadoEsperado,resultado);
    }

    @Test
    void calculaPrecioC3(){
        TipoCoche coche = TipoCoche.TURISMO;
        LocalDate inicio = LocalDate.of(2023, 4, 17);
        int dias = 8;
        ArrayList<LocalDate>diasfestivosexc = new ArrayList<>();
        diasfestivosexc.add(LocalDate.of(2023, 4, 18));
        diasfestivosexc.add(LocalDate.of(2023, 4, 21));
        diasfestivosexc.add(LocalDate.of(2023, 4, 22));

        String resultadoEsperado = "Error en dia: 2023-04-18; " + "Error en dia: 2023-04-21; " + "Error en dia: 2023-04-22; ";;

        ServicioStub servicioStub = new ServicioStub();
        servicioStub.setResult(10);
        CalendarioStub calendarioStub = new CalendarioStub();
        calendarioStub.setResult(new ArrayList<LocalDate>(), diasfestivosexc);

        AlquilaCochesTestable alquilaCochesTestable = new AlquilaCochesTestable();
        alquilaCochesTestable.setServicio(servicioStub);
        alquilaCochesTestable.calendario = calendarioStub;

        MensajeException exc = assertThrows(
                MensajeException.class,
                () -> alquilaCochesTestable.calculaPrecio(coche,inicio,dias));

        assertEquals(resultadoEsperado,exc.getMessage());
    }
}