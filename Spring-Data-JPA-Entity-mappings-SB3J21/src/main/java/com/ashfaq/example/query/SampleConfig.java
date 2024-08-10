package com.ashfaq.example.query;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:MangoEmpqueries.properties")})
public class SampleConfig {

	//reading a custom property we have to use @PropertySource annotation for single property file or @PropertySources for multiple properties files , we have to use them in a configuration class 
//	and then use @Value("${propkeyname}") to read the value from the property file
//	@Value("${prop}")
//	private String prop; EG can in main class
	
//	in logs : [2m2024-08-10T09:19:43.990+05:30[0;39m [32m INFO[0;39m [35m32728[0;39m [2m---[0;39m [2m[Spring-Data-JPA-Entity-mappings-SB3J21] [  restartedMain][0;39m [2m[0;39m[36mngDataJpaEntityMappingsSb3J21Application[0;39m [2m:[0;39m prop: SELECT e FROM MangoEmploye e WHERE e.departmentId = :departmentId AND (link unavailable) IN :employeeIds AND e.name IN :names

	
}
