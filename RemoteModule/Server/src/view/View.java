package view;
import model.ServerModel;
import cont.ServerController;

/**
 * View interface sets the methods that subclasses must implement.
 * @author Anne
 */
public interface View {
    void setCont(ServerController sc);
    ServerController getCont();
    void setModel(ServerModel sm);
    ServerModel getModel();    
}
