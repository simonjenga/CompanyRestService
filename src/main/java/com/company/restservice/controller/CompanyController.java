package com.company.restservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.restservice.model.Company;
import com.company.restservice.model.Owner;
import com.company.restservice.service.CompanyService;

/**
 * This is the company controller class that is used to process HTTP requests and responses
 * to the company RESTful service.
 *
 * @author Simon Njenga
 * @since 0.1
 */
@RestController
public class CompanyController {

	@Resource
	private CompanyService companyService;
	
	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public ResponseEntity<Company> addCompany(@RequestBody Company company) {
		int size = company.getOwner().size();
		List<Owner> owners = new ArrayList<Owner>(size);        
        for (int i = 0; i < size; i++) {
        	Owner owner =  new Owner();
        	owner.setCompany(company);
        	owner.setName(company.getOwner().get(i).getName());
        	owners.add(owner);
		}
		
		company.setOwner(owners);
		Company newCompany = companyService.saveCompany(company);
		
		return new ResponseEntity<Company>(newCompany, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/company/{companyId}", method = RequestMethod.PUT)
	public ResponseEntity<Company> updateCompany(@PathVariable("companyId") Long companyId, @RequestBody Company company) {
		Company companyToUpdate = companyService.getCompanyById(companyId);
        if (companyToUpdate == null) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
        
        int size = company.getOwner().size();
        List<Owner> owners = new ArrayList<Owner>(size);      
        for (int i = 0; i < size; i++) {
        	Owner owner =  new Owner();
        	owner.setId(company.getOwner().get(i).getId());
        	owner.setCompany(companyToUpdate);
        	owner.setName(company.getOwner().get(i).getName());
        	owners.add(owner);
		}
		
		company.setOwner(owners);		
		Company updatedCompany = companyService.updateCompany(company, companyToUpdate);		
			
		return new ResponseEntity<Company>(updatedCompany, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/companyowner/{companyId}", method = RequestMethod.PUT)
	public ResponseEntity<Company> companyOwner(@PathVariable("companyId") Long companyId, @RequestBody Owner owner) {
		Company companyToUpdate = companyService.getCompanyById(companyId);
        if (companyToUpdate == null) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
        
        List<Owner> owners = new ArrayList<Owner>(1);
        Owner newOwner =  new Owner();
        newOwner.setCompany(companyToUpdate);
        newOwner.setName(owner.getName());
        owners.add(newOwner);
		
        companyToUpdate.setOwner(owners);		
		Company updatedCompany = companyService.updateCompany(null, companyToUpdate);		
			
		return new ResponseEntity<Company>(updatedCompany, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public ResponseEntity<List<Company>> getCompanyList() {
		List<Company> companies = companyService.getCompanyList();
        if(companies == null || companies.isEmpty()) {
            return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/company/{companyId}", method = RequestMethod.GET)
	public ResponseEntity<Company> getCompany(@PathVariable("companyId") Long companyId) {
		Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Company>(company, HttpStatus.OK);
	}	
}
