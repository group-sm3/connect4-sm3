package view;
import model.ClientModel;
import cont.ClientController;

/**
 * View interfaces simply requires that all subclasses implement setting and
 * getting of ClientController and ClientModel objects.
 * @author Anne
 */
public interface View {

    /**
     *
     * @param sc Sets ClientController object.
     */
    void setCont(ClientController sc);

    /**
     *
     * @return Returns ClientController object.
     */
    ClientController getCont();

    /**
     *
     * @param sm Sets ClientModel object.
     */
    void setModel(ClientModel sm);

    /**
     *
     * @return Returns ClientModel object.
     */
    ClientModel getModel();    
}
