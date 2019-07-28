package com.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.model.Student;
import com.sms.repository.StudentRepository;

@RestController("/api")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		
		return studentRepository.findAll();
		
	}
	
	@PostMapping("/student")
	public ResponseEntity<?> createStudent(@RequestBody Student student){
		
		Student s=studentRepository.save(student);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
