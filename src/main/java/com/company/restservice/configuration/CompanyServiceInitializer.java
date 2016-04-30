package com.company.restservice.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.company.restservice.web.servlet.utils.CORSFilter;

/**
 * This class is a concrete implementation of the {@link AbstractAnnotationConfigDispatcherServletInitializer}
 * which in itself is an implementation of the {@link WebApplicationInitializer} interface.
 * 
 * This class helps simplifying Java-based Spring configuration as we don't need to manually create contexts but
 * just set appropriate configuration classes in {@link #getRootConfigClasses()} and {@link #getServletConfigClasses()} 
 * methods.
 *
 * http://www.kubrynski.com/2014/01/understanding-spring-web-initialization.html
 *
 * @author Simon Njenga
 * @since 0.1
 */
public class CompanyServiceInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { CompanyServiceConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/", "/restservice/*" };
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }
}