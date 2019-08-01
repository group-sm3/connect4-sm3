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
    
    public void invalidInput(){
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
            out.close();
            sock.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
}


