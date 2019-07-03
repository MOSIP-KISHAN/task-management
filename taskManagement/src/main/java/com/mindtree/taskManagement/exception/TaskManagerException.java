
package com.mindtree.taskManagement.exception;


public class TaskManagerException extends RuntimeException {

	private static final long serialVersionUID = -3522817090628105177L;
	
	public TaskManagerException(String code,String message) {
		super(code + " --> " + message);
	}
	

	/**
	 * @param message
	 * @param cause
	 */
	public TaskManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public TaskManagerException(String message) {
		super(message);
	}
	
	

}
