/*
    The JFrameView is the root of the View class hierarchy for top level 
    Swing frames.  It allows a controller and a model to be registered 
    and can also register itself as an obclient of that model.

    Requires the implementation of modelChanged(ModelEvent Event) method 
    to enable it to work with the notification mechanism in java.
 */
package client.view;
import client.view.*;
import javax.swing.*;
import client.cont.ClientController;
import client.model.ClientModel;
import client.model.ModelListener;
import client.model.AbstractModel;

/**
 *
 * @author Anne
 */
public abstract class JFrameView extends JFrame implements View, ModelListener{
    private ClientModel model;
    private ClientController cont;
    
    public JFrameView(ClientModel mod, ClientController con){
        setModel(mod);
        setCont(con);
    }
    // what?
    public void registerWithModel(){
        ((AbstractModel)model).addModelListener(this);
    }
    public ClientController getCont(){
        return cont;
    }
    
    public void setCont(ClientController cont){
        this.cont = cont;
    }
    
    public ClientModel getModel(){
        return model;
    }
    
    public void setModel(ClientModel model){
        this.model = model;
        registerWithModel();
    }
    

//    public void setCont(ClientController sc){
//        this.cont = sc;
//    }
//    public ClientController getCont(){
//        return this.cont;
//    }
//    public void setModel(ClientModel sm){
//        this.model = sm;
//    }
//    public ClientModel getModel(){
//        return this.model;
//    }
}