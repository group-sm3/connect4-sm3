package server.cont;
import server.model.ServerModel;
import server.view.ServerView;
/**
 * Interface controller requires setting and getting methods for its View and
 * Controller objects.
 * @author Anne
 */
public interface Controller {
    void setView(ServerView sv);
    ServerView getView();
    void setModel(ServerModel sm);
    ServerModel getModel();    
}
