/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import model.ServerModel;
import view.ServerView;
import controller.ServerController;

/**
 *
 * @author anne
 */
public class ServerControllerTest {
    
    // mvc
    ServerModel sm = new ServerModel();
    ServerView sv = new ServerView(sm);
    ServerController sc = new ServerController(sv, sm);
    
    public ServerControllerTest() {
    }
    
    @Test
    public void emptyServerModelPortNumber(){
        System.out.println("\nTest: Model Port Number Empty");
        System.out.println("Expected Value: ");
        assertEquals("", sc.getPortNumber());
        System.out.println("Actual Value: ");
    }
    @Test
    public void nonEmptyServerModelPortNumber(){
        System.out.println("\nTest: Model Port Number NOT Empty");
        System.out.println("Expected Value: 5050");
        String portNum = "5050";
        sc.setPortNumber(portNum);
        assertEquals(portNum, sc.getPortNumber());
        System.out.println("Actual Value: " + portNum);  
    }
    @Test
    public void ServerModelDefaultOffline(){
        System.out.println("\nTest: Server Offline by Default");
        System.out.println("Expected Value: false");
        assertEquals(false, sc.getOnline());
        System.out.println("Actual Value: " + sc.getOnline());
    }
    @Test 
    public void ServerModelTurnedOnline(){
        System.out.println("\nTest: Server Turned Online");
        System.out.println("Expected Value: true");  
        sc.setOnline(true);
        assertEquals(true, sc.getOnline());
        System.out.println("Actual Value: " + sc.getOnline());
        
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
}
