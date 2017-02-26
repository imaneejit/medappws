package org.gs.medapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetail 
{
	@Column( name = "CREATED_DATE" )
	private Date createdDate;
	
	@Column( name = "UPDATED_DATE" )
	private Date updatedDate;
	
	@Column( name = "CREATED_BY" )
	private String createdBy;
	
	@Column( name = "UPDATED_BY" )
	private String updatedBy;
	
	@Column( name = "UPDATED_PROGRAM" )
	private String updatedProgram;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "ID" )
	private Integer id;
	
	@Column( name = "LASTNAME" )
	private String lastname;
	
	@Column( name = "FIRSTNAME" )
	private String firstname;
	
	@Column( name = "MIDDLENAME" )
	private String middlename;
	
	@Column( name = "BIRTHDATE" )
	private String birthdate;
	
	@Column( name = "GENDER" )
	private String gender;
	
	@Column( name = "ADDRESS" )
	private String address;
	
	@Column( name = "EMAIL" )
	private String email;

	@Column( name = "ID_DESCRIPTION" )
	private String idDescription;
	
	@Column( name = "ID_NUMBER" )
	private String idNumber;
	
	@Column( name = "CONTACT_NUMBER" )
	private String contactNumber;
	
	@Column( name = "status" )
	private Integer status;

	public UserDetail() {
		super();
	}

	public UserDetail(Date createdDate, Date updatedDate, String createdBy, String updatedBy, String updatedProgram,
			Integer id, String lastname, String firstname, String middlename, String birthdate, String gender,
			String address, String email, String idDescription, String idNumber, String contactNumber, Integer status) 
	{
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedProgram = updatedProgram;
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.middlename = middlename;
		this.birthdate = birthdate;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.idDescription = idDescription;
		this.idNumber = idNumber;
		this.contactNumber = contactNumber;
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedProgram() {
		return updatedProgram;
	}

	public void setUpdatedProgram(String updatedProgram) {
		this.updatedProgram = updatedProgram;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdDescription() {
		return idDescription;
	}

	public void setIdDescription(String idDescription) {
		this.idDescription = idDescription;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
