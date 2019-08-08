package model;

/**
 * ModelListener Interface requires only that subclasses implement a method
 * to alert Listeners of state changes to update the view.
 * @author Anne
 */
public interface ModelListener {

    /**
     *
     * @param event ModelEvent object which contains information about the 
     * event, such as event ID and a message.
     */
    public void modelChanged(ModelEvent event);
}
