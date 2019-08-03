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
    private Integer portNumber = 0;
    private Boolean listening = false;
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
        try{tempInt = Integer.parseInt(portNum);}
        catch(NumberFormatException e){return false;}
        catch(NullPointerException n){return false;}
        if (tempInt > 65535 || tempInt < 2048){return false;}
        this.portNumber = tempInt;
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
        else{System.out.println("Port number valid: " + portNumber);}
    }
    
    // This creates a socket based on validated port number and waits for 
    // a client to connect.  Later will modify to multithread multiple clients.
    public void activateListen(){
        System.out.println("At Server::activateListen()");
        // start listen-wait mode 
        
        try{
            servSock = new ServerSocket(portNumber);
            System.out.println("\nServer online at port" + portNumber + ".  Waiting for client...");

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
            while (!line.equals("ENDGAME")){
                    line = in.readUTF();
                    System.out.println(line);
            }
        }
        catch(IOException i){
            System.out.println(i);
        }  
    }
    public void closeProgram(){
        System.out.println("Closing program.");
        closeConnections();
        System.out.println("Goodbye.");
        System.exit(0);
    }
    // Closes all client connections, but does not end program.
    public void closeConnections(){
        try{
            // close connection after client requests termination
            System.out.println("Closing connection.");
            if(sock!=null){
                sock.close();
                in.close();           
            }
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
}


