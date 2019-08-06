/*
    Root of Cont class hierarchy.  Defines basic facilities required to 
    implement a controller, i.e. a view and model to be linked to a controller.
 */
package server.cont;
import server.model.ServerModel;
import server.view.ServerView;

/**
 *
 * @author Anne
 */
public abstract class AbstractController implements Controller{
    private ServerView view;
    private ServerModel model;

    /**
     *
     * @param sv
     */
    public void setView(ServerView sv){
        this.view = sv;
    }

    /**
     *
     * @return
     */
    public ServerView getView(){
        return view;
    }

    /**
     *
     * @param sm
     */
    public void setModel(ServerModel sm){
        this.model = sm;
    }

    /**
     *
     * @return
     */
    public ServerModel getModel(){
        return model;
    }
}
