package ppss;

public class AlquilaCochesTestable extends AlquilaCoches {
    Servicio servicio;
    public void setServicio(Servicio servicioStub) {
        this.servicio = servicioStub;
    }
    @Override
    public Servicio crearServicio() {
        return servicio;
    }
}