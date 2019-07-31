package com.sms.payload;
/**
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sms.model.AcademicSession;

public class CreateStudentRequest {
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfAdmission;
	
	private Set<AcademicSession> academicSessions= new HashSet<>();
	
	 private String studentEmail;
	
	private String parentEmail;
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public Set<AcademicSession> getAcademicSessions() {
		return academicSessions;
	}

	public void setAcademicSessions(Set<AcademicSession> academicSessions) {
		this.academicSessions = academicSessions;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	
	
}**/
