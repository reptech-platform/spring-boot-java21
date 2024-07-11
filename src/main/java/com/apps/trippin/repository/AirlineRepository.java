package com.apps.trippin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.trippin.model.Airline;

import jakarta.persistence.EntityManager;

@Repository
public class AirlineRepository extends SimpleJpaRepository<Airline, String> {

	private final EntityManager em;
    public AirlineRepository(EntityManager em) {
        super(Airline.class, em);
        this.em = em;
    }
    @Override
    public List<Airline> findAll() {
    	
        return em.createNativeQuery("Select * from Airline", Airline.class).getResultList();
    }


}
