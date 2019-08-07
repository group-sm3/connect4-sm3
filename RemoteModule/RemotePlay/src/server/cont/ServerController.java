package server.cont;
import server.model.ServerModel;
import server.view.ServerView;
import server.view.JFrameView;

/**
 * ServerController mediates between ServerView and ServerModel, specifically
 * which operation the Model must carry out in response to what change the 
 * View sent.
 * @author Anne Leach
 */
public class ServerController extends AbstractController{
    Boolean isValidPort = false;

    /**
     * Constructor which sets ServerModel and ServerView objects, and also 
     * activates the menu to visible.
     */
    public ServerController(){
        // Link the model and view.
        setModel(new ServerModel());
        setView(new ServerView((ServerModel)getModel(),this));
        ((JFrameView)getView()).setVisible(true);
    }
    
    /**
     * Based on the option provided by the ServerView, the controller instructs
     * the ServerModel to carry out some action.
     * @param option A String which specifies the user's menu change.
     * @param txt String which contains the text a user attempted to enter into
     * a teJTextField.
     */
    public void operation(String option, String txt){
        if (option == (ServerView.LISTEN)){
            ((ServerModel)getModel()).validateTextField(txt);
            Integer tempInt = ((ServerModel)getModel()).getPortNumber();
            if (tempInt != 0){
                ((ServerModel)getModel()).activateListen();
            }
        }
        else{
            ((ServerModel)getModel()).error();
        }
    }
  
    /**
     * After use clicks red X button in menu, the program takes over control
     * of closing to properly clean up resources first. This instructs the 
     * ServerModel to begin that process.
     */
    public void closeProgram(){
        ((ServerModel)getModel()).closeProgram();
    }
}