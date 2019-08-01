/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;
import client.view.*;
import client.model.ClientModel;
import client.cont.ClientController;

/**
 *
 * @author Anne
 */
public interface View {
    void setCont(ClientController sc);
    ClientController getCont();
    void setModel(ClientModel sm);
    ClientModel getModel();    
}
