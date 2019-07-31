package com.sms.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import com.sms.service.StudentServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/student")
@Api(value="student", description = "Data service operations on Student", tags=("students"))
public class StudentController {
	
	
	 protected Logger logger = LoggerFactory.getLogger(StudentController.class);
	 
	 @Autowired
	 StudentServiceImpl studentService;
	 	
	 @PostMapping("/bulkUpload")
		@ApiOperation(value="Upload the file", notes="Uploads a Multipart File and returns the download URI",produces = "application/json", nickname="uploadFile")
		public ResponseEntity<?> bulkUpload(@RequestParam("file") MultipartFile File) {
		try {
			studentService.readStudentsFromCSV(File);
			 return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		
	                
		
		}
	
	   
	   
	   
	 
	 /**@PostMapping("/Create")
		@ApiOperation(value="Create", notes="Create a Student Record", nickname="createStudent")
	    public static ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest,WebRequest request) throws Exception 
	    {
		 Random generator= new Random();
		 generator.setSeed(System.currentTimeMillis());
		 int i = generator.nextInt(1000000)%1000000;
		 java.text.DecimalFormat f = new java.text.DecimalFormat("000000");
		 Calendar cal= Calendar.getInstance();
		 int year= cal.get(Calendar.YEAR);
		 int month= cal.get(Calendar.MONTH);
		 int dayOfMonth= cal.get(Calendar.MONTH);
		 
		 String registrationNo= Integer.toString(cal.get(Calendar.YEAR))+'\\'+Integer.toString(cal.get(Calendar.MONTH))+'\\'+Integer.toString(cal.get(Calendar.DAY_OF_MONTH))+'\\'+Integer.toString(i);
				 
	    	logger.info(String.format("student-service createStudent() invoked: %s for %s", signUpRequest.getName())); 
			 Student student = new Student(createStudentRequest.getFirstName(), createStudentRequest.getLastName(), createStudentRequest.getDateOfAdmission(), registrationNo,createStudentRequest.getAcademicSessions(),false,createStudentRequest.getStudentEmail(),createStudentRequest.getParentEmail(), UUID.randomUUID().toString());
		      
			 //
			 if(userRepository.existsByUsername(user.getUsername())) {
		            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
		                    HttpStatus.BAD_REQUEST);
		        }

		        if(userRepository.existsByEmail(user.getEmail())) {
		            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
		                    HttpStatus.BAD_REQUEST);
		        }

		        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
		                .orElseThrow(() -> new AppException("User Role not set."));

		        user.setRoles(Collections.singleton(userRole));

		        try {
		        	
		        	User result = userRepository.save(user);
		        	
		        	String appUrl = request.getContextPath();
					eventPublisher.publishEvent(new OnRegistrationSuccessEvent(appUrl, request.getLocale(),result));
		        	
		        	
					URI location = ServletUriComponentsBuilder
			                .fromCurrentContextPath().path("/v1/user/{username}")
			                .buildAndExpand(result.getUsername()).toUri();
					
					 return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"+location));
				} catch (Exception e) 
		        {
					logger.error("Exception raised registerUser REST Call {0}", e);
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
				}      
	    }**/
	 
	 
}
