package client.cont;
import client.cont.*;
import client.model.ClientModel;
import client.view.ClientView;
import client.view.JFrameView;

/**
 * Mediates interaction between ClientView and ClientModel objects.  
 * @author Anne Leach
 */
public class ClientController extends AbstractController{
    Boolean isValidPort = false;

    /**
     * Constructor sets ClientModel and ClientView objects, and also sets the
     * menu to visible.
     */
    public ClientController(){
        // Link the model and view.
        setModel(new ClientModel());
        setView(new ClientView((ClientModel)getModel(),this));
        ((JFrameView)getView()).setVisible(true);
    }
    /**
     * Method determines which action to invoke via the ServerModel. 
     * @param option String what action was taken in ClientView
     * @param portNumber String requested, unvalidated port number
     * @param address String requested, unvalidated ip address
     */
    public void operation(String option, String portNumber, String address){
        if (option == (ClientView.CONNECT)){
            ((ClientModel)getModel()).validateTextFields(portNumber, address);
            int temp = ((ClientModel)getModel()).getPortNumber();
            if (temp != 0){
                ((ClientModel)getModel()).activateListen();
            }      
        }
        else{
            ((ClientModel)getModel()).error();
        }
    }

    /**
     * The program takes over control when user clicks red X, ensure proper
     * resources cleanup before terminating.  This method invokes ClientModel
     * to begin that process.
     */
    public void closeProgram(){
        ((ClientModel)getModel()).closeProgram();
    }  
}