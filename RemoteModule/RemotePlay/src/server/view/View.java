/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.view;
import server.model.ServerModel;
import server.cont.ServerController;

/**
 *
 * @author Anne
 */
public interface View {
    void setCont(ServerController sc);
    ServerController getCont();
    void setModel(ServerModel sm);
    ServerModel getModel();    
}
