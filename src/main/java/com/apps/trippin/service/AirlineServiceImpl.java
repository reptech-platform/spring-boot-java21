package com.apps.trippin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.trippin.model.Airline;
import com.apps.trippin.model.Person;
import com.apps.trippin.repository.AirlineRepository;
import com.apps.trippin.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AirlineServiceImpl implements AirlineService{

	@Autowired 
	AirlineRepository airlineRepository;
	@Autowired
	PersonRepository personRepository;

	@Override
	public void save(Airline airline) {
		Person ceo = personRepository.findById(airline.getCeoId()).orElseThrow(()->new EntityNotFoundException("CEO not found"));
		airline.setCeo(ceo);
		airlineRepository.save(airline);
	}

}
