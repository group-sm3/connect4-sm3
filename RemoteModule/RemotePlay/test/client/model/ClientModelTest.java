package client.model;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anne Leach
 */
public class ClientModelTest {
    
    public ClientModelTest() {
    Integer portNumber = 0;
    Boolean talking = false;
    String address;
    String line = "";
    DataOutputStream out = null;
    BufferedReader in = null;
    Socket sock = null;
    ModelEvent me = null;
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    /**
//     * Test of setPortNumber method, of class ClientModel.
//     */
//    @Test
//    public void testSetPortNumber() {
//        System.out.println("setPortNumber");
//        Integer pn = null;
//        ClientModel instance = new ClientModel();
//        instance.setPortNumber(pn);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAddress method, of class ClientModel.
//     */
//    @Test
//    public void testSetAddress() {
//        System.out.println("setAddress");
//        String addr = "";
//        ClientModel instance = new ClientModel();
//        instance.setAddress(addr);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPortNumber method, of class ClientModel.
//     */
//    @Test
//    public void testGetPortNumber() {
//        System.out.println("getPortNumber");
//        ClientModel instance = new ClientModel();
//        int expResult = 0;
//        int result = instance.getPortNumber();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAddress method, of class ClientModel.  Should return an 
     * empty string before the address is initialized.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        ClientModel instance = new ClientModel();
        String expResult = "";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
//
//    /**
//     * Test of error method, of class ClientModel.
//     */
//    @Test
//    public void testError() {
//        System.out.println("error");
//        ClientModel instance = new ClientModel();
//        instance.error();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validatePortAddr method, of class ClientModel.
//     */
//    @Test
//    public void testValidatePortAddr() {
//        System.out.println("validatePortAddr");
//        String portNum = "";
//        String address = "";
//        ClientModel instance = new ClientModel();
//        Boolean expResult = null;
//        Boolean result = instance.validatePortAddr(portNum, address);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validateTextFields method, of class ClientModel.
//     */
//    @Test
//    public void testValidateTextFields() {
//        System.out.println("validateTextFields");
//        String portNum = "";
//        String addr = "";
//        ClientModel instance = new ClientModel();
//        instance.validateTextFields(portNum, addr);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of activateListen method, of class ClientModel.
//     */
//    @Test
//    public void testActivateListen() {
//        System.out.println("activateListen");
//        ClientModel instance = new ClientModel();
//        instance.activateListen();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of closeProgram method, of class ClientModel.
//     */
//    @Test
//    public void testCloseProgram() {
//        System.out.println("closeProgram");
//        ClientModel instance = new ClientModel();
//        instance.closeProgram();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of closeConnections method, of class ClientModel.
//     */
//    @Test
//    public void testCloseConnections() {
//        System.out.println("closeConnections");
//        ClientModel instance = new ClientModel();
//        instance.closeConnections();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
