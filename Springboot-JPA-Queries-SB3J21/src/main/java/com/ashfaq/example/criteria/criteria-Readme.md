Repo :

```
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findByCriteria(String name, Integer age, String address, String school, String color) {
        // Step 1: Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);

        // Step 2: Define the Root entity
        Root<Person> root = criteriaQuery.from(Person.class);

        // Step 3: Build Predicates for mandatory fields (name and age)
        List<Predicate> mandatoryPredicates = new ArrayList<>();
        mandatoryPredicates.add(criteriaBuilder.equal(root.get("name"), name));
        mandatoryPredicates.add(criteriaBuilder.equal(root.get("age"), age));

        // Step 4: Build Predicates for optional fields (address, school, color)
        List<Predicate> optionalPredicates = new ArrayList<>();
        if (address != null && !address.isEmpty()) {
            optionalPredicates.add(criteriaBuilder.equal(root.get("address"), address));
        }
        if (school != null && !school.isEmpty()) {
            optionalPredicates.add(criteriaBuilder.equal(root.get("school"), school));
        }
        if (color != null && !color.isEmpty()) {
            optionalPredicates.add(criteriaBuilder.equal(root.get("color"), color));
        }

        // Step 5: Combine Predicates
        Predicate mandatoryCondition = criteriaBuilder.and(mandatoryPredicates.toArray(new Predicate[0]));
        Predicate optionalCondition = criteriaBuilder.or(optionalPredicates.toArray(new Predicate[0]));

        // Final combined condition
        Predicate finalCondition = criteriaBuilder.and(mandatoryCondition, optionalCondition);

        // Step 6: Apply combined conditions to the query
        criteriaQuery.where(finalCondition);

        // Step 7: Execute the query with limit of 1000 records
        return entityManager.createQuery(criteriaQuery)
                            .setMaxResults(1000)   // Set a limit of 1000 records
                            .getResultList();
    }
}

```

SQL eqvivalent :
```
SELECT * FROM person 
WHERE name = :name AND age = :age 
AND (address = :address OR school = :school OR color = :color);
```

service: 
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepositoryCustomImpl personRepository;

    public List<Person> searchPersons(String name, Integer age, String address, String school, String color) {
        return personRepository.findByCriteria(name, age, address, school, color);
    }
}

```


Controller: 

```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    // Search API with request parameters
    @GetMapping("/search")
    public List<Person> searchPersons(
            @RequestParam String name,
            @RequestParam Integer age,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String school,
            @RequestParam(required = false) String color) {
        return personService.searchPersons(name, age, address, school, color);
    }
}

