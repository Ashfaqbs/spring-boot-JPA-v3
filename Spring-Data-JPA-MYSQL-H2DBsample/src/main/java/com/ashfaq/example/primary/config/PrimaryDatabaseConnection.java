package com.ashfaq.example.primary.config;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "primaryEntityManagerFactory",
		transactionManagerRef = "primaryTransactionManager",
		basePackages = {"com.ashfaq.example.primary.repo"}
		)
public class PrimaryDatabaseConnection {
	
	@Value("${spring.primary.datasource.url}")
    private String url;
	
	@Value("${spring.primary.datasource.username}")
    private String username;
	
	@Value("${spring.primary.datasource.password}")
    private String password;
	
	
	@Primary
	@Bean(name = "primaryDbDataSource")
    public DataSource primaryDbDataSource(){
        return DataSourceBuilder.create()
        		.url(url)
        		.username(username)
        		.password(password)
        		.build();
    }
	
	@Primary
	@Bean(name = "primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("primaryDbDataSource") DataSource primaryDataSource) {
		 LocalContainerEntityManagerFactoryBean emf = builder
		            .dataSource(primaryDataSource)
		            .packages("com.ashfaq.example.primary.model")
		            .build();

		    // Set JPA properties
		    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		    emf.setJpaVendorAdapter(vendorAdapter);
		    
		    Map<String, Object> jpaProperties = new HashMap<>();
		    jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		    jpaProperties.put("hibernate.show_sql", true);
		    // Add other JPA properties here
		    
		    emf.setJpaPropertyMap(jpaProperties);

		    return emf;
	}
	
	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager primaryTransactionManager(
			@Qualifier("primaryEntityManagerFactory") EntityManagerFactory
			primaryEntityManagerFactory) {
		return new JpaTransactionManager(primaryEntityManagerFactory);
	}
}