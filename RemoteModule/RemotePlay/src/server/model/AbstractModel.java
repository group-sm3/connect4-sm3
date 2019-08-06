package server.model;
import java.util.ArrayList;
import java.util.List;

/**
 * The AbstractModel class defines derivative model behavior, specifically 
 * adding and removing ModelListeners, as well as notifying all listeners of 
 * some status change.
 * @author Anne 
 */
public abstract class AbstractModel implements Model {
	private List<ModelListener> listeners = new ArrayList<ModelListener>(5);
	
    /**
     * Alerts all relevant listeners of some change.
     * @param event The ModelEvent object of interest.
     */
    public void notifyChanged(ModelEvent event){
		for(ModelListener ml : listeners) {
			ml.modelChanged(event);
		}
	}

    /**
     * Adds ModelListener object to list.
     * @param l ModelListener object to add to list of listeners.
     */
    public void addModelListener(ModelListener l){
		listeners.add(l);
	}

    /**
     * Removes ModelListener object from list.
     * @param l ModelListener object to remove from list of listeners.
     */
    public void removeModelListener(ModelListener l){
		listeners.remove(l);
	}
}
