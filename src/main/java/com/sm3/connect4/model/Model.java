package com.sm3.connect4.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
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
