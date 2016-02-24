package com.company.restservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Returns the CurrentSession for direct reference from subclasses.
	 * 
	 * @return CurrentSession
	 */
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Returns the OpenSession for direct reference from subclasses.
	 * 
	 * @return OpenSession
	 */
	protected Session getOpenSession() {
		return sessionFactory.openSession();
	}

}
