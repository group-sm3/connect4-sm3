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
 * @author anned
 */
public class ServerControllerTest {
    
    public ServerControllerTest() {
        // mvc
        ServerModel sm = new ServerModel();
        ServerView sv = new ServerView(sm);
        ServerController sc = new ServerController(sv, sm);
    }
    
    @Test
    public void emptyServerModel(){
        System.out.println("\nTest 1!");
    
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
}