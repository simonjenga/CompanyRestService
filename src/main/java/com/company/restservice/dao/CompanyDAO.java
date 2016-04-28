package com.company.restservice.dao;

import java.util.List;

import com.company.restservice.model.Company;

/**
 * This class is the Data Access Object (DAO) for the {@code Company} entity
 * in the domain model.
 *  
 * @author Simon Njenga
 * @version 0.1
 */
public interface CompanyDAO {

    /**
     * Saves a <code>Company</code> object to the database.
     * 
     * @param <code>Company</code>company, company object to save.
     * @return the <code>Company</code> object that has been saved in the database
     */
    Company saveCompany(Company company);

    /**
     * Updates a <code>Company</code> object in the database.
     * 
     * @param <code>Company</code>company, company object that needs to be updated.
     * @return the <code>Company</code> object that has been updated from the database
     */
    Company updateCompany(Company company);

    /**
     * Retrieves a <code>Company</code> object from the database.
     * 
     * @param <code>Long</code>companyId, companyId of the <code>Company</code> object to retrieve.
     * @return a <code>Company</code> object
     */
    Company getCompanyById(Long companyId);

    /**
     * Deletes a <code>Company</code> object from the database.
     * 
     * @param <code>Long</code>companyId, companyId of the <code>Company</code> object to delete.
     */
    void deleteCompany(Long companyId);

    /**
     * Retrieves a list of all <code>Company</code> objects from the database.
     * 
     * @return a <code>List</code> of <code>Company</code> objects
     */
    List<Company> getCompaniesList();
}
