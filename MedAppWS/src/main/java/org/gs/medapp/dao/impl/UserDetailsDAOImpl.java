package org.gs.medapp.dao.impl;

import java.util.List;

import org.gs.medapp.dao.UserDetailsDAO;
import org.gs.medapp.enums.UserStatus;
import org.gs.medapp.model.UserDetail;
import org.gs.medapp.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsDAOImpl implements UserDetailsDAO
{
	// get hibernate session factory
	SessionFactory sessionFactory = HibernateUtils.getSessionfactory();
	
	@Override
	public List<UserDetail> list() 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		List<UserDetail> list = session.createQuery("FROM " + UserDetail.class.getName(), UserDetail.class).getResultList();
		
		session.getTransaction().commit();
		return list;
	}

	@Override
	public UserDetail get(Integer id) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		UserDetail userDetail = session.get(UserDetail.class, id);
		
		session.getTransaction().commit();
		return userDetail;
	}

	@Override
	public Integer create(UserDetail userDetail) 
	{
		userDetail.setStatus(UserStatus.ACTIVE.getNum());
		
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		session.save(userDetail);
		
		session.getTransaction().commit();
		return userDetail.getId();
	}

	@Override
	public void update( UserDetail userDetail ) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		session.update(userDetail);
		
		session.getTransaction().commit();
	}

	@Override
	public void delete( Integer id ) 
	{
		// get the user detail object to be deleted
		UserDetail userDetail = get(id);
		
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		session.delete(userDetail);
		
		session.getTransaction().commit();
	}

}
