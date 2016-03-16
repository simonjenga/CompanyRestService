package com.company.restservice.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;

import com.company.restservice.web.servlet.utils.YearCalculator;

/**
 * Documentation for this class will be done later!
 * 
 * @author Simon Njenga
 * @since 0.1
 */
public class CompanyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		ServletRegistration.Dynamic registration = container.addServlet("yearCalc", new YearCalculator());
		registration.setLoadOnStartup(1);
		registration.addMapping("/theYear");
	}
}

// http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
// http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html