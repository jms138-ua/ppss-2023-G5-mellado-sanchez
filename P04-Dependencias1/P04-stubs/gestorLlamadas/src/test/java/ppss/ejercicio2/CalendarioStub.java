package ppss.ejercicio2;

public class CalendarioStub extends Calendario{
    int result;
    public void setResult(int hora){
        this.result = hora;
    }
    @Override
    public int getHoraActual(){
        return result;
    }
}
