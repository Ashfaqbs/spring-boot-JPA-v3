package com.ashfaq.example.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class StudentService {

	@Autowired
	private StudentRepository StudentRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public List<Student> getStudentsByCriteria(List<Long> ids, List<String> names, List<String> departments) {
		return StudentRepository.findByIdsOrNamesOrDepartments(ids, names, departments);
	}

	public List<Student> getStudentsByCriteriav2(StudentFilterRequest filterRequest) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		Root<Student> student = cq.from(Student.class);

		// List to hold predicates (conditions)
		List<Predicate> predicates = new ArrayList<>();

		// Add predicates based on the provided fields
		if (filterRequest.getIds() != null && !filterRequest.getIds().isEmpty()) {
			predicates.add(student.get("id").in(filterRequest.getIds()));
		}

		if (filterRequest.getNames() != null && !filterRequest.getNames().isEmpty()) {
			predicates.add(student.get("name").in(filterRequest.getNames()));
		}

		if (filterRequest.getDepartments() != null && !filterRequest.getDepartments().isEmpty()) {
			predicates.add(student.get("department").in(filterRequest.getDepartments()));
		}

		// Combine predicates using AND
		cq.where(cb.and(predicates.toArray(new Predicate[0])));

		// Execute query and return result
		return entityManager.createQuery(cq).getResultList();
	}
}
