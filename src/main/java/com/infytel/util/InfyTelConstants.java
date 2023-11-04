package com.infytel.util;


/**
 * The Enum ExceptionConstants.
 */

public enum InfyTelConstants {

	//Exception message constants
	CUSTOMER_NOT_FOUND("customer.not.found"), 
	PLAN_NOT_FOUND("plan.not.found"),
	GENERAL_EXCEPTION_MESSAGE("general.exception"),
	
	//Success message constants
	CUSTOMER_CREATE_SUCCESS("customer.create.success"),
	CUSTOMER_UPDATE_SUCCESS("customer.update.success"),
	CUSTOMER_DELETE_SUCCESS("customer.delete.success");
	
	
	

	private final String type;

	private InfyTelConstants(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
