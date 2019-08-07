package server.cont;
import server.model.ServerModel;
import server.view.ServerView;

/**
 * Abstract class specifies behavior (methods) of subclasses.  Simply setting 
 * and getting Server/Controller views/models.
 * @author Anne
 */
public abstract class AbstractController implements Controller{
    private ServerView view;
    private ServerModel model;

    /**
     *
     * @param sv Sets ServerView object.
     */
    public void setView(ServerView sv){
        this.view = sv;
    }

    /**
     *
     * @return Returns ServerView object.
     */
    public ServerView getView(){
        return view;
    }

    /**
     *
     * @param sm Sets ServerMOdel object.
     */
    public void setModel(ServerModel sm){
        this.model = sm;
    }

    /**
     *
     * @return Returns ServerModel object.
     */
    public ServerModel getModel(){
        return model;
    }
}
