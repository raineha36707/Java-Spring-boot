package com.infytel.dto;

import java.time.LocalDate;

import javax.validation.constraints.PositiveOrZero;

import com.infytel.entity.CallDetailsEntity;


public class CallDetailsDTO {

	private long calledBy;
	private long calledTo;
	private LocalDate calledOn;
	@PositiveOrZero(message="{call.duration.invalid}")
	private int duration;

	public long getCalledBy() {
		return calledBy;
	}

	public void setCalledBy(long calledBy) {
		this.calledBy = calledBy;
	}

	public long getCalledTo() {
		return calledTo;
	}

	public void setCalledTo(long calledTo) {
		this.calledTo = calledTo;
	}
	

	public LocalDate getCalledOn() {
		return calledOn;
	}

	public void setCalledOn(LocalDate calledOn) {
		this.calledOn = calledOn;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "CallDetailsDTO [calledBy=" + calledBy + ", calledTo=" + calledTo + ", calledOn=" + calledOn
				+ ", duration=" + duration + "]";
	}
	
	// Converts Entity into DTO
	public static CallDetailsDTO valueOf(CallDetailsEntity callDetails) 
	{
			CallDetailsDTO callsDTO = new CallDetailsDTO();
			callsDTO.setCalledBy(callDetails.getCalledBy());
			callsDTO.setCalledOn(callDetails.getCalledOn());
			callsDTO.setCalledTo(callDetails.getCalledTo());
			callsDTO.setDuration(callDetails.getDuration());
			return callsDTO;
	}



}
