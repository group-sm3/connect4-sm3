/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.cont;
import server.model.ServerModel;
import server.view.ServerView;
/**
 *
 * @author Anne
 */
public interface Controller {
    void setView(ServerView sv);
    ServerView getView();
    void setModel(ServerModel sm);
    ServerModel getModel();    
}
