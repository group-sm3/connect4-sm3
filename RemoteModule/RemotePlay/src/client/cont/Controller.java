package client.cont;
import client.model.ClientModel;
import client.view.ClientView;
/**
 * Interface requires that subclasses implement methods for setting and getting
 * ClientView and ClientModel objects.
 * @author Anne Leach
 */
public interface Controller {

    /**
     * 
     * @param sv Sets ClientView object
     */
    void setView(ClientView sv);

    /**
     *
     * @return Returns ClientView object
     */
    ClientView getView();

    /**
     *
     * @param sm Sets ClientModel object
     */
    void setModel(ClientModel sm);

    /**
     *
     * @return Returns ClientModel object
     */
    ClientModel getModel();    
}
