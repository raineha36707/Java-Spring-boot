package com.infytel.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infytel.dto.CallDetailsDTO;
import com.infytel.entity.CallDetailsEntity;
import com.infytel.repository.CallDetailsRepository;

@Service
public class CallDetailsService 
{
	@Autowired
	private CallDetailsRepository callDetailsRepository;
	
	public List<CallDetailsDTO> fetchCallDetails(long calledBy, LocalDate calledOn)
	{
		List<CallDetailsEntity> callDetailsEntities = callDetailsRepository.fetchCallDetails(calledBy,  calledOn);
		List<CallDetailsDTO> callDetailsDTOs = new ArrayList<>();
		for(CallDetailsEntity callDetails : callDetailsEntities)
		{
			callDetailsDTOs.add(CallDetailsDTO.valueOf(callDetails));
		}
		return callDetailsDTOs;
	}
}
