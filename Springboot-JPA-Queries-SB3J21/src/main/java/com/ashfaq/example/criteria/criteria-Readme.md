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

