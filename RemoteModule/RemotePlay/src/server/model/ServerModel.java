package server.model;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

/**
 * ServerModel is the implementation for the structure of the Server.  It 
 * listens on a port specified by user, and manages clients.
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
    
    /**
     * Sets port number to listen on.
     * @param pn Integer port number, enforced to be valid before setting.
     */
    public void setPortNumber(Integer pn){
        this.portNumber = pn;
    }
    
    /**
     * Returns the current port number..
     * @return Integer port number.
     */
    public int getPortNumber(){
        return portNumber;
    }
    
    /**
     * Displays error message to user in event of issue.
     */
    public void error(){
        ModelEvent me = new ModelEvent(this, 1, "", "Error!");
        notifyChanged(me);
    }
    
    /**
     * Validates the user's port number before setting it to the model.
     * @param portNum String which is the user's attempt.
     * @return Boolean true if requested port number valid, false otherwise.
     */
    public Boolean validatePortNum(String portNum){
        Integer tempInt = null;
        try{tempInt = Integer.parseInt(portNum);}
        catch(NumberFormatException e){return false;}
        catch(NullPointerException n){return false;}
        if (tempInt > 65535 || tempInt < 2048){return false;}
        this.portNumber = tempInt;
        return true;
    }

    /**
     * Pulls text string containing user's requested port number and validates.
     * If a false is returned from validatePortNumber(), a message is displayed 
     * to the user via ModelEvent me.
     * @param txt
     */
    public void validateTextField(String txt){
        System.out.println("Attempted portNum: " + txt);
        if (!validatePortNum(txt)){
            me = new ModelEvent(this, 2, "", "Valid port number: [2048, 65535]");
            notifyChanged(me);    
        }
        else{System.out.println("Port number valid: " + portNumber);}
    }
    
    /**
     * Having validated the port number, this methods attempts to set the Server
     * to listen on the requested port.
     */
    public void activateListen(){
        if (listening){
            me = new ModelEvent(this, 1, "", "Already listening on port " + portNumber + ". No change.");
            notifyChanged(me);
            return;
        }
        // start listen-wait thread        
        Runnable serverListen = new Runnable(){
            @Override
            public void run(){
                listening = true;
                me = new ModelEvent(this, 1, "", "Listening on port " + portNumber + ".");
                notifyChanged(me);
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
    /**
     * Inner class which handles the interaction from the connected client and 
     * server, freeing the server to continue listening on another thread.
     */
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
    
    /**
     * Engages a healthy shutdown and freeing up of system resources.  Initiated
     * by user clicking red X in the GUI menu.
     */
    public void closeProgram(){
        System.out.println("Closing program.");
        listening = false;
        closeConnections();
        System.out.println("Goodbye.");
        System.exit(0);
    }
    
    /**
     * Checks for and closes open sockets and streams.
     */
    public void closeConnections(){
        try{
            System.out.println("Closing connection.");
            if(ssock!=null){
                ssock.close();        
            }
            if(in != null){
                in.close();
            }
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
}


