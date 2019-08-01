/*
    This interface must be implement by all classes that wish to act as a 
    model.
 */
package client.model;

import client.model.*;

/**
 *
 * @author Anne
 */
public interface Model {
    void notifyChanged(ModelEvent event);
}
