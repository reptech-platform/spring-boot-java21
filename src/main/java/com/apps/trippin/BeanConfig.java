package com.apps.trippin;

import org.springframework.context.annotation.Bean;

import com.apps.trippin.service.DocumentService;
import com.apps.trippin.service.DocumentServiceImpl;

public class BeanConfig {
	@Bean("fileServie")
	public DocumentService getDocBean() {
		return new DocumentServiceImpl();
	}

}
