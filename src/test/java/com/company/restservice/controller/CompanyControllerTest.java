package com.company.restservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
//@org.junit.Ignore
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@EnableTransactionManagement(proxyTargetClass = true)
@TestExecutionListeners(value = DependencyInjectionTestExecutionListener.class, inheritListeners = true)
@ContextConfiguration(classes = { HibernateConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class CompanyControllerTest {

	@Autowired
	private CompanyDAO companyDAO;
	
	private Company company;
	private Owner ownerOne;
	
	private String baseURL;
    private RestTemplate template;
	
	@Before
	public void setup() {
		this.company = new Company();
		this.ownerOne = new Owner();
		
		this.baseURL = "http://localhost:8080/CompanyRestService/restservice";
        this.template = new RestTemplate();
				
        this.company.setName("Johnathan");
        this.company.setAddress("Chelsea");
        this.company.setCity("London");
        this.company.setCountry("England");
        this.company.setEmail("johnathan@chelsea.sports");
        this.company.setPhoneNumber("+44-867-023-9514");
		
        this.ownerOne.setName("Owner One");
		
		List<Owner> owners = new ArrayList<Owner>(1);
		owners.add(this.ownerOne);
		
		this.company.setOwner(owners);
		this.ownerOne.setCompany(this.company);
	}
    
    /**
     * This test is for HTTP GET request for the web service. 
     * 
     * @throws Exception If some problem inside
     */
	@Test
    @Rollback(true)
    public void testGetCompanyList() {
		// save the company to database
		Company savedCompany = companyDAO.saveCompany(company);
		
		Assert.assertTrue(savedCompany.getId() != null);
		
		ResponseEntity<String> response = this.template.getForEntity(this.baseURL + "/companies", String.class);
        
		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		System.out.println(response.getBody().toString());
    }
	
	/**
     * This test is for HTTP GET request for the web service. 
     * 
     * @throws Exception If some problem inside
     */
	@Test
    @Rollback(true)
    public void testGetCompany() {
		// save the company to database
		Company savedCompany = companyDAO.saveCompany(company);
		
		Assert.assertTrue(savedCompany.getId() != null);
		
		ResponseEntity<String> response = this.template.getForEntity(this.baseURL + "/company/" + savedCompany.getId(), String.class);
        
		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		System.out.println(response.getBody().toString());
    }
}
