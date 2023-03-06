package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.ArrayList;

public class OperacionStub implements IOperacionBO{
    Boolean conexion = false;
    ArrayList<String> sociosValidos = new ArrayList<>();
    ArrayList<String> isbnsValidos = new ArrayList<>();

    public void setResult(Boolean conexion, ArrayList<String> socios, ArrayList<String> isbns){
        this.conexion = conexion;
        this.sociosValidos = socios;
        this.isbnsValidos = isbns;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException{
        if(!this.conexion){
            throw new JDBCException();
        }else if (!sociosValidos.contains(socio)){
            throw new SocioInvalidoException();
        }else if (!isbnsValidos.contains(isbn)){
            throw new IsbnInvalidoException();
        }
    }
}
