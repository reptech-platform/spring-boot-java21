package com.apps.trippin.model;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="planitem")
@Data
public class PlanItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "planItemId")
	private long planItemId;
	@Column(name = "confirmationCode")
	private String confirmationCode;
	@Column(name = "startsAt")
	private LocalTime startsAt;
	@Column(name = "endsAt")
	private LocalTime endsAt;
	@Column(name = "duration")
	private Duration duration;
	@Column(name = "tripId")
	private Integer tripId;
}
