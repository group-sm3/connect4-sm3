package view;
import javax.swing.*;
import cont.ClientController;
import model.ClientModel;
import model.ModelListener;
import model.AbstractModel;

/**
 * JFrameView abstract class sets the behavior and fields for upper level Swing
 * frames.  It registers a controller and a model, specifically creating a 
 * relationship between listeners and actions.
 * @author Anne Leach
 */
public abstract class JFrameView extends JFrame implements View, ModelListener{
    private ClientModel model;
    private ClientController cont;
    
    /**
     * Constructor which sets the ClientModel object and ClientController 
     * object.
     * @param mod ClientModel object.
     * @param con ClientController object.
     */
    public JFrameView(ClientModel mod, ClientController con){
        setModel(mod);
        setCont(con);
    }

    /**
     * Sets ModelListeners to the model, so that the model is aware of some 
     * change in the view.
     */
    public void registerWithModel(){
        ((AbstractModel)model).addModelListener(this);
    }

    /**
     * 
     * @return Returns ClientController object.
     */
    public ClientController getCont(){
        return cont;
    }
    
    /**
     *
     * @param cont Sets ClientController object.
     */
    public void setCont(ClientController cont){
        this.cont = cont;
    }
    
    /**
     *
     * @return ClientModel object.
     */
    public ClientModel getModel(){
        return model;
    }
    
    /**
     *
     * @param model Sets ClientModel object.
     */
    public void setModel(ClientModel model){
        this.model = model;
        registerWithModel();
    }
}