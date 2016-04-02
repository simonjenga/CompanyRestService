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
@Rollback
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
	
	private Company company, savedCompany;
	private Owner ownerOne, ownerTwo;
	
	@Before
	public void setUp() throws Exception {
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
}