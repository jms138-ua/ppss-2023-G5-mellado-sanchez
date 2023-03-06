package ppss;

import ppss.IOperacionBO;
import ppss.Operacion;

public class IOperacionBOFactory {
    private static IOperacionBO operacion = null;
    public static IOperacionBO create() {
        if (operacion != null) {
            return operacion;
        }
        return new Operacion();
    }
    public static void setOperacion(IOperacionBO operacion) {
        IOperacionBOFactory.operacion = operacion;
    }
}