/*
    
 */
package client.model;
import client.model.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author Anne
 */
public class ClientModel extends AbstractModel {
    // the calculator had methods such as add(), sub(), equals().
    // here I will have data and methods of the client menu instead.
    private Integer portNumber = 0;
    Boolean talking = false;
    String address;
    String line = "";
    private DataOutputStream out = null;
    private BufferedReader in = null;
    private Socket sock = null;
    ModelEvent me = null;
    
    public void setPortNumber(Integer pn){
        this.portNumber = pn;
    }
    
    public void setAddress(String addr){
        this.address = addr;
    }
    
    public int getPortNumber(){
        return portNumber;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void error(){
        ModelEvent me = new ModelEvent(this, 1, "", "Error!");
        notifyChanged(me);
    }
    
    public Boolean validatePortAddr(String portNum, String address){
        Integer tempInt = null;
        try{tempInt = Integer.parseInt(portNum);}
        catch(NumberFormatException e){  return false;}
        catch(NullPointerException n){  return false;}
        if (tempInt > 65535 || tempInt < 2048){  return false;}
        this.portNumber = tempInt;
        // If time allows, will add text validation.        
        this.address = address;
        return true;
    }
    
    // This pulls values from the textfield and ensures that the port number
    // the server must listen on is valid (integer [2048, 65535]).
    public void validateTextFields(String portNum, String addr){
        System.out.println("Attempted portNum: " + portNum);
        System.out.println("Attempted ipAddr " + addr);
        if (!validatePortAddr(portNum, addr)){
            me = new ModelEvent(this, 2, "", "Valid port number: [2048, 65535]");
            notifyChanged(me);    
        }
        else{
            System.out.println("Port number valid: " + portNumber);
        }
    }
    
    public void activateListen(){
        
        // start talking thread
        Runnable clientTalk = new Runnable(){
            @Override
            public void run(){
                try{
                    System.out.println("Attempting to connect to server...");
                    sock = new Socket(address, portNumber);
                    System.out.println("Connected to server.");
                    talking = true;
                    me = new ModelEvent(this, 1, "", "Connected to server");
                    notifyChanged(me);
                }
                catch(IOException i){
                    System.out.println("Error connecting to server.");
                    me = new ModelEvent(this, 3, "", "IP Address example: 127.0.0.1");
                    notifyChanged(me);
                    return;
                    //i.printStackTrace();
                }
            }
        };
        Thread clientThread = new Thread(clientTalk);
        clientThread.start();
        if (!talking){
            return;
        }
        
        try{
            in.close();
            in = null;
            out.close();
            out = null;
            sock.close();
            sock = null;
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
    
    public void closeProgram(){
        System.out.println("Closing program.\nGoodbye.");
        closeConnections();
        System.exit(0);
    }
    
    public void closeConnections(){
        try{
            if (sock != null){
                sock.close();
            }
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
}