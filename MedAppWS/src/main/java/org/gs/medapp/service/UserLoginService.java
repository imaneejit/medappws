package org.gs.medapp.service;

import java.util.Date;
import java.util.List;

import org.gs.medapp.dao.UserLoginDAO;
import org.gs.medapp.enums.UserStatus;
import org.gs.medapp.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService 
{
	@Autowired
	private UserLoginDAO userLoginDAO;
	
	// get all users
	public List<UserLogin> getAllUsers()
	{
		return userLoginDAO.list();
	}
	
	// get specific user using id
	public UserLogin getUserById( Integer id )
	{
		return userLoginDAO.get(id);
	}
	
	// get specific user using username
	public UserLogin getUserByUsername( String username )
	{
		return userLoginDAO.get(username);
	}
	
	// create user
	public Integer createUser( UserLogin user )
	{
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		user.setCreatedBy("TO DO");
		user.setUpdatedBy("TO DO");
		user.setUpdatedProgram("UserDetailsService.createUser");
		
		user.setStatus(UserStatus.ACTIVE.getNum());
		
		return userLoginDAO.create(user);
	}
	
	// update user
	public void updateUser( UserLogin user )
	{
		user.setUpdatedDate(new Date());
		user.setUpdatedBy("TO DO");
		user.setUpdatedProgram("UserDetailsService.updateUser");
		
		userLoginDAO.update(user);
	}
	
	// delete user
	public void deleteUser( Integer id )
	{
		userLoginDAO.delete(id);
	}
}
