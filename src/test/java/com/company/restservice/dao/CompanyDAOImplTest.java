package com.company.restservice.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.company.restservice.configuration.HibernateConfiguration;
import com.company.restservice.model.Company;
import com.company.restservice.model.Owner;

/**
 * Test case for {@link CompanyDAO}.
 * 
 * @author Simon Njenga
 * @since 0.1
 */
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
@ComponentScan(basePackages = "com.company.restservice")
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class CompanyDAOImplTest {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	private Company company;
	private Owner ownerOne, ownerTwo;
	
	@Before
	public void setup() {
		company = new Company();
		ownerOne = new Owner();
		ownerTwo = new Owner();
				
		company.setName("Elizabeth");
		company.setAddress("Oxford");
		company.setCity("London");
		company.setCountry("England");
		company.setEmail("elizabeth@kingsway.edu");
		company.setPhoneNumber("+44-756-093-2218");
		
		ownerOne.setName("Owner One");
		ownerTwo.setName("Owner Two");
		
		List<Owner> owners = new ArrayList<Owner>(2);
		owners.add(ownerOne);
		owners.add(ownerTwo);		
		
		company.setOwner(owners);
		ownerOne.setCompany(company);
		ownerTwo.setCompany(company);
	}
	
	/**
     * JUnit tests documentation to be implemented later!.
     *  
     * @throws Exception If some problem inside
     */
	@Test
    @Rollback(true)
	public void testAddCompany() {
		// save the company to database
		Company savedCompany = companyDAO.saveCompany(company);
		
		Assert.assertTrue(savedCompany.getId() != null);
		Assert.assertEquals(company.getName(), savedCompany.getName());
		Assert.assertTrue(savedCompany.getOwner().size() == 2);
	}
	
	/**
     * JUnit tests documentation to be implemented later!.
     *  
     * @throws Exception If some problem inside
     */
	@Test
    @Rollback(true)
	public void testUpdateCompany() {
		// save the company to database
		Company savedCompany = companyDAO.saveCompany(company);
		savedCompany.setName("Felicity");
		savedCompany.setAddress("Salisbury");
		savedCompany.setCity("Wiltshire");
		savedCompany.setCountry("England");
		savedCompany.setEmail("felicity@salisbury.com");
		savedCompany.setPhoneNumber("+44-753-110-9524");
		
		Company updatedCompany = companyDAO.updateCompany(savedCompany);
		
		Assert.assertTrue(savedCompany.getId() != null);
		Assert.assertNotEquals(updatedCompany.getName(), "Elizabeth");
		Assert.assertEquals(updatedCompany.getName(), "Felicity");
		Assert.assertTrue(updatedCompany.getEmail().equals("felicity@salisbury.com"));
	}
	
	/**
     * JUnit tests documentation to be implemented later!.
     *  
     * @throws Exception If some problem inside
     */
	@Test
    @Rollback(true)
	public void testCompaniesList() {
		// save the company to database
		Company savedCompany = companyDAO.saveCompany(company);
		
		List<Company> companiesList = companyDAO.getCompaniesList();
		
		Assert.assertTrue(savedCompany.getId() != null && companiesList.size() != 0);
	}
}

