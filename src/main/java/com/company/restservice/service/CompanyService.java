package com.company.restservice.service;

import java.util.List;

import com.company.restservice.model.Company;

/**
 * Service interface class.
 *  
 * @author Simon Njenga
 * @version 0.1
 */
public interface CompanyService {

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
     * @param <code>Company</code>company, company object that has the update details.
     * @param <code>Company</code>companyToUpdate, company object that needs to be updated.
     * @return the <code>Company</code> object that has been updated from the database
     */
    Company updateCompany(Company company, Company companyToUpdate);

    /**
     * Retrieves a list of all <code>Company</code> objects from the database.
     * 
     * @return a <code>List</code> of <code>Company</code> objects
     */
    List<Company> getCompanyList();

    /**
     * Retrieves a <code>Company</code> object from the database.
     * 
     * @param <code>Long</code>companyId, companyId of the <code>Company</code> object to retrieve.
     * @return a <code>Company</code> object
     */
    Company getCompanyById(Long companyId);
}
