package model;
import java.awt.event.ActionEvent;

/**
 * This is used to notify objects of changes in the state of the model.  The 
 * item the view updates is based upon the ModelEent id.  See the key below.
 *      id 1 - JLabel userMessage 
 *      id 2 - JTextField portTextField
 * @author Anne
 */
public class ModelEvent extends ActionEvent{
    private String message;

    /** 
     * Constructor to set field values for ModelEvent instance.
     * @param obj 
     * @param id int value that determines which item of the menu (ServerView)
     * to update.
     * @param msg
     * @param message String value which contains a message to be displayed 
     * by ServerView to inform user of error or state change.
     */
    public ModelEvent(Object obj, int id, String msg, String message){
        super(obj, id, msg);
        this.message = message;
    }

    /**
     * Gets ModelEvent id.
     * @return id Int value which determines ModelEvent type.
     */
    public String getMessage(){return message;}
    @Override
    public int getID(){return id;}
}
