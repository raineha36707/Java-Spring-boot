package com.infytel.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infytel.dto.EntityList;
import com.infytel.dto.PlanDTO;
import com.infytel.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController 
{
	
	@Autowired
	private PlanService planService;   
	
	//Get all available plans
	@GetMapping(produces = {"application/json","application/xml"})
	public EntityList<PlanDTO> fetchPlans() 
	{
		return planService.fetchPlans();
	}
	
	//Gets plans based on localRate
	@GetMapping(value = "/{query}/plan", produces = {"application/json","application/xml"})
	public EntityList<PlanDTO> plansLocalRate(@MatrixVariable(pathVar="query") Map<String, List<Integer>> map ) 
	{
		 Set<String> keysLocalRates = map.keySet();
		 ArrayList localRates = new ArrayList();//this is generic because of the strange issue of casting and assigning
		 Set<Integer> localRatesFinal = new HashSet<>();
		for(String key : keysLocalRates)
		{
			for(int i=0;i<map.get(key).size();i++)
			{
				localRates.add(map.get(key).get(i));
				
			}
		}
		Iterator it = localRates.iterator();
		while(it.hasNext())
		{
			int rate =Integer.parseInt((String)it.next());
			localRatesFinal.add(rate);
		}
		
		return planService.plansLocalRate(localRatesFinal);
	}
	//fetching the plans by id
		@GetMapping(value ="/{planId}", params = "version=1" )
		public PlanDTO fetchPlanById(@PathVariable("planId") int planId)
		{
			return planService.fetchPlanById(planId);
		}
		@GetMapping(value ="/{planId}", params = "version=2" )
		public Map<String, Integer> fetchPlanByIdv2(@PathVariable("planId") int planId)
		{
			return planService.fetchPlanByIdv2(planId);
		}
}
