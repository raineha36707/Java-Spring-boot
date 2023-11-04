package com.infytel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infytel.dto.EntityList;
import com.infytel.dto.PlanDTO;
import com.infytel.entity.PlanEntity;
import com.infytel.repository.PlanRepository;

@Service
public class PlanService
{
	@Autowired
	private PlanRepository plansRepository; 
	private EntityList<PlanDTO> plans;
	PlanEntity defaultPlanEntity = null;
	public PlanService()
	{
		defaultPlanEntity = new PlanEntity();
		defaultPlanEntity.setLocalRate(0);
		defaultPlanEntity.setNationalRate(0);
		defaultPlanEntity.setPlanId(0);
		defaultPlanEntity.setPlanName("default");
	}
	public EntityList<PlanDTO> fetchPlans() 
	{
		List<PlanEntity> planEntities = plansRepository.findAll();
		List<PlanDTO> planDTOs = new ArrayList<>();
		for(PlanEntity plan : planEntities)
		{
			planDTOs.add(PlanDTO.valueOf(plan));
		}
		plans = new EntityList<>(planDTOs);
		return plans;
	} 
	
	public EntityList<PlanDTO> plansLocalRate(Set<Integer> localRatesFinal ) 
	{
		List<PlanEntity> planEntities = plansRepository.findByLocalRates(localRatesFinal);
		List<PlanDTO> planDTOs = new ArrayList<>();
		for(PlanEntity plan : planEntities)
		{
			planDTOs.add(PlanDTO.valueOf(plan));
		}
		plans = new EntityList<>(planDTOs);
		return plans;
	}
	public PlanDTO fetchPlanById(int planId)
	{
		return PlanDTO.valueOf(plansRepository.findById(planId).orElse(defaultPlanEntity));
	} 
	public Map<String, Integer> fetchPlanByIdv2(@PathVariable("planId") int planId)
	{
		Map<String,Integer> rates = new HashMap<>();
		PlanDTO planDTO = PlanDTO.valueOf(plansRepository.findById(planId).orElse(defaultPlanEntity));
		rates.put("localRate", planDTO.getLocalRate());
		rates.put("nationalRate", planDTO.getNationalRate());
		return rates;
	}
}
