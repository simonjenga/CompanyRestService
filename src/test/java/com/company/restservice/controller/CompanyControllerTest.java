package com.company.restservice.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.company.restservice.configuration.HibernateConfiguration;
import com.company.restservice.dao.CompanyDAO;
import com.company.restservice.model.Company;
import com.company.restservice.model.Owner;

/**
 * Test case for {@link CompanyController}.
 * 
 * This test is disabled by default as it requires the web service to be deployed and be running
 * on the Server before this test can be executed.
 * 
 * Otherwise, it throws the following error message: 
 * I/O error on GET request for "http://localhost:8080/CompanyRestService/restservice/companies":Connection refused:
 * 
 * @author Simon Njenga
 * @since 0.1
 */
@Ignore
@Transactional
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
@ComponentScan(basePackages = "com.company.restservice")
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class CompanyControllerTest {

	public static final String REST_SERVICE_URI = "http://localhost:8080/CompanyRestService/restservice";
	
	@Autowired
	private CompanyDAO companyDAO;
	
	private Company company;
	private Owner ownerOne;
	
	@Before
	public void setup() {
		company = new Company();
		ownerOne = new Owner();
				
		company.setName("Johnathan");
		company.setAddress("Chelsea");
		company.setCity("London");
		company.setCountry("England");
		company.setEmail("johnathan@chelsea.sports");
		company.setPhoneNumber("+44-867-023-9514");
		
		ownerOne.setName("Owner One");
		
		List<Owner> owners = new ArrayList<Owner>(2);
		owners.add(ownerOne);
		
		company.setOwner(owners);
		ownerOne.setCompany(company);
	}
    
    /**
     * This test is for HTTP GET request for the web service. 
     * 
     * @throws Exception If some problem inside
     */
	@Test
    @SuppressWarnings("unchecked")
    public void testGetCompanyList() {
		// save the company to database
		companyDAO.saveCompany(company);
		
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> companiesMap = restTemplate.getForObject(REST_SERVICE_URI + "/companies", List.class);
        
        Assert.assertTrue(companiesMap != null && !companiesMap.isEmpty() && companiesMap.size() > 0);
    }
}
