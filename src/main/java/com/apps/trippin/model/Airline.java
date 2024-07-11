package com.apps.trippin.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="Airline")
@Data
public class Airline implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1610032993667669522L;

	@Id
	@Column(name = "airlineCode")
	private String airlineCode;

	@Column(name = "name")
	private String name;
	
	@OneToOne
    @JoinColumn(name = "ceo_id")
    private Person ceo;
	
	@Transient
	private String ceoId;

}
