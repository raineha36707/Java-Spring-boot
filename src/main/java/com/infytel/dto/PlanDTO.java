package com.infytel.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.infytel.entity.PlanEntity;

@XmlRootElement
public class PlanDTO {

	
	Integer planId;
	
	String planName;

	Integer nationalRate;

	Integer localRate;
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getNationalRate() {
		return nationalRate;
	}
	public void setNationalRate(Integer nationalRate) {
		this.nationalRate = nationalRate;
	}
	public Integer getLocalRate() {
		return localRate;
	}
	public void setLocalRate(Integer localRate) {
		this.localRate = localRate;
	}
	public PlanDTO() {
		super();
	}

	@Override
	public String toString() {
		return "PlanDTO [planId=" + planId + ", planName=" + planName + ", nationalRate=" + nationalRate
				+ ", localRate=" + localRate + "]";
	}
	// Converts Entity into DTO
	public static PlanDTO valueOf(PlanEntity plan) {
		PlanDTO planDTO= new PlanDTO();
		planDTO.setLocalRate(plan.getLocalRate());
		planDTO.setNationalRate(plan.getNationalRate());
		planDTO.setPlanId(plan.getPlanId());
		planDTO.setPlanName(plan.getPlanName());
		return planDTO;
	}
	// Converts DTO into Entity
	public PlanEntity createEntity()
	{
		PlanEntity plan = new PlanEntity();
		plan.setPlanId(this.planId);
		plan.setPlanName(this.planName);
		plan.setNationalRate(this.nationalRate);
		plan.setLocalRate(this.localRate);
		return plan;
	}

}
