package com.apps.trippin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apps.trippin.model.Document;
import com.apps.trippin.repository.DocumentRepository;
import com.apps.trippin.service.DocumentService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition
@Tag(name = "Trippin App Document Controller")
@RestController
@RequestMapping("/document")
public class DocumentController{
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@PostMapping 
	public String uploadFile(@RequestParam("file") MultipartFile file){ 

		return documentService.saveDocument(file); 
    }
	
	@GetMapping 
	public List<Document> findAll(){ 
		return documentRepository.findAll();
    }
	
	@GetMapping("/count")
	public long count(){ 
		return documentRepository.count();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Resource> getDoc(@PathVariable(name = "id") int docId){ 
		Resource file =  documentService.getDocument(docId);
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
