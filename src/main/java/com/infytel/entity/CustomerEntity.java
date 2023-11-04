package com.infytel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@Column(name = "phone_no", nullable = false)
	private Long phoneNo;
	@Column(nullable = false, length = 50)
	private String name;
	@Column(nullable = false, length = 50)
	private String email;
	@Column(nullable = false)
	private Integer age;
	@Column(nullable = false, length = 50)
	private String address;
	@Column(nullable = false, length = 50)
	private String password;
	@Column(nullable = false, length = 1)
	private char gender;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="plan_id")
	private PlanEntity plan;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn(name="phone_no")
	private List<FriendFamilyEntity> friends=new ArrayList<>();
	
	
	public PlanEntity getPlan() {
		return plan;
	}

	public void setPlan(PlanEntity plan) {
		this.plan = plan;
	}

	public List<FriendFamilyEntity> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendFamilyEntity> friends) {
		this.friends = friends;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CustomerEntity() {
		super();
	}
}
