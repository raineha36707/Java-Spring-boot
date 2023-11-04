package com.infytel.controller;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.infytel.dto.CallDetailsDTO;
import com.infytel.service.CallDetailsService;

@RestController
@RequestMapping("/calldetails")
@Validated
public class CallDetailsController 
{
	@Autowired
	private CallDetailsService callDetailsService;
	
	//Fetching call details based on the request parameters being passed along with the URI
	@GetMapping(produces = "application/json")
	public List<CallDetailsDTO> callDetails(@RequestParam("calledBy") long calledBy, 
											@RequestParam("calledOn") 
											@DateTimeFormat(pattern="MM-dd-yyyy") 
											@PastOrPresent(message="{call.calledon.invalid}") LocalDate calledOn)
											//like @PastOrPresent, we have @Future, @Past 
											//and @FutureOrPresent for date/time related attributes
											//@DateTimeFormat annotation has many form of representations and one such is, 
											//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	{
		return callDetailsService.fetchCallDetails(calledBy, calledOn);   
	}
}
