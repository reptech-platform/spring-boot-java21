package com.apps.trippin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.trippin.model.Trip;

import jakarta.persistence.EntityManager;

@Repository
public class TripRepository extends SimpleJpaRepository<Trip, Integer	> {
	private final EntityManager em;
    public TripRepository(EntityManager em) {
        super(Trip.class, em);
        this.em = em;
    }
    @Override
    public List<Trip> findAll() {
        return em.createNativeQuery("Select * from Trip", Trip.class).getResultList();
    }
}
