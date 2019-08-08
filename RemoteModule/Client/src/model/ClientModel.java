package model;
import java.net.*;
import java.io.*;

/**
 * ClientModel defines the structure and behavior of the Client program.  
 * It validates the requested port number and ip address, activates the socket
 * should the inputs be valid, manages the status of the connection, and 
 * cleanly closes the program with cleanup before terminating.
 * 
 * @author Anne Leach
 */
public class ClientModel extends AbstractModel {
    private Integer portNumber = 0;
    Boolean talking = false;
    String address = "";
    String line = "";
    private DataOutputStream out = null;
    private BufferedReader in = null;
    private Socket sock = null;
    ModelEvent me = null;
    
    /**
     * Sets port number.
     * @param pn Integer port number
     */
    public void setPortNumber(Integer pn){
        this.portNumber = pn;
    }
    
    /**
     * Sets ip address.
     * @param addr String ip address
     */
    public void setAddress(String addr){
        this.address = addr;
    }
    
    /**
     * Returns current port number.
     * @return int port number
     */
    public int getPortNumber(){
        return portNumber;
    }
    
    /**
     * Returns current ip address.
     * @return String ip address
     */
    public String getAddress(){
        return address;
    }
    
    /**
     * Alerts ClientView of event of error.
     */
    public void error(){
        ModelEvent me = new ModelEvent(this, 1, "", "Error!");
        notifyChanged(me);
    }
    
    /**
     * Validates the port number and address.  Currently port number is 
     * validated actively, but the ip address is enforced through runtime errors 
     * that are caught and updates the view with a request to enter a "valid 
     * port number."  Would like to add more robust ip addressing in the future.
     * @param portNum String port number
     * @param address String ip address
     * @return Boolean is valid / is not valid
     */
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
    


    /**
     * This pulls values from the textfields and ensures that the port number
     * the server must listen on is valid (integer [2048, 65535]).  Alerts user
     * if the requested port is invalid.
     * @param portNum String port number
     * @param addr String address
     */
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
    
    /**
     * Tries to establish connection with server based upon current ip address
     * and port number.  Upon success or failure, the method notifies any
     * listening entities of change (i.e. updates ServerView to alert user).
     * Furthermore, it establishes separate threads so the Client can 
     * continue talking with server, without freezing the user interface.
     */
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
    
    /**
     * Initiates the cleanup of program before terminating program.
     */
    public void closeProgram(){
        System.out.println("Closing program.\nGoodbye.");
        closeConnections();
        System.exit(0);
    }
    
    /**
     * Checks for open sockets and stream.  If open, closes them.
     */
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