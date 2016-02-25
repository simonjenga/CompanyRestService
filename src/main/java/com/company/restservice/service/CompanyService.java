package com.company.restservice.service;

import java.util.List;

import com.company.restservice.model.Company;

/**
 * Documentation for this class will be done later!
 * 
 * @author Simon Njenga
 * @since 0.1
 */
public interface CompanyService {

	Company saveCompany(Company company);
	
	Company updateCompany(Company company, Company companyToUpdate);
	
	List<Company> getCompanyList();
	
	Company getCompanyById(Long companyId);
}
