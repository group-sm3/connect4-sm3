package cont;
import model.ClientModel;
import view.ClientView;

/**
 * Abstract class reflects its super interface in that it sets methods to 
 * set and get ClientView and ClientModel objects.
 * @author Anne Leach
 */
public abstract class AbstractController implements Controller{
    private ClientView view;
    private ClientModel model;

    /**
     *
     * @param sv ClientView object
     */
    public void setView(ClientView sv){
        this.view = sv;
    }

    /**
     *
     * @return ClientView object
     */
    public ClientView getView(){
        return view;
    }

    /**
     *
     * @param sm ClientModel object
     */
    public void setModel(ClientModel sm){
        this.model = sm;
    }

    /**
     *
     * @return ClientModel object
     */
    public ClientModel getModel(){
        return model;
    }
}
