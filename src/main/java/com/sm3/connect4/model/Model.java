package com.sm3.connect4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A abstract class used to implement the ModelEvent and ModelListener classes
 * into the model.
 */
public class Model {
	private List<ModelListener> listeners = new ArrayList<ModelListener>(5);

	/**
	 * Notifies the listeners of an event.
	 * 
	 * @param event A ModelEvent class object used to represent the changed
	 *              information of the model.
	 */
	public void notifyChanged(ModelEvent event) {
		for (ModelListener ml : listeners) {
			ml.modelChanged(event);
		}
	}

	/**
	 * Adds a ModelListener to the listeners list.
	 * 
	 * @param l A ModelListener class object to be added to the listeners list.
	 */
	public void addModelListener(ModelListener l) {
		listeners.add(l);
	}

	/**
	 * Removes a ModelListener to the listeners list.
	 * 
	 * @param l A ModelListener class object to be removed from the listeners list.
	 */
	public void removeModelListener(ModelListener l) {
		listeners.remove(l);
	}

}
