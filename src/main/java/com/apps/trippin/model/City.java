package com.apps.trippin.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class City implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5113004438513238991L;
	@Column(name = "LocationName")
    private String name;
    @Column(name = "countryRegion")
    private String countryRegion;
    @Column(name = "region")
    private String region;
}
