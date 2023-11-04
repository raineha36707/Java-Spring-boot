package com.infytel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.infytel.dto.CustomerDTO;
import com.infytel.entity.CustomerEntity;
import com.infytel.entity.PlanEntity;
import com.infytel.exceptions.NoSuchCustomerException;
import com.infytel.exceptions.NoSuchPlanException;
import com.infytel.repository.CustomerRepository;
import com.infytel.repository.PlanRepository;
import com.infytel.util.InfyTelConstants;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class CustomerService 
{
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired 
	private PlanRepository planRepository;
	@Autowired
	private Environment environment;
	
	public  List<CustomerDTO> fetchCustomer()
	{
		List<CustomerEntity> customerEntities =  customerRepository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for(CustomerEntity customer : customerEntities)
		{
			customerDTOs.add(CustomerDTO.valueOf(customer));
		}
		return customerDTOs.stream().map(c->{c.setPassword("*");return c;}).collect(Collectors.toList());
	}
	
	public String createCustomer(CustomerDTO customerDTO)throws NoSuchPlanException
	{
		Optional<PlanEntity> plan = planRepository.findById(customerDTO.getCurrentPlan().getPlanId());
		if(plan.isPresent())
		{
			customerRepository.saveAndFlush(customerDTO.createEntity());   
		}
		else
		{
			throw new NoSuchPlanException(environment.getProperty(InfyTelConstants.PLAN_NOT_FOUND.toString()));
		}
		return environment.getProperty(InfyTelConstants.CUSTOMER_CREATE_SUCCESS.toString());
	}
	
	public CustomerDTO updateCustomer(long phoneNumber, CustomerDTO customerDTO) throws NoSuchCustomerException
	{
		CustomerEntity customer = customerRepository.findByPhoneNo(phoneNumber);
		if(customer==null)
		{
			throw new NoSuchCustomerException(environment.getProperty(InfyTelConstants.CUSTOMER_NOT_FOUND.toString()));
		}
		if(customerDTO.getName()!=null)
			customer.setName(customerDTO.getName());
		if(customerDTO.getAddress()!=null)
			customer.setAddress(customerDTO.getAddress());
		if(customerDTO.getEmail()!=null)
			customer.setEmail(customerDTO.getEmail());
		CustomerEntity customerReceived = customerRepository.saveAndFlush(customer);
		customerReceived.setPassword("*");
		return CustomerDTO.valueOf(customerReceived);
	}
	
	public String deleteCustomer(@PathVariable("phoneNumber") long phoneNumber) throws NoSuchCustomerException 
	{
		CustomerEntity customer = customerRepository.findByPhoneNo(phoneNumber);
		if(customer==null)
			throw new NoSuchCustomerException(environment.getProperty(InfyTelConstants.CUSTOMER_NOT_FOUND.toString()));
		customerRepository.delete(customer);
		return environment.getProperty(InfyTelConstants.CUSTOMER_DELETE_SUCCESS.toString());
	}
}
