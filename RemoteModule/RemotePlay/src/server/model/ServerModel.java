/*
    
 */
package server.model;
import java.net.*;
import java.io.*;

/**
 *
 * @author Anne
 */
public class ServerModel extends AbstractModel {
    // the calculator had methods such as add(), sub(), equals().
    // here I will have data and methods of the server menu instead.
    Integer portNumber = null;
    Boolean listening = false;
    private DataInputStream in = null;
    private ServerSocket servSock = null;
    private Socket sock = null;
    ModelEvent me = null;
    
    public void setPortNumber(Integer pn){
        this.portNumber = pn;
    }
    
    public int getPortNumber(){
        return portNumber;
    }
    
    public void error(){
        ModelEvent me = new ModelEvent(this, 1, "", "Error!");
        notifyChanged(me);
    }
    public Boolean validatePortNum(String portNum){
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
        return true;
    }
    // This pulls values from the textfield and ensures that the port number
    // the server must listen on is valid (integer [2048, 65535]).
    public void validateTextField(String txt){
        System.out.println("Attempted portNum: " + txt);
        if (!validatePortNum(txt)){
            me = new ModelEvent(this, 1, "", "Valid port number: [2048, 65535]");
            notifyChanged(me);    
        }
        else{
            System.out.println("Port number valid: " + txt);
        }
    }
    
    // This creates a socket based on validated port number and waits for 
    // a client to connect.  Later will modify to multithread multiple clients.
    public void activateListen(){
        System.out.println("At Server::activateListen()");
        // start listen-wait mode 
        try{
            servSock = new ServerSocket(portNumber);
            System.out.println("\nServer online.  Waiting for client...");

            // once client requests cnx, accept
            sock = servSock.accept();
            System.out.println("Client connection accepted.");
            
            me = new ModelEvent(this, 1, "", "Connected!");
            notifyChanged(me);
                
            // initiate stream to receive client input
            // maybe input will simply forward request packets as instances of a Request class
            // for now simply a reflection until command "endgame" received
            in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));

            // process data client sent
            String line = "";
            while (!line.equals("endgame")){
                    line = in.readUTF();
                    System.out.println(line);
            }
        }
        catch(IOException i){
            System.out.println(i);
        }  
    }
    // Closes all client connections, but does not end program.
    public void closeConnections(){
        try{
            // close connection after client requests termination
            System.out.println("Game ended.  Closing connection.\n");
            sock.close();
            in.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
        
    }
}


