package client.model;

import client.model.*;

/**
 * Alerts any listening entities that the model has changed its state.
 * @author Anne Leach
 */
public interface Model {
    void notifyChanged(ModelEvent event);
}
