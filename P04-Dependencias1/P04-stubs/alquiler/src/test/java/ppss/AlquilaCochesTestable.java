package ppss;

public class AlquilaCochesTestable extends AlquilaCoches {
    Calendario calendario;
    public void setCalendario(Calendario calendarioStub) {
        this.calendario = calendarioStub;
    }
    @Override
    public Calendario crearCalendario() {
        return calendario;
    }

    Servicio servicio;
    public void setServicio(Servicio servicioStub) {
        this.servicio = servicioStub;
    }
    @Override
    public Servicio crearServicio() {
        return servicio;
    }
}