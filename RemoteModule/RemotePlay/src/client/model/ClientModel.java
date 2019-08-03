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
    int portNumber;
    Boolean talking;
    String address, line;
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
        try{
            tempInt = Integer.parseInt(portNum);
        }
        catch(NumberFormatException e){
            return false;
        }
        catch(NullPointerException n){
            return false;
        }
        if (tempInt > 65535 || tempInt < 2048){
            return false;
        }
        // If time allows, will add text validation.        
        return true;
    }
    // This pulls values from the textfield and ensures that the port number
    // the server must listen on is valid (integer [2048, 65535]).
    public void validateTextFields(String portNum, String addr){
        System.out.println("Attempted portNum: " + portNum);
        System.out.println("Attempted ipAddr " + addr);
        if (!validatePortAddr(portNum, addr)){
            me = new ModelEvent(this, 1, "", "Valid port number: [2048, 65535]");
            notifyChanged(me);    
        }
        else{
            System.out.println("Port number valid: " + portNumber);
        }
    }
    
    public void activateListen(){
        System.out.println("At Client::activateListen()");
    // start listen-wait mode 
        try{
            sock = new Socket(address, portNumber);
            System.out.println("\nClient connected to server.");
            
            ModelEvent me = new ModelEvent(this, 1, "", "Connected!");
            notifyChanged(me);
                
            // initiate stream to receive client input
            // maybe input will simply forward request packets as instances of a Request class
            // for now simply a reflection until command "endgame" received
            in = new BufferedReader(new InputStreamReader(System.in));

            // send data to client
            out = new DataOutputStream(sock.getOutputStream());

            // close connection after client requests termination
            System.out.println("Game ended.  Closing connection.\n");
            sock.close();
            in.close();
        }
        catch(UnknownHostException u){
            System.out.println(u);
        }  
        catch(IOException i){
            System.out.println(i);
        }  
        
        // Get data input.
        line = "";
        while (!line.equals("endgame")){
            try{
                line = in.readLine();
                out.writeUTF(line);
            }
            catch(IOException i){
                System.out.println(i);
            }
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