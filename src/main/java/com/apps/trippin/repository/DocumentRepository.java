package com.apps.trippin.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.trippin.model.Document;

import jakarta.persistence.EntityManager;

@Repository
public class DocumentRepository extends SimpleJpaRepository<Document, Integer>  {
	private final EntityManager em;
    public DocumentRepository(EntityManager em) {
        super(Document.class, em);
        this.em = em;
    }
}
