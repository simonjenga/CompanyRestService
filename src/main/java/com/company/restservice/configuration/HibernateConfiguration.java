package com.company.restservice.configuration;
 
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.company.restservice.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
 
    @Autowired
    private Environment environment;
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.company.restservice.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
     
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
     
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.query.substitutions", environment.getRequiredProperty("hibernate.query.substitutions"));
        
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));        
        properties.put("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));  
        
        properties.put("hibernate.c3p0.minPoolSize", environment.getRequiredProperty("hibernate.c3p0.minPoolSize"));
        properties.put("hibernate.c3p0.maxPoolSize", environment.getRequiredProperty("hibernate.c3p0.maxPoolSize"));
        properties.put("hibernate.c3p0.max_statement", environment.getRequiredProperty("hibernate.c3p0.max_statement"));        
        properties.put("hibernate.c3p0.testConnectionOnCheckout", environment.getRequiredProperty("hibernate.c3p0.testConnectionOnCheckout"));
        
        properties.put("hibernate.cache.provider_class", environment.getRequiredProperty("hibernate.cache.provider_class"));        
        //properties.put("hibernate.current_session_context_class", environment.getRequiredProperty("hibernate.current_session_context_class"));
        
        //properties.put("hibernate.connection.driver_class", environment.getRequiredProperty("hibernate.connection.driver_class"));
        //properties.put("hibernate.connection.url", environment.getRequiredProperty("hibernate.connection.url"));
        //properties.put("hibernate.connection.username", environment.getRequiredProperty("hibernate.connection.username"));
        //properties.put("hibernate.connection.password", environment.getRequiredProperty("hibernate.connection.password"));
        return properties;        
    }
     
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}