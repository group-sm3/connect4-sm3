package model;

/**
 *  This interface must be implement by all classes that wish to act as a 
 *  model.
 * @author Anne
 */
public interface Model {

    /**
     * All derivative Model classes must implement a method notify changed to 
     * alert the change of state.
     * @param event
     */
    void notifyChanged(ModelEvent event);
}
