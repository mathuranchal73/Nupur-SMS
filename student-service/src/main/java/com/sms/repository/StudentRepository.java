package com.sms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
	

	ArrayList<Student> findAll();
	 Boolean existsByregistrationNo(String registrationNo);
	 Boolean existsBystudentEmail(String studentEmail);

}
