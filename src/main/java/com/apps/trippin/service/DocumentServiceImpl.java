package com.apps.trippin.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apps.trippin.model.Document;
import com.apps.trippin.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService, InitializingBean {
	@Autowired
	DocumentRepository documentRepository;

	private final Path root = Paths.get(System.getProperty("user.home")+"/uploads");
	@Override
	public String saveDocument(MultipartFile file) {
		 String fileUploadStatus; 
	        try {
	        	Path userDir = Files.createDirectories(Paths.get(System.getProperty("user.home")+"/uploads"));
	        	Files.createDirectories(userDir);
	            Files.copy(file.getInputStream(), userDir.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
	            fileUploadStatus = "File Uploaded Successfully"; 
	            Document doc = new Document();
	            doc.setDocFileType(file.getOriginalFilename().split(".")[1]);
	            doc.setDocLocation(root.getFileName().toString());
	            doc.setDocName(file.getOriginalFilename());
	            Document result = documentRepository.save(doc);
	            fileUploadStatus  = fileUploadStatus +"doc ID "+result.getDocId();
	          } catch (Exception e) {
	            if (e instanceof FileAlreadyExistsException) {
	              throw new RuntimeException("A file of that name already exists.");
	            }
	            throw new RuntimeException(e.getMessage());
	          }
	        return fileUploadStatus;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 try {
		      Files.createDirectories(root);
		    } catch (IOException e) {
		      throw new RuntimeException("Could not initialize folder for upload!");
		    }
		
	}

	@Override
	public Resource getDocument(int docId) {	
		    try {
		    Optional<Document> document = documentRepository.findById(docId);
		    if(document.isPresent()) {
		      Path file = root.resolve(document.get().getDocLocation()+File.pathSeparator+document.get().getDocName());
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    }else {
		    	throw new RuntimeException("File Not found");
		    }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
	} 

}
