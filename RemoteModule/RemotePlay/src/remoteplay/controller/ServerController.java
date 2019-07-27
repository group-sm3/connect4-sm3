/*
    The Server Controller manages both the Server data (model) 
 */
package remoteplay.controller;
import remoteplay.view.ServerView;
import remoteplay.model.ServerModel;
import java.io.*;
import java.net.*;

/**
 *
 * @author anne
 */
public class ServerController {
    private ServerModel sm;
    private ServerView sv;
    
    public ServerController(ServerView v, ServerModel m){
        //System.out.println("Inside ServerController()");
        this.sm = m;
        this.sv = v;
    }
    public String getPortNumber(){
        return sm.getPortNumber();
    }
    public void setPortNumber(String pn){
        sm.setPortNumber(pn);
    }
    public Boolean getOnline(){
        return sm.getOnline();
    }
    public void setOnline(Boolean on){
        sm.setOnline(on);
    }
    public void updateServerView(){
        sv.printServerStatus(sm.getOnline(), sm.getPortNumber());
    }
}