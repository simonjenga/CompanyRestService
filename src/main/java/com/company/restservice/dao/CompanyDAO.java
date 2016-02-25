package com.company.restservice.dao;

import java.util.List;

import com.company.restservice.model.Company;

/**
 * Documentation for this class will be done later!
 * 
 * @author Simon Njenga
 * @since 0.1
 */
public interface CompanyDAO {

	Company saveCompany(Company company);
	
	Company updateCompany(Company company);
	
	Company getCompanyById(Long companyId);
	
	List<Company> getCompaniesList();
}
