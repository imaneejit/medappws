package org.gs.medapp.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.gs.medapp.dao.UserLoginDAO;
import org.gs.medapp.model.UserLogin;
import org.gs.medapp.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class UserLoginDAOImpl implements UserLoginDAO
{
	// logger
	private final Logger _log = Logger.getLogger( UserLoginDAOImpl.class );
	
	// get hibernate session factory
	SessionFactory sessionFactory = HibernateUtils.getSessionfactory();

	@Override
	public List<UserLogin> list() 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		List<UserLogin> users = session.createQuery("FROM " + UserLogin.class.getName(), UserLogin.class).getResultList();
		
		session.getTransaction().commit();
		return users;
	}

	@Override
	public UserLogin get(Integer id) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		UserLogin user = session.get(UserLogin.class, id);
		
		session.getTransaction().commit();
		return user;
	}
	
	@Override
	public UserLogin get(String username) 
	{
		UserLogin user = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{
			session.getTransaction().begin();
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<UserLogin> criteriaQuery = criteriaBuilder.createQuery(UserLogin.class);
			Root<UserLogin> userLogin = criteriaQuery.from(UserLogin.class);
			
			criteriaQuery.select(userLogin);
			criteriaQuery.where(criteriaBuilder.equal(userLogin.get("username"), username));
			
			user = session.createQuery(criteriaQuery).getSingleResult();
		} 
		catch (NoResultException nre) 
		{
			_log.error("User not found!", nre);
		}
		finally
		{
			session.getTransaction().commit();
		}
		
		return user;
	}

	@Override
	public Integer create(UserLogin user) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		session.save(user);
		
		session.getTransaction().commit();
		return user.getId();
	}

	@Override
	public void update(UserLogin user) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		session.update(user);
		
		session.getTransaction().commit();
	}

	@Override
	public void delete(Integer id) 
	{
		// user to be deleted
		UserLogin user = get(id);
		
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		session.delete(user);
		
		session.getTransaction().commit();
	}
}
