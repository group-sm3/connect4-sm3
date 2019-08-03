/*
    This is used to notify objects of changes in the state of the model.
 */
package server.model;
import java.awt.event.ActionEvent;

/**
 *
 * @author Anne
 */
public class ModelEvent extends ActionEvent{
    private String message;
    public ModelEvent(Object obj, int id, String msg, String message){
        super(obj, id, msg);
        this.message = message;
    }
    public String getMessage(){return message;}
    @Override
    public int getID(){return id;}
}
