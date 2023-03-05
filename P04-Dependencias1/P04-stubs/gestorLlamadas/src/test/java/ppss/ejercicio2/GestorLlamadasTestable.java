package ppss.ejercicio2;

public class GestorLlamadasTestable extends GestorLlamadas{
    Calendario calendario;
    public void setCalendario(Calendario calendarioStub){
        this.calendario = calendarioStub;
    }
    @Override
    public Calendario getCalendario() {
        return calendario;
    }
}
