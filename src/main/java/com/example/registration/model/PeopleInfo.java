package com.example.registration.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class PeopleInfo {
	
	
	
	public PeopleInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PeopleInfo(Long id, String firstName, String middleName, String familyName, Date dOB, String placeOfBirth,
			String nationality) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.familyName = familyName;
		this.dOB = dOB;
		this.placeOfBirth = placeOfBirth;
		this.nationality = nationality;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", length = 20)
	private String firstName;
	
	@Column(name = "middle_name", length = 20)
	private String middleName;
	
	@Column(name = "family_name", length = 20)
	private String familyName;
	
	@Column(name = "dob")
	private java.sql.Date dOB;
	
	@Column(name = "birth_place", length = 20)
	private String placeOfBirth;
	
	@Column(name = "nationality", length = 20)
	private String nationality;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public java.sql.Date getdOB() {
		return dOB;
	}

	public void setdOB(java.sql.Date dOB) {
		this.dOB = dOB;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	

}
