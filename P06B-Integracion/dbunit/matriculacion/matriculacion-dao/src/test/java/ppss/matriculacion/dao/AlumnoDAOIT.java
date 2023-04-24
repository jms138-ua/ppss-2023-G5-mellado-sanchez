package ppss.matriculacion.dao;

import ppss.matriculacion.to.AlumnoTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

import java.time.LocalDate;

@Tag("Integracion-fase1")
public class AlumnoDAOIT {
    private JDBCAlumnoDAO alumnoDAO;
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;

    @BeforeEach
    public void setUp() throws Exception {
        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                cadena_conexionDB, "root", "ppss");
        connection = databaseTester.getConnection();
    }

    @Test
    public void testA1() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        alumno.setFechaNacimiento(LocalDate.of(1985, 2, 22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testA2() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        alumno.setFechaNacimiento(LocalDate.of(1982, 2, 22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exc = assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );
        assertEquals("Error al conectar con BD", exc.getMessage());
    }

    @Test
    public void testA3() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setNombre(null);
        alumno.setFechaNacimiento(LocalDate.of(1982, 2, 22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exc = assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );
        assertEquals("Error al conectar con BD", exc.getMessage());
    }

    @Test
    public void testA4() throws Exception {
        AlumnoTO alumno = null;

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exc = assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );
        assertEquals("Alumno nulo", exc.getMessage());
    }

    @Test
    public void testA5() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        alumno.setFechaNacimiento(LocalDate.of(1982, 2, 22));

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exc = assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );
        assertEquals("Error al conectar con BD", exc.getMessage());
    }

    @Test
    public void testB1() throws Exception {
        String alumno_nif = "11111111A";

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().delAlumno(alumno_nif));

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testB2() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        alumno.setFechaNacimiento(LocalDate.of(1982, 2, 22));

        String alumno_nif_todelete = "33333333C";

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException exc = assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().delAlumno("alumno_nif_todelete")
        );
        assertEquals("No se ha borrado ningun alumno", exc.getMessage());
    }
}