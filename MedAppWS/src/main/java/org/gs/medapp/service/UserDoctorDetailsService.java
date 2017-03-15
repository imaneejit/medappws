package org.gs.medapp.service;

import org.gs.medapp.dao.UserDoctorDetailsDAO;
import org.gs.medapp.model.UserDoctorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDoctorDetailsService 
{
	@Autowired
	private UserDoctorDetailsDAO userDoctorDetailsDAO;
	
	// add doctor details
	public Integer addDoctorDetails( UserDoctorDetail userDoctorDetail )
	{
		return userDoctorDetailsDAO.addDoctorDetails(userDoctorDetail);
	}
}
