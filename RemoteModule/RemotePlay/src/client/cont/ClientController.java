/*
    Notes here.
 */
package client.cont;
import client.cont.*;
import client.model.ClientModel;
import client.view.ClientView;
import client.view.JFrameView;

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
    public void operation(String option){
        if (option == (ClientView.CONNECT)){
            //getIsValidPort(isValidPort);
//            while(!isValidPort){
//                ((ClientModel)getModel()).invalidInput();
//            }
            ((ClientModel)getModel()).activateListen();
        }
        else{
            ((ClientModel)getModel()).error();
        }
    }
    // validate user input
//    void getIsValidPort(Boolean isValidPort){
//        if ((ClientModel)getPortNumber() != 5050){
//            
//        }
//    }
}