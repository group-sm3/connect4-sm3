/*
    The abstract model class provides basic notification behavior.
 */
package client.model;
import client.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anne
 */
public abstract class AbstractModel implements Model {
	private List<ModelListener> listeners = new ArrayList<ModelListener>(5);
	
	public void notifyChanged(ModelEvent event){
		for(ModelListener ml : listeners) {
			ml.modelChanged(event);
		}
	}
	public void addModelListener(ModelListener l){
		listeners.add(l);
	}
	public void removeModelListener(ModelListener l){
		listeners.remove(l);
	}
}
