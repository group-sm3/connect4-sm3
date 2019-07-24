/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteplay.model;

/**
 *
 * @author anned
 */
public class ServerModel {
    private String portNumber = "";
    private Boolean online = false;
    
    public String getPortNumber(){
        return portNumber;
    }
    public void setPortNumber(String pn){
        this.portNumber = pn;
    }
    public Boolean getOnline(){
        return online;
    }
    public void setOnline(Boolean on){
        this.online = on;
    }
}
