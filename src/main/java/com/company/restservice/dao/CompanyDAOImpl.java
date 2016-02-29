package com.company.restservice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.restservice.model.Company;

/**
 * Documentation for this class will be done later!
 * 
 * @author Simon Njenga
 * @since 0.1
 */
@Transactional
@Repository(value = "companyDAO")
public class CompanyDAOImpl extends GenericDAO implements CompanyDAO {

	@Override
	public Company saveCompany(Company company) {
		final Session session = this.getOpenSession();
		Transaction tx = session.beginTransaction();
		session.save(company);
		tx.commit();		
		session.flush();
		session.close();
		return company;
	}
	
	@Override
	public Company updateCompany(Company company) {
		final Session session = this.getOpenSession();
		Transaction tx = session.beginTransaction();
		session.clear();
		session.update(company);
		session.merge(company);
		tx.commit();		
		session.flush();
		session.close();
		return company;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompaniesList() {
		final Session session = this.getOpenSession();
		Transaction tx = session.beginTransaction();
		List<Company> companies = session.createQuery("from Company").list();
		tx.commit();
		session.flush();
		session.close();
		return companies;
	}

	@Override
	public Company getCompanyById(Long companyId) {
		final Session session = this.getOpenSession();
		Transaction tx = session.beginTransaction();
		Company company = (Company) getCurrentSession().get(Company.class, companyId);
		tx.commit();
		session.flush();
		session.close();
		return company;
	}
}
