package com.ashfaq.example.secondary.dbconfig;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "secondaryEntityManagerFactory",
		transactionManagerRef = "secondaryTransactionManager",
		basePackages = {"com.ashfaq.example.secondary.repo"},namedQueriesLocation = "classpath:queries.properties"
		)
public class SecondaryDatabaseConnection {
		
	@Value("${spring.secondary.datasource.url}")
    private String url;
	
	@Value("${spring.secondary.datasource.username}")
    private String username;
	
	@Value("${spring.secondary.datasource.password}")
    private String password;
	
	
	@Bean(name = "secondaryDbDataSource")
	public DataSource secondaryDbDataSource(){
        return DataSourceBuilder.create()
        		.url(url)
        		.username(username)
        		.password(password)
        		.build();
    }
	
	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean 
	secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("secondaryDbDataSource") DataSource secondaryDataSource) {
		 LocalContainerEntityManagerFactoryBean emf = builder
		            .dataSource(secondaryDataSource)
		            .packages("com.ashfaq.example.secondary.model")
		            .build();

		    // Set JPA properties
		    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		    emf.setJpaVendorAdapter(vendorAdapter);
		    
		    Map<String, Object> jpaProperties = new HashMap<>();
		    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		    jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		    jpaProperties.put("hibernate.show_sql", true);
		    // Add other JPA properties here
		    
		    emf.setJpaPropertyMap(jpaProperties);

		    return emf;
	}
	
	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager secondaryTransactionManager(
			@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory
			secondaryEntityManagerFactory) {
		return new JpaTransactionManager(secondaryEntityManagerFactory);
	}

}


/*
WHY 

*Multiple Data Sources:

Primary Data Source: Typically, a Spring Boot application uses a single data source configured automatically or explicitly.
Secondary Data Source: In some scenarios, you may need to connect to multiple databases. For instance, a secondary database might be used for reporting, analytics, or to separate different types of data.


*Custom Configuration for Each Data Source:

This configuration class can allows us to set specific properties for each data source, including JDBC URLs, credentials, JPA properties, and Hibernate settings.

*Query Externalization


Code Explanation:

Key Components in the Configuration Class

Data Source Bean (secondaryDbDataSource):

Creates a DataSource bean configured with URL, username, and password specific to the secondary database. This is necessary to establish a connection to the secondary database.
Entity Manager Factory Bean (secondaryEntityManagerFactory):

Configures LocalContainerEntityManagerFactoryBean for the secondary database. This includes setting the JPA vendor adapter (e.g., Hibernate), specifying the packages to scan for entities, and setting JPA properties like the dialect and whether to show SQL statements.
The properties set here (hibernate.dialect, hibernate.hbm2ddl.auto, etc.) are specific to the secondary database and allow customization of JPA behavior for that database.
Transaction Manager Bean (secondaryTransactionManager):

Configures JpaTransactionManager for managing transactions with the secondary database. This is necessary to handle transactional operations specific to the secondary data source.


Why All This is Needed

Separation of Concerns: Managing multiple databases often requires separating concerns and configuring each data source independently to handle its specific needs.
Custom Configuration: Different databases might require different settings (e.g., different dialects or transaction management strategies), so custom configuration ensures that the application interacts with each database correctly.
Named Queries and Repository Configuration: When using multiple databases, each data source might have its own set of named queries and repository configurations. The configuration class allows you to define these settings and ensure they are applied correctly.




*/