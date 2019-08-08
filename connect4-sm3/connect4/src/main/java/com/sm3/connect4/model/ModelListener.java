package com.sm3.connect4.model;

/**
 * A interface used to recieve messages from the model by the ModelEvent class.
 */
public interface ModelListener {
	/**
	 * A method used when recieving a message from the ModelEvent class object.
	 * 
	 * @param event A ModelEvent class object containing the information from the
	 *              model for the view.
	 */
	public void modelChanged(ModelEvent event);
}
