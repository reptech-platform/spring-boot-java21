package com.apps.trippin.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.apps.trippin.model.PlanItem;

import lombok.Data;


@Data
public class TripDto implements Serializable{
	private Integer tripId;
	private String shareId;
	private String name;
	private Double budget;
	private String description;
	private List<String> tags;
	private LocalDate startsAt;
	private LocalDate endsAt;
	private LocalTime startTime;
	private LocalTime endTime;
	private Float cost;
	private List<PlanItem> planItems = new ArrayList<>();
}
