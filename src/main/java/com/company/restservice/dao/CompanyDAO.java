package com.company.restservice.dao;

import java.util.List;

import com.company.restservice.model.Company;

public interface CompanyDAO {

	Company saveCompany(Company company);
	
	Company updateCompany(Company company);
	
	Company getCompanyById(Long companyId);
	
	List<Company> getCompaniesList();
}
