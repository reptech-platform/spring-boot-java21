package com.apps.trippin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.trippin.model.Person;

import jakarta.persistence.EntityManager;


@Repository
public class PersonRepository extends SimpleJpaRepository<Person, String> {

	private final EntityManager em;
    public PersonRepository(EntityManager em) {
        super(Person.class, em);
        this.em = em;
    }
    @Override
    public List<Person> findAll() {
        return em.createNativeQuery("Select * from Person", Person.class).getResultList();
    }

}
