package client.model;

import client.model.*;

/**
 * Requires only that subclasses implement a method to alert listening entities
 * of change in model state.
 * @author Anne Leach
 */
public interface ModelListener {
    public void modelChanged(ModelEvent event);
}
