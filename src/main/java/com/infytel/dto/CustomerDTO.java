package com.infytel.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.infytel.entity.CustomerEntity;
import com.infytel.entity.FriendFamilyEntity;

public class CustomerDTO 
{
	@NotNull(message="{customer.phone.must}")
	private Long phoneNo;
	@NotBlank(message="{customer.name.must}")
	private String name;
	@NotNull(message="{customer.email.must}")
	@Email(message= "{customer.email.invalid}")
	private String email;
	@Min(value=18, message = "{customer.age.invalid}")
	@Max(value=60, message = "{customer.age.invalid}")
	private int age;
	private char gender;
	private List<FriendFamilyDTO> friendAndFamily;
	//Password should comprise of alphabets of both the cases and digits as well with a length of minimum 5
	@NotEmpty(message="{customer.password.must}")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$",message= "{customer.password.invalid}")
	private String password;
	private String address;
	@NotNull(message="{customer.plan.must}")
	private PlanDTO currentPlan;

	public PlanDTO getCurrentPlan() {
		
		return currentPlan;
	}

	public void setCurrentPlan(PlanDTO currentPlan) {
		this.currentPlan = currentPlan;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<FriendFamilyDTO> getFriendAndFamily() {
		return friendAndFamily;
	}

	public void setFriendAndFamily(List<FriendFamilyDTO> friendAndFamily) {
		this.friendAndFamily = friendAndFamily;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "CustomerDTO [phoneNo=" + phoneNo + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", friendAndFamily=" + friendAndFamily + ", password=" + password + ", address=" + address + "]";
	}
	// Converts DTO into Entity
	public CustomerEntity createEntity() 
	{
			CustomerEntity cust = new CustomerEntity();
			cust.setAge(this.getAge());
			cust.setGender(this.getGender());
			cust.setName(this.getName());
			cust.setEmail(this.getEmail());
			cust.setPhoneNo(this.getPhoneNo());
			cust.setAddress(this.getAddress());
			cust.setPassword(this.getPassword());
			List<FriendFamilyDTO> friendFamilyDTOs = this.getFriendAndFamily();
			List<FriendFamilyEntity> friendFamily = new ArrayList<>();
			if(friendFamilyDTOs!=null)
			{
				for(FriendFamilyDTO friendFamilyDTO : friendFamilyDTOs)
				{
					friendFamily.add(friendFamilyDTO.createEntity());
				}
			cust.setFriends(friendFamily);
			}
			cust.setPlan(this.getCurrentPlan().createEntity());
			return cust;
	}
	// Converts Entity into DTO
	public static CustomerDTO valueOf(CustomerEntity customer) 
	{
		CustomerDTO customerDTO= new CustomerDTO();
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setAge(customer.getAge());
		PlanDTO planDTO = PlanDTO.valueOf(customer.getPlan());
		customerDTO.setCurrentPlan(planDTO);
		List<FriendFamilyDTO> friendFamilyDTOs = FriendFamilyDTO.valueOf(customer.getFriends());
		customerDTO.setFriendAndFamily(friendFamilyDTOs);
		customerDTO.setGender(customer.getGender());
		customerDTO.setName(customer.getName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setPassword(customer.getPassword());
		customerDTO.setPhoneNo(customer.getPhoneNo());
		return customerDTO;
	}
		
	}


