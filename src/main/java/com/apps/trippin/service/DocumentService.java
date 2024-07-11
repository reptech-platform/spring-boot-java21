package com.apps.trippin.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
	String saveDocument(MultipartFile file);

	Resource getDocument(int docId);
}
