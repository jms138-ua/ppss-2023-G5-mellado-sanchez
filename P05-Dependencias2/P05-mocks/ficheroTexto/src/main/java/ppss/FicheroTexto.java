package ppss;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FicheroTexto {
    public FileReader crearFichero(String nombreFichero) throws FileNotFoundException {
        return new FileReader(nombreFichero);
    }
    public int contarCaracteres(String nombreFichero) throws FicheroException {
        int contador = 0;
        FileReader fichero = null;
        try {
            fichero = crearFichero(nombreFichero); //Dependencia!
            int i=0;
            while (i != -1) {
                i = fichero.read(); //Dependencia!
                System.out.println("Acabo de leer: "+(char) i);
                contador++;
            }
            contador--;
        } catch (FileNotFoundException e1) {
            throw new FicheroException(nombreFichero +
                    " (No existe el archivo o el directorio)");
        } catch (IOException e2) {
            throw new FicheroException(nombreFichero +
                    " (Error al leer el archivo)");
        }
        try {
            System.out.println("Antes de cerrar el fichero");
            fichero.close(); //Dependencia!
        } catch (IOException e) {
            throw new FicheroException(nombreFichero +
                    " (Error al cerrar el archivo)");
        }
        return contador;
    }
}