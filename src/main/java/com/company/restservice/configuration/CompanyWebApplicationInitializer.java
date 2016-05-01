package com.company.restservice.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;

import com.company.restservice.web.servlet.utils.YearCalculator;

/**
 * This class is an implementation of the {@link WebApplicationInitializer} interface.
 * 
 * This class helps simplifying Java-based Spring configuration in Servlet 3.0+ environments in order to configure
 * the {@link ServletContext} programmatically as opposed to (or possibly in conjunction with) the traditional
 * web.xml-based approach.
 *
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html
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