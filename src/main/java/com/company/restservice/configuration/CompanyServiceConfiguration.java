package com.company.restservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * {@code DispatcherServlet} Context: defines this servlet's request-processing infrastructure @Configuration annotation.
 * This annotation indicates that the class can be used by the Spring IoC container as a source of bean definitions.
 * Enables the Spring MVC @Controller programming model.
 *
 * {@link #DispatcherServlet(WebApplicationContext)} Javadoc for more details.
 * {@link #FrameworkServlet(WebApplicationContext)} Javadoc for more details.
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
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
}