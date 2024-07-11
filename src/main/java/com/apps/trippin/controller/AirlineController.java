package com.apps.trippin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.trippin.model.Airline;
import com.apps.trippin.model.Person;
import com.apps.trippin.repository.AirlineRepository;
import com.apps.trippin.service.AirlineService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@OpenAPIDefinition
@Tag(name = "Trippin App Airline Controller")
@RestController
@RequestMapping("/airline")
public class AirlineController {
	@Autowired
	private AirlineRepository airlineRepository;
	@Autowired
	private AirlineService airlineService;
	@GetMapping
	public List<Airline> findAll() {
		
			return airlineRepository.findAll();
	}
	@GetMapping("/search")
	public List<Airline> findAll(Pageable pageable) {
			return airlineRepository.findAll();
	}
	@PostMapping
	public Airline save(@RequestBody Airline airline) {
		return airlineRepository.save(airline);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") String id, @RequestBody Airline airline) {
		airline.setAirlineCode(id);
		 airlineRepository.save(airline);
	}
	
	@GetMapping("/{id}")
	public Optional<Airline> getUser(@PathVariable("id") String id) {
		return airlineRepository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		airlineRepository.deleteById(id);
	}
	
	@GetMapping("/count")
	public Long count() {	
		return airlineRepository.count();

	}
	
	@GetMapping("/{id}/ceo")
	public Person getCeo(@PathVariable("id") String id) {
		Airline airLine = airlineRepository.findById(id).orElseThrow(()->new EntityNotFoundException("CEO not found"));
		return airLine.getCeo();
	}

}
