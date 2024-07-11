package com.apps.trippin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Person")
@Data
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3604589187447700133L;
	@Id
	@Column(name = "userName")
	private String userName;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "income")
	private BigDecimal income;
	@Column(name = "dateOfBirth")
	private LocalDate dateOfBirth;
	@Column(name = "middleName")
	private String middleName;
	@Column(name = "gender")
	private PersonGender gender;
	@Column(name = "age")
	private Integer age;
	@ElementCollection
	@CollectionTable(name = "PersonEmail", joinColumns = @JoinColumn(name = "userName"))
	@Column(name = "emailId")
	private List<String> emails;

	@ElementCollection 
	private List<Location> addressInfo;

	@Lob
	@Column(name = "homeAddress", columnDefinition = "BLOB")
	private Location homeAddress;

	@Column(name = "favoriteFeature")
	private Feature favoriteFeature;
	private List<Feature> features;
	@ManyToOne
	@JoinColumn(name = "bestFrindId", insertable = false, updatable = false)
	private Person bestFriend;
	@Column(name = "bestFrindId")
	private String bestFrindId;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinTable(
			name="PersonTrip",
			joinColumns = @JoinColumn( name="UserName"),
			inverseJoinColumns = @JoinColumn(name = "tripId"))
	private List<Trip> trips;

	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Document> attachments;
}
