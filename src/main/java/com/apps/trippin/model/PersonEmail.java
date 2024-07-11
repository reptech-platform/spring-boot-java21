package com.apps.trippin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "PersonEmail")
@Data
public class PersonEmail {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "PersonEmail.Id", sequenceName = "PersonEmail.Id", allocationSize = 1)
    private Integer id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "emailId")
    private String emailId;
}
