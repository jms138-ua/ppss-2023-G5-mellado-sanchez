package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

/* IMPORTANTE:
    Dado que prácticamente todos los métodos de dBUnit lanzan una excepción,
    vamos a usar "throws Esception" en los métodos, para que el código quede más
    legible sin necesidad de usar un try..catch o envolver cada sentencia dbUnit 
    con un assertDoesNotThrow()
    Es decir, que vamos a primar la legibilidad de los tests.
    Si la SUT puede lanza una excepción, SIEMPRE usaremos assertDoesNotThrow para
    invocar a la sut cuando no esperemos que se lance dicha excepción (independientemente de que hayamos propagado las excepciones provocadas por dbunit).
*/
public class ClienteDAO_IT {
  
  private ClienteDAO clienteDAO; //SUT
  private IDatabaseTester databaseTester;
  private IDatabaseConnection connection;

  @BeforeEach
  public void setUp() throws Exception {

    String cadena_conexionDB = "jdbc:mysql://localhost:3306/DBUNIT?useSSL=false";
    databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
            cadena_conexionDB, "root", "ppss");
    connection = databaseTester.getConnection();

    clienteDAO = new ClienteDAO();
  }

  @Test
  public void testInsert() throws Exception {
    Cliente cliente = new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
    
     //invocamos a la sut
    assertDoesNotThrow(()->clienteDAO.insert(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente"); 

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);

   }

  @Test
  public void testDelete() throws Exception {
    Cliente cliente =  new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la SUT
    assertDoesNotThrow(()->clienteDAO.delete(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");
    
    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

  @Test
  public void test_insert2() throws Exception {
    Cliente cliente = new Cliente(3,"Javier", "Mellado");
    cliente.setDireccion("Ecuador 5");
    cliente.setCiudad("Alicante");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init2.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la sut
    assertDoesNotThrow(()->clienteDAO.insert(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado2.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);

  }

  @Test
  public void test_delete2() throws Exception {
    Cliente cliente =  new Cliente(3,"Javier", "Mellado");
    cliente.setDireccion("Ecuador 5");
    cliente.setCiudad("Alicante");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado2.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la SUT
    assertDoesNotThrow(()->clienteDAO.delete(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-init2.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

  @Test
  public void testUpdate() throws Exception {
    Cliente cliente =  new Cliente(1,"John", "Smith");
    cliente.setDireccion("Other Street");
    cliente.setCiudad("NewCity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la SUT
    assertDoesNotThrow(()->clienteDAO.update(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-update-esperado.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

  @Test
  public void testRetrieve() throws Exception {
    int cliente_id = 1;

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la SUT
    Cliente cliente = assertDoesNotThrow(()->clienteDAO.retrieve(cliente_id));

    Cliente cliente_esperado =  new Cliente(1,"John", "Smith");
    cliente_esperado.setDireccion("1 Main Street");
    cliente_esperado.setCiudad("Anycity");

    assertAll(
            ()->assertEquals(cliente.getId(),cliente_esperado.getId()),
            ()->assertEquals(cliente.getNombre(),cliente_esperado.getNombre()),
            ()->assertEquals(cliente.getApellido(),cliente_esperado.getApellido()),
            ()->assertEquals(cliente.getDireccion(),cliente_esperado.getDireccion()),
            ()->assertEquals(cliente.getCiudad(),cliente_esperado.getCiudad())
    );
  }
}