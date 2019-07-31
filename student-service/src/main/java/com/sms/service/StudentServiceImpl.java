package com.sms.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.sms.model.Student;
import com.sms.payload.UploadBulkFileResponse;
import com.sms.repository.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {
	
	 	@Autowired
	    private EurekaClient eurekaClient;
	   
	    @Value("${service.upload-service.serviceId}")
	    private String uploadServiceServiceId;
	    
	    @Autowired
		 private RestTemplate restTemplate;
	    
	    @Autowired
	    StudentRepository studentRepository;
	    
	    @Autowired
		 ObjectMapper objectMapper;

	 public List<Student> readStudentsFromCSV(MultipartFile File) throws IOException
	 {
		 MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
	        bodyMap.add("file",getUserFileResource(File));
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

	        Application application = eurekaClient.getApplication(uploadServiceServiceId);
			InstanceInfo instanceInfo = application.getInstances().get(0);
			String url = "http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort()+"/"+"uploadBulkFile";
			System.out.println("URL" + url);
	        ResponseEntity<UploadBulkFileResponse> response = restTemplate.exchange(url,
	                HttpMethod.POST, requestEntity, UploadBulkFileResponse.class);
	        
	     
	        URL pathToFile = URI.create(response.getBody().getFileDownloadUri().toString()).toURL();
	        
	        ArrayList<Student> studentList = new ArrayList<Student>();
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(pathToFile.openStream()))) {
	       // try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
	        	 String line="";
	        	 
	        	 while ((line = br.readLine()) != null){
	        		 String[] attributes = line.split(",");
	        		 Student student= new Student(attributes[0],attributes[1],generateRegistrationNo(),attributes[2],attributes[3]);
	        		 student.setUuid(UUID.randomUUID().toString());
	     			 studentList.add(student);
	     			 
	        	 }
	        	 	 br.close();
	        		 studentRepository.saveAll(studentList);
	        		 
	        	 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return studentList;
	 }
	 
	 
	  String generateRegistrationNo() {
		 
		 Random generator= new Random();
		 //generator.setSeed(System.currentTimeMillis());
		 int i = generator.nextInt(1000000)%1000000;
		 java.text.DecimalFormat f = new java.text.DecimalFormat("000000");
		 Calendar cal= Calendar.getInstance();
		 int year= cal.get(Calendar.YEAR);
		 int month= cal.get(Calendar.MONTH);
		 int dayOfMonth= cal.get(Calendar.MONTH);
		 
		 String registrationNo= Integer.toString(cal.get(Calendar.YEAR))+'\\'+Integer.toString(cal.get(Calendar.MONTH))+'\\'+Integer.toString(cal.get(Calendar.DAY_OF_MONTH))+'\\'+Integer.toString(i);
				 
		 return registrationNo;
	 }


	public FileSystemResource getUserFileResource(MultipartFile File) throws IOException {
	        //todo replace tempFile with a real file
	        Path tempFile = Files.createTempFile(File.getName(),".csv");
	        Files.write(tempFile, File.getBytes());
	        System.out.println("uploading: " + tempFile);
	        File file = tempFile.toFile();
	        //to upload in-memory bytes use ByteArrayResource instead
	        return  new FileSystemResource(file);
	    }
}
