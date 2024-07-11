package com.apps.trippin.service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.apps.trippin.model.Person;
import com.apps.trippin.repository.PersonRepository;
import com.apps.trippin.util.Utils;

@Service
public class PersonServiceImpl implements PersonService, InitializingBean{

	//@Value("${BUCKET_NAME}")
	private String bucketName ;
	//@Value("${ADDRESS_FILE}")
	private String fileName ;
	private String region;
	
	private static final String AWS_REGION = "AWS_REGION";
	
	private AmazonS3 s3Client = null;
	@Autowired
	PersonRepository personRepository;
	@Override
	public Person savePerson(Person perosn) {
		return personRepository.save(perosn);
		
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		region = System.getenv(AWS_REGION);
		if(region == null) {
			throw new RuntimeException("Unable Get AWS_REGION........!");
		}
		loadFromEnvironmentVariable(region);
		
	}
	
	private void loadFromEnvironmentVariable(String awsRegion) {
		this.s3Client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new EnvironmentVariableCredentialsProvider())
				.withRegion(awsRegion)
				.build();
	}

	private void saveImage(String imageName) {
		byte[] decodedBytes = Base64.getDecoder().decode("");
		try {
			FileUtils.writeByteArrayToFile(new File(Utils.getUserHome()+File.separator+"images"+imageName), decodedBytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
	}
}
