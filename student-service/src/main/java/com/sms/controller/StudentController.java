package com.sms.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sms.exception.AppException;
import com.sms.model.AcademicSession;
import com.sms.model.Session;
import com.sms.model.Student;
import com.sms.payload.ApiResponse;
import com.sms.payload.CreateStudentRequest;
import com.sms.payload.UpdateStudentRequest;
import com.sms.repository.AcademicSessionRepository;
import com.sms.repository.StudentRepository;
import com.sms.service.StudentServiceImpl;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/student")
@Api(value="student", description = "Data service operations on Student", tags=("students"))
public class StudentController {
	
	
	 private static Logger logger = LoggerFactory.getLogger(StudentController.class);
	 
	 @Autowired
	 StudentRepository studentRepository;
	 
	 @Autowired
	 AcademicSessionRepository academicSessionRepository;
	 
	 @Autowired
	 StudentServiceImpl studentService;
	 
	 @GetMapping("/")
	    public ArrayList<Student> getAllStudents() {

	        return studentRepository.findAll();
	    }
	 @GetMapping("/{studentId}")
	 public Student getStudentById(@PathVariable Long studentId) {
		 	return studentService.getStudentById(studentId);
	 	}
	 
	 @GetMapping("/{firstName}")
	 public Student getStudentByFirstName(@PathVariable String firstName) {
		 	return studentService.getStudentByFirstName(firstName);
	 	}
	 
	 	
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
	   
	 
	 @PostMapping("/create")
		@ApiOperation(value="Create", notes="Create a Student Record", nickname="createStudent")
	    public ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest,WebRequest request) throws Exception 
	    {
		 	return studentService.createStudent(createStudentRequest);
	    }
	 
	 @PutMapping("/update/{id}")
	 @ApiOperation(value="Update", notes="Update a Student Record", nickname="updateStudent")
	 public ResponseEntity<?> updateStudent(@PathVariable(value="id") Long studentId,@Valid @RequestBody UpdateStudentRequest updateStudentRequest, WebRequest request) throws Exception
	 {
		 return studentService.updateStudent(studentId,updateStudentRequest);
	 }
	 
	 
}
