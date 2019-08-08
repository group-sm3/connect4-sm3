package view;
import javax.swing.*;
import cont.ServerController;
import model.ServerModel;
import model.ModelListener;
import model.AbstractModel;
/**
 *  The JFrameView is the root of the View class hierarchy for top level 
 *  Swing frames.  It allows a controller and a model to be registered 
 *  and can also register itself as an observer of that model.
 * 
 *  Requires the implementation of modelChanged(ModelEvent Event) method 
 *  to enable it to work with the notification mechanism in java.
 *  @author Anne
 */
public abstract class JFrameView extends JFrame implements View, ModelListener{
    private ServerModel model;
    private ServerController cont;
    
    /**
     *
     * @param mod ServerModel object
     * @param con ServerController object
     */
    public JFrameView(ServerModel mod, ServerController con){
        setModel(mod);
        setCont(con);
    }

    /**
     * Sets modellisteners to the model..
     */
    public void registerWithModel(){
        ((AbstractModel)model).addModelListener(this);
    }

    /**
     *
     * @return ServerController object.
     */
    public ServerController getCont(){
        return cont;
    }

    /**
     *
     * @param cont Sets ServerController object.
     */
    public void setCont(ServerController cont){
        this.cont = cont;
    }

    /**
     *
     * @return model Returns ServerModel object.
     */
    public ServerModel getModel(){
        return model;
    }

    /**
     * 
     * @param model ServerModel object to set.
     */
    public void setModel(ServerModel model){
        this.model = model;
        registerWithModel();
    }
}