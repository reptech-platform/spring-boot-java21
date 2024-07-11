package com.apps.trippin.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "trip")
@Data
public class Trip implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5083060401095470783L;
	@Id
	@Column(name = "tripId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tripId;
	@Column(name = "shareId")
	private String shareId;
	@Column(name = "name")
	private String name;
	@Column(name = "budget")
	private Double budget;
	@Column(name = "description")
	private String description;
	@ElementCollection
	@CollectionTable(name = "TripTag", joinColumns = @JoinColumn(name = "tripId"))
	@Column(name = "tagId")
	private List<String> tags;
	@Column(name = "startsAt")
	private LocalDate startsAt;
	@Column(name = "endsAt")
	private LocalDate endsAt;
	@Column(name = "startTime")
	private LocalTime startTime;
	@Column(name = "endTime")
	private LocalTime endTime;
	@Column(name = "cost")
	private Float cost;
	@ManyToMany(mappedBy = "trips", fetch = FetchType.LAZY)
	private List<Person> travellers;

	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "TripId")
	private List<PlanItem> planItems = new ArrayList<>();

}
