package model;
import java.util.ArrayList;
import java.util.List;

/**
 * The abstract model class provides basic notification behavior that
 * subclasses must implement.
 * @author Anne Leach
 */
public abstract class AbstractModel implements Model {
	private List<ModelListener> listeners = new ArrayList<ModelListener>(5);
	
    /**
     * Alerts any listening entities of changes in model (i.e. the ServerView).
     * @param event ModelEvent object to provide info on change
     */
    public void notifyChanged(ModelEvent event){
		for(ModelListener ml : listeners) {
			ml.modelChanged(event);
		}
	}

    /**
     * Adds a ModelListener to a respective list.
     * @param l ModelListener object
     */
    public void addModelListener(ModelListener l){
		listeners.add(l);
	}

    /**
     * Adds a ModelListener to a respective list.
     * @param l ModelListener object
     */
    public void removeModelListener(ModelListener l){
		listeners.remove(l);
	}
}
