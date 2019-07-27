/*
    The JFrameView is the root of the View class hierarchy for top level 
    Swing frames.  It allows a controller and a model to be registered 
    and can also register itself as an observer of that model.

    Requires the implementation of modelChanged(ModelEvent Event) method 
    to enable it to work with the notification mechanism in java.
 */
package server.view;
import javax.swing.*;
import server.cont.ServerController;
import server.model.ServerModel;
import server.model.ModelListener;
import server.model.AbstractModel;

/**
 *
 * @author Anne
 */
public abstract class JFrameView extends JFrame implements View, ModelListener{
    private ServerModel model;
    private ServerController cont;
    
    public JFrameView(ServerModel mod, ServerController con){
        setModel(mod);
        setCont(con);
    }
    // what?
    public void registerWithModel(){
        ((AbstractModel)model).addModelListener(this);
    }
    public ServerController getCont(){
        return cont;
    }
    
    public void setCont(ServerController cont){
        this.cont = cont;
    }
    
    public ServerModel getModel(){
        return model;
    }
    
    public void setModel(ServerModel model){
        this.model = model;
        registerWithModel();
    }
    

//    public void setCont(ServerController sc){
//        this.cont = sc;
//    }
//    public ServerController getCont(){
//        return this.cont;
//    }
//    public void setModel(ServerModel sm){
//        this.model = sm;
//    }
//    public ServerModel getModel(){
//        return this.model;
//    }
}