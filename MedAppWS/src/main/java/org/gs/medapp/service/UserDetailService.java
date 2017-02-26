package org.gs.medapp.service;

import java.util.Date;
import java.util.List;

import org.gs.medapp.dao.UserDetailsDAO;
import org.gs.medapp.enums.UserStatus;
import org.gs.medapp.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService 
{
	@Autowired
	private UserDetailsDAO userDetailsDAO;
	
	// get all user detail record
	public List<UserDetail> getAllUserDetails()
	{
		return userDetailsDAO.list();
	}
	
	// get specific user details record via id
	public UserDetail getUserDetail( Integer id )
	{
		return userDetailsDAO.get(id);
	}
	
	// create user detail record
	public Integer createUserDetail( UserDetail userDetail )
	{
		userDetail.setCreatedDate(new Date());
		userDetail.setUpdatedDate(new Date());
		userDetail.setCreatedBy("TO DO");
		userDetail.setUpdatedBy("TO DO");
		userDetail.setUpdatedProgram("UserDetailsService.createUserDetail");
		userDetail.setStatus(UserStatus.ACTIVE.getNum());
		Integer userDetailId = userDetailsDAO.create(userDetail);
		
		return userDetailId;
	}
	
	// update user detail record
	public void updateUserDetail( UserDetail userDetail )
	{
		userDetail.setUpdatedDate(new Date());
		userDetail.setUpdatedBy("TO DO");
		userDetail.setUpdatedProgram("UserDetailService.updateUserDetail");
		
		userDetailsDAO.update(userDetail);
	}
	
	// physical delete user detail record
	public void deleteUserDetail( Integer id )
	{
		userDetailsDAO.delete(id);
	}
}
