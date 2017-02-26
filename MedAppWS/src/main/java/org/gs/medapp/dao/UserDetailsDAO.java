package org.gs.medapp.dao;

import java.util.List;

import org.gs.medapp.model.UserDetail;

public interface UserDetailsDAO
{
	// get all user details
	List<UserDetail> list();
	
	// get user details by id
	UserDetail get(Integer id);
	
	// create user details
	Integer create(UserDetail userDetail);
	
	// update user details
	void update(UserDetail userDetail);
	
	// delete user details
	void delete(Integer id);
}
