package com.apps.trippin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.trippin.model.Person;
import com.apps.trippin.model.Trip;
import com.apps.trippin.repository.TripRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@OpenAPIDefinition
@Tag(name = "Trippin App Trip Controller")
@RequestMapping("/trip")
@RestController
public class TripController {
	@Autowired
	TripRepository tripRepository;
	
	@GetMapping
	public List<Trip> findAll() {
		
			return tripRepository.findAll();
	}
	
	@PostMapping
	public Trip save(@RequestBody Trip trip) {
		return tripRepository.save(trip);
	}
	
	@GetMapping("/search")
	public Page<Trip> findAll(Pageable pageable) {
		
			return tripRepository.findAll(pageable);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {

		tripRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Trip update(@PathVariable("id") Integer id, @RequestBody Trip trip) {
		trip.setTripId(id);
		return tripRepository.save(trip);
	}
	
	@GetMapping("/count")
	public Long count() {	
		return tripRepository.count();

	}
	
	@GetMapping("/{id}/travellers")
	public List<Person> findTravellers(@PathVariable("id") Integer id) {
		Trip trip = tripRepository.findById(id).orElseThrow(()->new EntityNotFoundException("CEO not found"));
		return trip.getTravellers();
	}
	
	@GetMapping("/{id}")
	public Optional<Trip> getUser(@PathVariable("id") Integer id) {
		return tripRepository.findById(id);
	}
}
