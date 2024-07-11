package com.apps.trippin.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.apps.trippin.dto.PersonDto;
import com.apps.trippin.model.Person;
import com.apps.trippin.model.Trip;
import com.apps.trippin.repository.PersonRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@OpenAPIDefinition
@Tag(name = "Trippin App Person Controller")
@RequestMapping("/person")
@RestController
public class PersonController {
	@Autowired
	PersonRepository personRepository;

	@Autowired
    private ModelMapper modelMapper;

	@Operation(description = "Get list of person objects.",
			summary = "To get all the Persons in the application",
			responses = {
					@ApiResponse(responseCode = "200", description = "Payload is list Person Records",
							content = @Content(schema = @Schema(implementation = Person.class)))
	})
	@GetMapping
	public List<PersonDto> findAll() {
		List<Person> list = personRepository.findAll();
		for(Person p:list) {
			if(p.getTrips() != null) {
				for(Trip trip:p.getTrips()) {
					trip.setTravellers(null);
				}
			}
		}
		return list.stream().map( e-> this.modelMapper.map(e, PersonDto.class)).toList();
	}

	@GetMapping("/{id}")
	public PersonDto findPerson(@PathVariable(name = "id") String userId) {	
		Optional<Person> result = personRepository.findById(userId);
		if(result.isPresent()) {
			if(result.get().getTrips() != null) {
				for(Trip trip:result.get().getTrips()) {
					trip.setTravellers(null);
				}
			}
			return this.modelMapper.map(result.get(), PersonDto.class);
		}
		return null;
	}

	@GetMapping("/search")
	public Page<Person> findAll(Pageable pageable) {	
		return personRepository.findAll(pageable);

	}
	
	@GetMapping("/count")
	public Long count() {	
		return personRepository.count();

	}

	@PostMapping()
	public Person save(@RequestBody Person perosn) {
		return personRepository.save(perosn);
	}
	@GetMapping("/{id}/trip")
	public List<Trip> findTrip(@PathVariable(name = "id") String userId) {	
		Person person = personRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("Trips not found"));
		return person.getTrips();

	}
	
	@PutMapping("/{id}")
	public Person update(@PathVariable(name = "id") String userId, @RequestBody Person perosn) {
		perosn.setUserName(userId);
		return personRepository.save(perosn);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") String userId) {	
		 personRepository.deleteById(userId)
;
	}
}
