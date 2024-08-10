package com.ashfaq.example.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomMangoEmployeRepositoryImpl  {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${mango.employee.query}")
    private String query;
	
	    public List<MangoEmploye> findByDepartmentIdAndEmployeeIdsAndNamesByEntityManager(Long departmentId, List<Long> employeeIds, List<String> names) {
	        return entityManager.createQuery(query)
	        		 .setParameter("departmentId", departmentId)
	                 .setParameter("employeeIds", employeeIds)
	                 .setParameter("names", names)
	                 .getResultList();
	        
	        
	        
	        /***
	         	      Always do a type safety check 

	         
	          List<Object[]> resultList = query.getResultList();
    		List<MangoEmploye> mangoEmployeList = new ArrayList<>();
    
    		for (Object[] row : resultList) {
        // Assuming the order of elements in the Object array matches the fields in MangoEmploye
        Long id = (Long) row[0];
        String name = (String) row[1]; // Adjust index based on your entity's field order
        
        // Construct a MangoEmploye instance and add it to the list
        MangoEmploye mangoEmploye = new MangoEmploye(id, name); // Adjust constructor parameters as necessary
        mangoEmployeList.add(mangoEmploye);
    		}
    
    			return mangoEmployeList;
	         ***/
	        
	        
	    }
	    
/***
 http://localhost:8080/mango-employees/searchv1?departmentId=1&employeeIds=1,2,3&names=Alice,Bob
 
 [
{
"id": 1,
"name": "Alice",
"departmentId": 1
},
{
"id": 2,
"name": "Bob",
"departmentId": 1
}
]



 
 http://localhost:8080/mango-employees/searchv1-with-entity-manager?departmentId=1&employeeIds=1,2,3&names=Alice,Bob
 [
{
"id": 1,
"name": "Alice",
"departmentId": 1
},
{
"id": 2,
"name": "Bob",
"departmentId": 1
}
]
 */
    
	    
	    
	    //Pagination
	    
	    public List<MangoEmploye> findByDepartmentIdAndEmployeeIdsAndNamesByEntityManager(Long departmentId, List<Long> employeeIds, List<String> names, int pageNumber, int pageSize) {
	
	        int firstResult = pageNumber * pageSize;
	        
	    	List<MangoEmploye> resultList = entityManager.createQuery(query, MangoEmploye.class).
	        // Set parameters
	        setParameter("departmentId", departmentId).
	        setParameter("employeeIds", employeeIds).
	        setParameter("names", names).
	        // Apply pagination
	        setFirstResult(firstResult).
	        setMaxResults(pageSize).getResultList();
	        
	        return resultList;
	    }  	
    
    
}
