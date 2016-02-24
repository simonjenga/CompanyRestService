package com.company.restservice.service;

import java.util.List;

import com.company.restservice.model.Company;

public interface CompanyService {

	Company saveCompany(Company company);
	
	Company updateCompany(Company company, Company companyToUpdate);
	
	List<Company> getCompanyList();
	
	Company getCompanyById(Long companyId);
}
