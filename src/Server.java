/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import model.ServerModel;
import view.ServerView;
import controller.ServerController;
import java.io.*;

/**
 *
 * @author anned
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("\n\nWelcome to Serverview 3000");
        
        // mvc
        ServerModel sm = new ServerModel();
        ServerView sv = new ServerView(sm);
        ServerController sc = new ServerController(sv, sm);
        
        // start up the view
        sc.updateServerView();
        sc.setPortNumber("8080");
        sc.updateServerView();

        System.out.println("\nGoodbye!\n");
    }
}
