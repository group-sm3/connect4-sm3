package server.model;
import java.net.*; 
import java.io.*; 
import java.lang.Thread;
/**
 * PortListener classes enables the program to continue listening on a port
 * for client connections in a distinct thread, without freezing the user menu.
 * @author Anne
 */
public class PortListener extends Thread{
    Socket csock = null;
    BufferedReader in = null;
    DataOutputStream out = null;
    String line = "";
    
    /**
     * Constructor which sets the fields required to establish socket.
     * @param csock Socket object.
     * @param in BufferedReader object.
     * @param out DataOutputStream object.
     */
    public void PortListener(Socket csock, BufferedReader in, DataOutputStream out){
        this.csock = csock;
        this.in = in;
        this.out = out;
    }
    
    @Override
    /**
     * For the moment, the program will simply establish connection and echo
     * lines.  It is up to the game designers as to how they want to incorporate
     * this into the main game infrastructure.
    */
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
    }
}
