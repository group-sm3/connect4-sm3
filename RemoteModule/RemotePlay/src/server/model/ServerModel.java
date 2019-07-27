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
    int portNumber;
    Boolean listening;
    private DataInputStream in = null;
    private ServerSocket servSock = null;
    private Socket sock = null;
    
    public void setPortNumber(Integer pn){
        this.portNumber = pn;
    }
    public void error(){
        ModelEvent me = new ModelEvent(this, 1, "", "Error!");
        notifyChanged(me);
    }
    public void activateListen(){
        System.out.println("At activateListen()");
    // start listen-wait mode 
        try{
            servSock = new ServerSocket(portNumber);
            System.out.println("\nServer online.  Waiting for client...");

            // once client requests cnx, accept
            sock = servSock.accept();
            System.out.println("Client connection accepted.");
            
            ModelEvent me = new ModelEvent(this, 1, "", "Connected!");
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


