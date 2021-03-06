package com.company.restservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.company.restservice.dao.CompanyDAO;
import com.company.restservice.model.Company;
import com.company.restservice.model.Owner;

/**
 * Test case for {@link CompanyController}.
 * 
 * @author Simon Njenga
 * @since 0.1
 */
@Rollback
@WebAppConfiguration
@EnableTransactionManagement
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners(value = DependencyInjectionTestExecutionListener.class)
public class CompanyControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CompanyDAO companyDAO;

    private MockMvc mockMvc;

    private Company company, savedCompany;
    private Owner ownerOne;

    @Before
    public void setUp() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();

        this.company = new Company();
        this.ownerOne = new Owner();

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

        // save the company to database
        this.savedCompany = this.companyDAO.saveCompany(this.company);
    }

    /**
     * This test should testAddCompany.
     *  
     * @throws Exception If some problem inside
     */
    @Test
    @Rollback
    public void testAddCompany() throws Exception {
        Assert.assertTrue(this.savedCompany.getId() != null);

        String theCompany = "{ \"name\": \"Felicity\", \"address\": \"Oxford\", \"city\" : \"London\", \"country\" : \"England\"," +
            " \"email\" : \"felicity@oxford.com\", \"phoneNumber\" : \"+44-234-090\", \"owner\": [ { \"name\" : \"Blueish\" } ] }";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/company")
            .contentType(MediaType.APPLICATION_JSON_UTF8).content(theCompany))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * This test should testUpdateCompany.
     *
     * @throws Exception If some problem inside
     */
    @Test
    @Rollback
    public void testUpdateCompany() throws Exception {
        Assert.assertTrue(this.savedCompany.getId() != null);

        String theCompany = "{ \"name\": \"Elizabeth\", \"address\": \"Salisbury\", \"city\" : \"London\", \"country\" : \"England\"," +
            " \"email\" : \"elizabeth@salisbury.com\", \"phoneNumber\" : \"+44-786-086\"," +
            " \"owner\": [ { \"id\" : \"1\", \"name\" : \"Reddish\" }, { \"id\" : \"2\", \"name\" : \"Greenish\" } ] }";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/company/{companyId}", this.savedCompany.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8).content(theCompany))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * This test should testCompanyOwner.
     * 
     * @throws Exception If some problem inside
     */
    @Test
    @Rollback
    public void testCompanyOwner() throws Exception {
        Assert.assertTrue(this.savedCompany.getId() != null);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/companyowner/{companyId}", this.savedCompany.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"name\": \"Brownish\" }"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * This test should testGetCompanyList.
     *
     * @throws Exception If some problem inside
     */
    @Test
    @Rollback
    public void testGetCompanyList() throws Exception {
        Assert.assertTrue(this.savedCompany.getId() != null);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/companies")
            .contentType(MediaType.APPLICATION_JSON_UTF8).content("{ }"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * This test should testGetCompany.
     *
     * @throws Exception If some problem inside
     */
    @Test
    @Rollback
    public void testGetCompany() throws Exception {
        Assert.assertTrue(this.savedCompany.getId() != null);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/company/{companyId}", this.savedCompany.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8).content("{ }"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @After
    public void tearDown() throws Exception {
        // Intentionally empty!
    }
}
