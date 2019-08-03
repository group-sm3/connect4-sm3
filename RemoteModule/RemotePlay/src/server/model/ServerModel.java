/*
    
 */
package server.model;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

/**
 *
 * @author Anne
 */
public class ServerModel extends AbstractModel {
    private Integer portNumber = 0;
    private Boolean listening = false;
    private DataInputStream in = null;
    private ServerSocket ssock = null;
    private Socket csock = null;
    ModelEvent me = null;
    final ExecutorService clientProcessingPool  = Executors.newFixedThreadPool(10);
    
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
    
    public void activateListen(){
        //System.out.println("At Server::activateListen()");
        listening = true;
        // start listen-wait thread
        
        Runnable serverListen = new Runnable(){
            @Override
            public void run(){
                try{
                    ssock = new ServerSocket(portNumber);
                    while(listening){
                        csock = ssock.accept();
                        clientProcessingPool.submit(new ClientTask(csock));
                    }
                }
                catch (IOException i){
                    System.out.println(i);
                }
            }
        };
        Thread serverThread = new Thread(serverListen);
        serverThread.start();
        System.out.println("Server listening on port " + portNumber);
    }
    
    private class ClientTask implements Runnable{
        private final Socket csock;
        private ClientTask(Socket cs){
            this.csock = cs;
        }
        @Override
        public void run(){
            System.out.println("Client accepted.");
            // Do client stuff.
            try{
                csock.close();
            }
            catch(IOException i){
                System.out.println(i);
            }
        }
    };
    
    public void closeProgram(){
        System.out.println("Closing program.");
        listening = false;
        closeConnections();
        System.out.println("Goodbye.");
        System.exit(0);
    }
    // Closes all client connections, but does not end program.
    
    public void closeConnections(){
        try{
            // close connection after client requests termination
            System.out.println("Closing connection.");
            // Close client sockets... eventually.
            // Close server sockets.
            if(csock!=null){
                csock.close();
                in.close();           
            }
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
}


