/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.cont;
import client.cont.*;
import client.model.ClientModel;
import client.view.ClientView;
/**
 *
 * @author Anne
 */
public interface Controller {
    void setView(ClientView sv);
    ClientView getView();
    void setModel(ClientModel sm);
    ClientModel getModel();    
}
