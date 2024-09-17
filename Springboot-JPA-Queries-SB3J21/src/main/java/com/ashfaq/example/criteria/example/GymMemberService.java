package com.ashfaq.example.criteria.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
