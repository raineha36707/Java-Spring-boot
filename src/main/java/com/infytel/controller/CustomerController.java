package com.infytel.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infytel.dto.CustomerDTO;
import com.infytel.exceptions.NoSuchCustomerException;
import com.infytel.exceptions.NoSuchPlanException;
import com.infytel.service.CustomerService;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController 
{
	@Autowired
	private CustomerService customerService;
	
	//Fetching customer details
	@GetMapping(produces="application/json")
	public  List<CustomerDTO> fetchCustomer()
	{
		return customerService.fetchCustomer();
	}
		
	//Adding a customer
	@PostMapping(consumes="application/json")
	public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws NoSuchPlanException
	{
		return ResponseEntity.ok(customerService.createCustomer(customerDTO));
			
	}

	//Updating an existing customer
	@PutMapping(value = "/{phoneNumber}", consumes = "application/json")
	public CustomerDTO updateCustomer(@PathVariable("phoneNumber") 
	 								  @Pattern(regexp = "[0-9]{10}",message="{customer.phoneNo.invalid}") 
	 								  String phoneNumber,@RequestBody CustomerDTO customerDTO) throws NoSuchCustomerException
	{
		return customerService.updateCustomer(Long.parseLong(phoneNumber), customerDTO);
	}
	

	// Deleting a customer
	@DeleteMapping(value="/{phoneNumber}",produces="text/html")
	public String deleteCustomer(@PathVariable("phoneNumber") 
			 					 @Pattern(regexp = "[0-9]{10}",message="{customer.phoneNo.invalid}") 
			 					 String phoneNumber) throws NoSuchCustomerException {

		return customerService.deleteCustomer(Long.parseLong(phoneNumber));

	}
}
