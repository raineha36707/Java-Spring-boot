package com.infytel.dto;

import java.util.ArrayList;
import java.util.List;
import com.infytel.entity.FriendFamilyEntity;

public class FriendFamilyDTO {

	long phoneNo;
	long friendAndFamily;

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getFriendAndFamily() {
		return friendAndFamily;
	}

	public void setFriendAndFamily(long friendAndFamily) {
		this.friendAndFamily = friendAndFamily;
	}

	public FriendFamilyDTO(long phoneNo, long friendAndFamily) {
		this();
		this.phoneNo = phoneNo;
		this.friendAndFamily = friendAndFamily;
	}

	public FriendFamilyDTO() {
		super();
	}

	// Converts DTO into Entity
	public FriendFamilyEntity createEntity() {
		FriendFamilyEntity friend = new FriendFamilyEntity();
		friend.setFriendAndFamily(this.friendAndFamily);
		friend.setPhoneNo(this.getPhoneNo());
		return friend;
	}
	// Converts Entity into DTO
	public static List<FriendFamilyDTO> valueOf(List<FriendFamilyEntity> friendFamilyList) 
	{
		List<FriendFamilyDTO> friendFamilyDTOs = new ArrayList<>();
		FriendFamilyDTO friendFamilyDTO= new FriendFamilyDTO();
		for(FriendFamilyEntity friendFamily : friendFamilyList)
		{
			friendFamilyDTO.setFriendAndFamily(friendFamily.getFriendAndFamily());
			friendFamilyDTO.setPhoneNo(friendFamily.getPhoneNo());
			friendFamilyDTO.setFriendAndFamily(friendFamily.getId());
			friendFamilyDTOs.add(friendFamilyDTO);
		}
		return friendFamilyDTOs;
	}
	@Override
	public String toString() {
		return "FriendFamilyDTO [phoneNo=" + phoneNo + ", friendAndFamily=" + friendAndFamily + "]";
	}

}
