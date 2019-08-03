/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;
import java.net.*; 
import java.io.*; 
import java.lang.Thread;
/**
 *
 * @author Anne
 */
public class PortListener extends Thread{
    Socket csock = null;
    BufferedReader in = null;
    DataOutputStream out = null;
    String line = "";
    
    public void PortListener(Socket csock, BufferedReader in, DataOutputStream out){
        this.csock = csock;
        this.in = in;
        this.out = out;
    }
    
    @Override
    public void run(){
        while (!line.equals("ENDGAME")){
            try{
                line = in.readLine();
                out.writeUTF(line);
            }
            catch(IOException i){
                System.out.println(i);
            }
        }
        // if client endgames, gracefully close connection
        // by alerting ServerController.
        
    }
    
    
}
