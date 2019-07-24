/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteplay.view;
import remoteplay.model.ServerModel;
import java.util.Scanner;


/**
 *
 * @author anne
 */
public class ServerView {
    Scanner scanOb = new Scanner(System.in);
    public ServerView(ServerModel sm){
        //System.out.println("Welcome to ServerViewBot.");
    }
    
    public void printServerStatus(Boolean on, String pn){
        System.out.println("\nServer Status");
        System.out.println("Online: " + on);
        System.out.println("Port Num: " + pn);
        System.out.println("Client List");
        
//        System.out.println("Please enter your desired port number: ");
//        String portNum = scanOb.nextLine();
//        System.out.println("Your requested port number is: " + portNum);
        
    }
}
