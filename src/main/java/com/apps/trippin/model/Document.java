package com.apps.trippin.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="document")
@Data
public class Document implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7418289602337257460L;
	@Id
	@Column(name = "docId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docId;
	@Column(name = "docName")
	private String docName;
	@Column(name = "docLocation")
	private String docLocation;
	@Column(name = "docFileType")
	private String docFileType;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", insertable = false, updatable = false)
    private Person person;
    @Column(name = "personId")
    private String personId;
}
