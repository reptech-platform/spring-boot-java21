package com.apps.trippin.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.apps.trippin.model.Document;
import com.apps.trippin.model.Feature;
import com.apps.trippin.model.Location;
import com.apps.trippin.model.Person;
import com.apps.trippin.model.PersonGender;

import lombok.Data;

@Data
public class PersonDto implements Serializable{
	private String userName;
	private String firstName;
	private String lastName;
	private BigDecimal income;
	private LocalDate dateOfBirth;
	private String middleName;
	private PersonGender gender;
	private Integer age;
	private List<String> emails;
	private List<Location> addressInfo;
	private Location homeAddress;
	private Feature favoriteFeature;
	private Person bestFriend;
	private String bestFrindId;
	private List<TripDto> trips;
	private List<Document> attachments;
}
