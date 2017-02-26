package org.gs.medapp.dao;

import java.util.List;

import org.gs.medapp.model.UserLogin;

public interface UserLoginDAO 
{
	// get all users
	List<UserLogin> list();
	
	// get user by id
	UserLogin get(Integer id);
	
	// get user by username
	UserLogin get(String username);
	
	// create user
	Integer create(UserLogin user);
	
	// update user
	void update(UserLogin user);
	
	// delete user
	void delete(Integer id);
}
