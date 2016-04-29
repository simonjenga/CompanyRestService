package com.company.restservice.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.restservice.model.Company;

/**
 * Data Access Object (DAO) implementation class for {@link CompanyDAO}
 *  
 * @author Simon Njenga
 * @version 0.1
 */
@Transactional
@Repository(value = "companyDAO")
public class CompanyDAOImpl extends GenericDAO implements CompanyDAO {

    @Override
    public Company saveCompany(Company company) {
        final Session session = this.getCurrentSession();
        session.save(company);
        session.flush();
        return company;
    }

    @Override
    public Company updateCompany(Company company) {
        final Session session = this.getCurrentSession();
        session.update(company);
        session.flush();
        return company;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Company> getCompaniesList() {
        final Session session = this.getCurrentSession();
        List<Company> companies = session.createQuery("from Company").list();
        session.flush();
        return companies;
    }

    @Override
    public Company getCompanyById(Long companyId) {
        final Session session = this.getCurrentSession();
        Company company = (Company) getCurrentSession().get(Company.class, companyId);
        session.flush();
        return company;
    }

    @Override
    public void deleteCompany(Long companyId) {
        final Session session = this.getCurrentSession();
        session.delete(this.getCompanyById(companyId));
        session.flush();
    }
}
