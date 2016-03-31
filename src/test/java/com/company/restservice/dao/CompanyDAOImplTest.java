package com.company.restservice.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.restservice.model.Company;
import com.company.restservice.model.Owner;

/**
 * Test case for {@link CompanyDAO}.
 * 
 * @author Simon Njenga
 * @since 0.1
 */
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = SpringJUnit4ClassRunner.class)
@EnableTransactionManagement(proxyTargetClass = true)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners(value = DependencyInjectionTestExecutionListener.class, inheritListeners = true)
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class CompanyDAOImplTest {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	private Company company;
	private Owner ownerOne, ownerTwo;
	
	@Before
	public void setup() {
		this.company = new Company();
		this.ownerOne = new Owner();
		this.ownerTwo = new Owner();
				
		this.company.setName("Elizabeth");
		this.company.setAddress("Oxford");
		this.company.setCity("London");
		this.company.setCountry("England");
		this.company.setEmail("elizabeth@kingsway.edu");
		this.company.setPhoneNumber("+44-756-093-2218");
		
		this.ownerOne.setName("Owner One");
		this.ownerTwo.setName("Owner Two");
		
		List<Owner> owners = new ArrayList<Owner>(2);
		owners.add(this.ownerOne);
		owners.add(this.ownerTwo);		
		
		this.company.setOwner(owners);
		this.ownerOne.setCompany(this.company);
		this.ownerTwo.setCompany(this.company);
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
		Company savedCompany = this.companyDAO.saveCompany(this.company);
		
		Assert.assertTrue(savedCompany.getId() != null);
		Assert.assertEquals(this.company.getName(), savedCompany.getName());
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
		Company savedCompany = this.companyDAO.saveCompany(this.company);
		savedCompany.setName("Felicity");
		savedCompany.setAddress("Salisbury");
		savedCompany.setCity("Wiltshire");
		savedCompany.setCountry("England");
		savedCompany.setEmail("felicity@salisbury.com");
		savedCompany.setPhoneNumber("+44-753-110-9524");
		
		Company updatedCompany = this.companyDAO.updateCompany(savedCompany);
		
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
		Company savedCompany = this.companyDAO.saveCompany(this.company);
		
		List<Company> companiesList = this.companyDAO.getCompaniesList();
		
		Assert.assertTrue(savedCompany.getId() != null && companiesList.size() != 0);
	}
	
	/**
     * JUnit tests documentation to be implemented later!.
     *  
     * @throws Exception If some problem inside
     */
	@Test
    @Rollback(true)
	public void testUpdateCompanyWithNewOwner() {
		// save the company to database
		Company savedCompany = this.companyDAO.saveCompany(this.company);
		savedCompany.setName("Felicity");
		savedCompany.setAddress("Salisbury");
		savedCompany.setCity("Wiltshire");
		savedCompany.setCountry("England");
		savedCompany.setEmail("felicity@salisbury.com");
		savedCompany.setPhoneNumber("+44-753-110-9524");
		
		Owner ownerThree = new Owner();
		ownerThree.setName("Owner Three");
		
		List<Owner> owners = new ArrayList<Owner>(1);
		owners.add(ownerThree);	
		
		savedCompany.setOwner(owners);
		ownerThree.setCompany(savedCompany);
		
		this.companyDAO.updateCompany(savedCompany);
		
		Company updatedCom = this.companyDAO.getCompanyById(savedCompany.getId());
		
		Assert.assertTrue(!savedCompany.getId().equals(null));
		Assert.assertNotEquals(updatedCom.getName(), "Elizabeth");
		Assert.assertEquals(updatedCom.getName(), "Felicity");
		Assert.assertTrue(updatedCom.getEmail().equals("felicity@salisbury.com"));
		
		List<Owner> allOwners = updatedCom.getOwner();
		
		Assert.assertTrue(allOwners.size() == 3);
		Assert.assertTrue(!allOwners.get(2).equals(null));
		Assert.assertTrue(allOwners.get(2).getName().equals("Owner Three"));
		Assert.assertTrue(allOwners.get(2).getCompany().getAddress().equals("Salisbury"));
		Assert.assertTrue(allOwners.get(2).getCompany().getPhoneNumber().equals("+44-753-110-9524"));		
	}
}