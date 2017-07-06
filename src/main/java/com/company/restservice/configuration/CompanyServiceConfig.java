package com.company.restservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
 
/**
 * Configure Spring - this class is empty and the only reason for it’s existence is
 * for {@code ComponentScan} which provides beans auto-detection facility in the application.
 * 
 * @author Simon Njenga
 * @since 0.1
 */
@Configuration
@ComponentScan(basePackages = "com.company.restservice")
public class CompanyServiceConfig {
 
}
