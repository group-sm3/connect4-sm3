package model;
import java.awt.event.ActionEvent;

/**
 * This is used to notify objects of changes in the state of the model.
 * @author Anne Leach
 */
public class ModelEvent extends ActionEvent{
    private String message;
    /**
     * Constructor which calls superclass Constructor, in addition to setting
     * a String message which is the text which some item in the ClientView 
     * must display.
     * @param obj Passes self
     * @param id int id specifies the type of event (e.g. change some JTextField
     * or JLabel.
     * @param msg unused
     * @param message String which will be pasted to respective area (see id).
     */
    public ModelEvent(Object obj, int id, String msg, String message){
        super(obj, id, msg);
        this.message = message;
    }
    /**
     * Returns event message to update ClientView.
     * @return String update message.
     */
    public String getMessage(){return message;}
    @Override
    public int getID(){return id;}
}
