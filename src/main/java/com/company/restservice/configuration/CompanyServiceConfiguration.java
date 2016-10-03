package com.company.restservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * This class extends {@code WebMvcConfigurerAdapter}, which provides empty methods that can be overridden to
 * provide customize default configuration of Spring MVC. The @EnableWebMvc annotation enables Spring MVC
 * configuration to be done explicitly through Java code.
 * 
 * The @Configuration annotation indicates that the class can be used by the Spring IoC container as a source
 * of bean definitions. It helps to enable the Spring MVC @Controller programming model.
 *
 * The {@code DispatcherServlet} context defines this servlet's request-processing infrastructure. Please visit
 * here {@link #DispatcherServlet(WebApplicationContext)}, {@link #FrameworkServlet(WebApplicationContext)} for
 * more details.
 *
 * http://docs.spring.io/autorepo/docs/spring/3.2.x/spring-framework-reference/html/mvc.html
 *
 * @author Simon Njenga
 * @since 0.1
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.company.restservice" })
public class CompanyServiceConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Java configuration for 'welcome-file-list' found in web.xml
        registry.addViewController("/").setViewName("forward:/jsp/index.jsp");
    }
}