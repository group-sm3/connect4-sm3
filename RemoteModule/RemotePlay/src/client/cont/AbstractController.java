/*
    Root of Cont class hierarchy.  Defines basic facilities required to 
    implement a controller, i.e. a view and model to be linked to a controller.
 */
package client.cont;
import client.cont.*;
import client.model.ClientModel;
import client.view.ClientView;

/**
 *
 * @author Anne
 */
public abstract class AbstractController implements Controller{
    private ClientView view;
    private ClientModel model;

    public void setView(ClientView sv){
        this.view = sv;
    }
    public ClientView getView(){
        return view;
    }
    public void setModel(ClientModel sm){
        this.model = sm;
    }
    public ClientModel getModel(){
        return model;
    }
}
