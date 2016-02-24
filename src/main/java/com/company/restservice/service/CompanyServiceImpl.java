package com.company.restservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.restservice.dao.CompanyDAO;
import com.company.restservice.model.Company;
import com.company.restservice.model.Owner;

@Service(value = "companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	@Override
	@Transactional(readOnly = false)
	public Company saveCompany(Company company) {
		return companyDAO.saveCompany(company);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Company updateCompany(Company company, Company companyToUpdate) {
		if (company == null) {
			return companyDAO.updateCompany(companyToUpdate);
		} else {
			companyToUpdate.setId(companyToUpdate.getId());
			companyToUpdate.setName(company.getName());
			companyToUpdate.setCountry(company.getCountry());
			companyToUpdate.setAddress(company.getAddress());
			companyToUpdate.setCity(company.getCity());
			companyToUpdate.setEmail(company.getEmail());
			companyToUpdate.setPhoneNumber(company.getPhoneNumber());
			companyToUpdate.setOwner(new ArrayList<Owner>());
			companyToUpdate.setOwner(company.getOwner());
			return companyDAO.updateCompany(companyToUpdate);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Company getCompanyById(Long companyId) {
		return companyDAO.getCompanyById(companyId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Company> getCompanyList() {
		return companyDAO.getCompaniesList();
	}
}
