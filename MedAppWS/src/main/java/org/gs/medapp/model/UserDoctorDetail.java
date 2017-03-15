package org.gs.medapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "user_dctr_details" )
public class UserDoctorDetail 
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "ID" )
	private Integer id;
	
	@Column( name = "USER_LOGIN_ID" )
	private Integer userLoginId;
	
	@Column( name = "PRC_NUM" )
	private String prcNum;
	
	@Column( name = "CLINIC" )
	private String clinic;
	
	@Column( name = "PTR" )
	private String ptr;
	
	@Column( name = "MEDICAL_ART" )
	private String medicalArt;
	
	@Column( name = "SPECIALIZATION" )
	private String specialization;
	
	@Column( name = "NO_OF_ASSISTANT" )
	private Integer noOfassistant;
	
	public UserDoctorDetail() 
	{

	}

	public UserDoctorDetail(Integer id, Integer userLoginId, String prcNum, String clinic, String ptr,
			String medicalArt, String specialization, Integer noOfassistant) 
	{
		this.id = id;
		this.userLoginId = userLoginId;
		this.prcNum = prcNum;
		this.clinic = clinic;
		this.ptr = ptr;
		this.medicalArt = medicalArt;
		this.specialization = specialization;
		this.noOfassistant = noOfassistant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(Integer userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getPrcNum() {
		return prcNum;
	}

	public void setPrcNum(String prcNum) {
		this.prcNum = prcNum;
	}

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	public String getPtr() {
		return ptr;
	}

	public void setPtr(String ptr) {
		this.ptr = ptr;
	}

	public String getMedicalArt() {
		return medicalArt;
	}

	public void setMedicalArt(String medicalArt) {
		this.medicalArt = medicalArt;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Integer getNoOfassistant() {
		return noOfassistant;
	}

	public void setNoOfassistant(Integer noOfassistant) {
		this.noOfassistant = noOfassistant;
	}
}
