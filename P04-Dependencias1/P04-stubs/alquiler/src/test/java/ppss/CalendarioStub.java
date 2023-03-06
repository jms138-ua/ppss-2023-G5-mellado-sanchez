package ppss;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarioStub extends Calendario{
    ArrayList<LocalDate> dias = new ArrayList<>();
    ArrayList<LocalDate> diasExepcion = new ArrayList<>();

    public void setResult(ArrayList<LocalDate> dias, ArrayList<LocalDate> diasExepcion){
        this.dias = dias;
        this.diasExepcion = diasExepcion;
    }

    @Override
    public boolean es_festivo(LocalDate otroDia) throws CalendarioException{
        if (diasExepcion.contains(otroDia)){
            throw new CalendarioException();
        }
        return dias.contains(otroDia);
    }
}