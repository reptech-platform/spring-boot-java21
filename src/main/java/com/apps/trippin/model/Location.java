package com.apps.trippin.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "Address")
    private String address;

    @Column(name = "City")
    @Embedded
    private City city;

    @Column(name = "Code")
    private Integer code;
}
