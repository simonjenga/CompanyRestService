package com.company.restservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This abstract class is a generic Data Access Object (DAO) which defines the basic common
 * methods for hibernate's <code>SessionFactory</code> entities in the domain model.
 *
 * @author Simon Njenga
 * @since 0.1
 */
public abstract class GenericDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns the CurrentSession for direct reference from subclasses.
     *
     * @return The current <code>Session</code> in hibernate's <code>SessionFactory</code>
     */
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Returns the OpenSession for direct reference from subclasses.
     *
     * @return The open <code>Session</code> in hibernate's <code>SessionFactory</code>
     */
    protected Session getOpenSession() {
        return sessionFactory.openSession();
    }
}
