package org.gs.medapp.dao.impl;

import org.gs.medapp.dao.UserDoctorDetailsDAO;
import org.gs.medapp.model.UserDoctorDetail;
import org.gs.medapp.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class UserDoctorDetailsDAOImpl implements UserDoctorDetailsDAO
{
	// get hibernate session factory
	SessionFactory sessionFactory = HibernateUtils.getSessionfactory();
	
	@Override
	public Integer addDoctorDetails(UserDoctorDetail userDoctorDetail) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		session.save(userDoctorDetail);
		
		session.getTransaction().commit();
		return userDoctorDetail.getId();
	}
}
