/*
    Notes here.
 */
package server.cont;
import server.model.ServerModel;
import server.view.ServerView;
import server.view.JFrameView;

/**
 *
 * @author anne
 */
public class ServerController extends AbstractController{
    Boolean isValidPort = false;
    public ServerController(){
        // Link the model and view.
        setModel(new ServerModel());
        setView(new ServerView((ServerModel)getModel(),this));
        ((JFrameView)getView()).setVisible(true);
    }
    
    public void operation(String option, String txt){
        if (option == (ServerView.LISTEN)){
            ((ServerModel)getModel()).validateTextField(txt);
            Integer tempInt = ((ServerModel)getModel()).getPortNumber();
            if (tempInt != 0){
                //((ServerModel)getModel()).activateListen();
            }
        }
        else{
            ((ServerModel)getModel()).error();
        }
    }
  
    public void closeProgram(){
        ((ServerModel)getModel()).closeProgram();
    }
}