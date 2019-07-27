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
    public ServerController(){
        // Link the model and view.
        setModel(new ServerModel());
        setView(new ServerView((ServerModel)getModel(),this));
        ((JFrameView)getView()).setVisible(true);
    }
   
    // These are command buttons (+, -, clear, equals).
    // Server equivalent (listen)
    public void operation(String option){
        if (option == (ServerView.LISTEN)){
            ((ServerModel)getModel()).activateListen();
        }
        else{
            ((ServerModel)getModel()).error();
        }
    }
}