```

Api url : GET http://localhost:8080/search?name=John&age=25&address=NY&school=Harvard&color=blue



-- sample criteria 


Let's break down your requirements:

1. ID and class are mandatory fields – They will be used in an AND condition (both are required).


2. Name, list of bikes, and list of cars are optional – These will be used in an OR condition (if any are present, they should be considered).



We will build a search feature using the Criteria API based on these requirements:

AND for mandatory fields (ID and class).

OR for optional fields (name, bikes, cars).


We'll pass everything as an entity to the service, then build the query using the Criteria API.


---

1. Entity Class: Person

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String personClass;  // Mandatory field

    private String name;  // Optional field

    @ElementCollection
    private List<String> bikes;  // Optional field (list of bikes)

    @ElementCollection
    private List<String> cars;  // Optional field (list of cars)

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPersonClass() { return personClass; }
    public void setPersonClass(String personClass) { this.personClass = personClass; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getBikes() { return bikes; }
    public void setBikes(List<String> bikes) { this.bikes = bikes; }

    public List<String> getCars() { return cars; }
    public void setCars(List<String> cars) { this.cars = cars; }
}


---

2. Custom Repository Method Using Criteria API

Here we will create a method that builds the dynamic query:

Custom Repository Interface:

import java.util.List;

public interface PersonRepositoryCustom {
    List<Person> searchPerson(Person person);
}

Custom Repository Implementation:

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> searchPerson(Person person) {
        // Step 1: Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);

        // Step 2: Define the Root (main entity)
        Root<Person> root = criteriaQuery.from(Person.class);

        // Step 3: Create a list to store predicates (conditions)
        List<Predicate> predicates = new ArrayList<>();

        // Step 4: Add mandatory fields (AND condition)
        Predicate idPredicate = criteriaBuilder.equal(root.get("id"), person.getId());
        Predicate classPredicate = criteriaBuilder.equal(root.get("personClass"), person.getPersonClass());
        Predicate andPredicate = criteriaBuilder.and(idPredicate, classPredicate);
        predicates.add(andPredicate);

        // Step 5: Create OR condition for optional fields
        List<Predicate> orConditions = new ArrayList<>();
        if (person.getName() != null && !person.getName().isEmpty()) {
            orConditions.add(criteriaBuilder.equal(root.get("name"), person.getName()));
        }
        if (person.getBikes() != null && !person.getBikes().isEmpty()) {
            orConditions.add(root.get("bikes").in(person.getBikes()));
        }
        if (person.getCars() != null && !person.getCars().isEmpty()) {
            orConditions.add(root.get("cars").in(person.getCars()));
        }

        // Add OR predicates to the query if any exist
        if (!orConditions.isEmpty()) {
            Predicate orPredicate = criteriaBuilder.or(orConditions.toArray(new Predicate[0]));
            predicates.add(orPredicate);
        }

        // Step 6: Set the predicates as the where clause of the query
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        // Step 7: Execute the query and return the results
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}


---

3. Service Layer

This service will accept the Person entity from the controller and pass it to the custom repository for querying.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepositoryCustom personRepositoryCustom;

    public List<Person> searchPerson(Person person) {
        return personRepositoryCustom.searchPerson(person);
    }
}


---

4. Controller Layer

This controller will receive the input via API call, create a Person object with the mandatory and optional fields, and pass it to the service for querying.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/search")
    public List<Person> searchPerson(@RequestBody Person person) {
        return personService.searchPerson(person);
    }
}


---

5. Sample Data and Request

Sample Request Body:

{
    "id": 1,
    "personClass": "ClassA",
    "name": "John",
    "bikes": ["Yamaha", "Honda"],
    "cars": ["Tesla", "BMW"]
}

SQL Script to Add Data:

INSERT INTO person (id, person_class, name)
VALUES (1, 'ClassA', 'John');

-- Assuming your bikes, cars are stored in a separate table due to @ElementCollection
INSERT INTO person_bikes (person_id, bikes)
VALUES (1, 'Yamaha'), (1, 'Honda');

INSERT INTO person_cars (person_id, cars)
VALUES (1, 'Tesla'), (1, 'BMW');


---

How it works:

1. The ID and personClass are mandatory, and the query will filter the results based on these fields with an AND condition.


2. The name, bikes, and cars are optional. If provided, the query will use an OR condition to match any of them.


3. If no optional fields are provided, only the mandatory fields will be used for filtering.



sample 02 








1. Person entity contains fields like ID, name, class, bike, and car (all single variables, not lists).


2. Request DTO will contain ID, name, class (single values) and lists of bikes and cars.



Your goal is to take the request DTO, pass the data to a query, and fetch records from the database where:

ID = the given ID.

Name = the given name.

Class = the given class.

Bike is in the list of bikes.

Car is in the list of cars.


1. Person Entity

The entity will be simple, with ID, name, personClass, bike, and car as single fields.

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String personClass;  // Mandatory

    private String name;  // Optional

    private String bike;  // Mandatory

    private String car;  // Mandatory

    // Getters and Setters
    //...
}

2. Request DTO

This DTO will contain lists for bikes and cars, as well as single values for ID, name, and class.

import java.util.List;

public class PersonRequestDTO {

    private Long id;
    private String name;
    private String personClass;

    private List<String> bikes;  // List of bikes to check against
    private List<String> cars;   // List of cars to check against

    // Getters and Setters
    //...
}

3. Query with Criteria API

You can now use the Criteria API to handle the case where bike and car should match any value in the lists provided in the PersonRequestDTO.

Service Layer

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final EntityManager entityManager;

    public PersonService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Person> findPersonsByCriteria(PersonRequestDTO requestDTO) {
        // Start building the Criteria API
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> person = query.from(Person.class);

        // Build the predicates (conditions)
        Predicate idPredicate = cb.equal(person.get("id"), requestDTO.getId());
        Predicate namePredicate = cb.equal(person.get("name"), requestDTO.getName());
        Predicate classPredicate = cb.equal(person.get("personClass"), requestDTO.getPersonClass());

        // For bikes and cars list, use "IN" clause
        Predicate bikePredicate = person.get("bike").in(requestDTO.getBikes());
        Predicate carPredicate = person.get("car").in(requestDTO.getCars());

        // Combine predicates (AND for ID, name, and class), (OR for bikes and cars)
        Predicate finalPredicate = cb.and(idPredicate, namePredicate, classPredicate);
        Predicate vehiclePredicate = cb.or(bikePredicate, carPredicate);

        // Final query where both conditions are applied
        query.select(person).where(cb.and(finalPredicate, vehiclePredicate));

        // Execute the query
        return entityManager.createQuery(query)
                            .setMaxResults(1000) // Limiting the result size
                            .getResultList();
    }
}

4. How It Works:

1. ID, Name, and Class: These are mandatory, so we create predicates (idPredicate, namePredicate, and classPredicate) that check for exact matches.


2. Bike and Car Lists: The in method is used to check if the bike and car are in the lists passed from the request DTO.


3. Combining Conditions:

AND is used for ID, name, and class.

OR is used for the bike and car.



4. Limiting the Results: We use setMaxResults(1000) to limit the number of results to 1000.



5. Controller Layer

Here's how you can call the service from a simple controller:

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/search")
    public List<Person> searchPersons(@RequestBody PersonRequestDTO requestDTO) {
        return personService.findPersonsByCriteria(requestDTO);
    }
}

6. SQL Equivalent Query (for reference):

SELECT * 
FROM person 
WHERE id = ? 
  AND name = ? 
  AND person_class = ? 
  AND (bike IN ('bike1', 'bike2', ...) OR car IN ('car1', 'car2', ...));

This SQL query mirrors what the Criteria API is doing—matching ID, name, and class exactly, and checking if the bike or car belongs to the provided lists.


---

