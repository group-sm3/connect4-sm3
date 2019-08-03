/*
    Notes here.
 */
package client.cont;
import client.cont.*;
import client.model.ClientModel;
import client.view.ClientView;
import client.view.JFrameView;
import server.model.ServerModel;

/**
 *
 * @author anne
 */
public class ClientController extends AbstractController{
    Boolean isValidPort = false;
    public ClientController(){
        // Link the model and view.
        setModel(new ClientModel());
        setView(new ClientView((ClientModel)getModel(),this));
        ((JFrameView)getView()).setVisible(true);
    }
    
    // These are command buttons (+, -, clear, equals).
    // Client equivalent (listen)
    public void operation(String option, String portNumber, String address){
        if (option == (ClientView.CONNECT)){
            ((ClientModel)getModel()).validateTextFields(portNumber, address);
            ((ClientModel)getModel()).activateListen();
        }
        else{
            ((ClientModel)getModel()).error();
        }
    }
    public void closeProgram(){
        ((ClientModel)getModel()).closeProgram();
    }  
}

