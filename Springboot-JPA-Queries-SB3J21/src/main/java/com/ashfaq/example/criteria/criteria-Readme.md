To achieve CONDITIONS using the Criteria API, we will implement the logic where `name` and `weight` are mandatory filters and the `address`, `location`, and `height` are optional filters. If the optional fields are passed, they should be included in the query; otherwise, the query should filter based only on `name` and `weight`.

Here's how we can set up the code for this:

### 1. Entity: `GymMember`
```java
package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String address;
    private String location;
    private double weight;
    private double height;
    private String supportGuy;
}
```

### 2. Request DTO: `GymMemberSearchRequest`
This DTO is used to capture the search criteria.

```java
package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class GymMemberSearchRequest {
    private String name;
    private Double weight;
    private List<String> addressList;
    private List<String> locationList;
    private List<Double> heightList;
}
```

### 3. Criteria Query in the Service Layer
Here is the `GymMemberService` where we implement the Criteria API query logic:

```java
package com.example.service;

import com.example.dto.GymMemberSearchRequest;
import com.example.entity.GymMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymMemberService {

    private final EntityManager entityManager;

    public GymMemberService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<GymMember> findGymMembersByCriteria(GymMemberSearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GymMember> query = cb.createQuery(GymMember.class);
        Root<GymMember> root = query.from(GymMember.class);

        List<Predicate> predicates = new ArrayList<>();

        // Mandatory conditions: name and weight
        predicates.add(cb.equal(root.get("name"), request.getName()));
        predicates.add(cb.equal(root.get("weight"), request.getWeight()));

        // Optional conditions: address list, location list, height list
        if (request.getAddressList() != null && !request.getAddressList().isEmpty()) {
            predicates.add(root.get("address").in(request.getAddressList()));
        }

        if (request.getLocationList() != null && !request.getLocationList().isEmpty()) {
            predicates.add(root.get("location").in(request.getLocationList()));
        }

        if (request.getHeightList() != null && !request.getHeightList().isEmpty()) {
            predicates.add(root.get("height").in(request.getHeightList()));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}
```

### 4. Controller: `GymMemberController`
This controller receives the search request and delegates the logic to the service layer.

```java
package com.example.controller;

import com.example.dto.GymMemberSearchRequest;
import com.example.entity.GymMember;
import com.example.service.GymMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym-members")
public class GymMemberController {

    private final GymMemberService gymMemberService;

    public GymMemberController(GymMemberService gymMemberService) {
        this.gymMemberService = gymMemberService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<GymMember>> searchGymMembers(@RequestBody GymMemberSearchRequest request) {
        List<GymMember> gymMembers = gymMemberService.findGymMembersByCriteria(request);
        return ResponseEntity.ok(gymMembers);
    }
}
```

### 5. Example Request Payloads

#### 1. Full Data (All Optional Fields Provided)
```json
{
  "name": "John",
  "weight": 80.5,
  "addressList": ["Address1", "Address2"],
  "locationList": ["Location1", "Location2"],
  "heightList": [70.0, 75.0]
}
```

#### 2. Only Mandatory Fields
```json
{
  "name": "John",
  "weight": 80.5,
  "addressList": [],
  "locationList": [],
  "heightList": []
}
```

#### 3. Partial Data (Optional Address List Provided)
```json
{
  "name": "John",
  "weight": 80.5,
  "addressList": ["Address1"],
  "locationList": [],
  "heightList": []
}
```

### 6. SQL Query for Each Case

- **Full Data:**
```sql
SELECT * FROM gym_member 
WHERE name = 'John' 
  AND weight = 80.5 
  AND address IN ('Address1', 'Address2') 
  AND location IN ('Location1', 'Location2') 
  AND height IN (70.0, 75.0);
```

- **Only Mandatory Fields:**
```sql
SELECT * FROM gym_member 
WHERE name = 'John' 
  AND weight = 80.5;
```

- **Partial Data (Address Provided):**
```sql
SELECT * FROM gym_member 
WHERE name = 'John' 
  AND weight = 80.5 
  AND address IN ('Address1');
```

### Summary
- **Mandatory Fields:** `name` and `weight` must always be part of the query.
- **Optional Fields:** `addressList`, `locationList`, and `heightList` are optional. If the user provides these lists, they are included in the `IN` clause of the query. Otherwise, they are ignored.

This setup allows we to handle flexible search criteria based on the input.


### Results
 Api :  localhost:8080/gym-members/search 

 ```
Req : 
{
  "name": "John",
  "weight": 80.5,
  "addressList": ["Address1", "Address2"],
  "locationList": ["Location1", "Location2"],
  "heightList": [70.0, 75.0]
}

Resp : 

[
    {
        "id": 21,
        "name": "John",
        "address": "Address1",
        "location": "Location1",
        "weight": 80.5,
        "height": 75.0,
        "supportGuy": "Mike"
    },
    {
        "id": 26,
        "name": "John",
        "address": "Address1",
        "location": "Location1",
        "weight": 80.5,
        "height": 75.0,
        "supportGuy": "Mike"
    }
]





Req :

{
  "name": "John",
  "weight": 80.5,
  "addressList": [],
  "locationList": [],
  "heightList": []
}


Resp :

[
    {
        "id": 21,
        "name": "John",
        "address": "Address1",
        "location": "Location1",
        "weight": 80.5,
        "height": 75.0,
        "supportGuy": "Mike"
    },
    {
        "id": 26,
        "name": "John",
        "address": "Address1",
        "location": "Location1",
        "weight": 80.5,
        "height": 75.0,
        "supportGuy": "Mike"
    },
    {
        "id": 27,
        "name": "John",
        "address": "Address2",
        "location": "Location2",
        "weight": 80.5,
        "height": 80.0,
        "supportGuy": "Jake"
    },
    {
        "id": 28,
        "name": "John",
        "address": "Address1",
        "location": "Location3",
        "weight": 80.5,
        "height": 70.0,
        "supportGuy": "Paul"
    }
]

Req :

{
  "name": "John",
  "weight": 80.5,
  "addressList": ["Address1"],
  "locationList": [],
  "heightList": []
}


Resp :

[
    {
        "id": 21,
        "name": "John",
        "address": "Address1",
        "location": "Location1",
        "weight": 80.5,
        "height": 75.0,
        "supportGuy": "Mike"
    },
    {
        "id": 26,
        "name": "John",
        "address": "Address1",
        "location": "Location1",
        "weight": 80.5,
        "height": 75.0,
        "supportGuy": "Mike"
    },
    {
        "id": 28,
        "name": "John",
        "address": "Address1",
        "location": "Location3",
        "weight": 80.5,
        "height": 70.0,
        "supportGuy": "Paul"
    }
]

 ```