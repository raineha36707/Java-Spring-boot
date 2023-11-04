package com.infytel.exceptions;

public class NoSuchPlanException extends Exception {
 
	private static final long serialVersionUID = 1L;
	
 
	

	public NoSuchPlanException() {
		super();
	}

	public NoSuchPlanException(String errors) {
		super(errors);
		
	}
	
	

}
