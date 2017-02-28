package org.gs.medapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "user_login" )
public class UserLogin 
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
	
	@Column( name = "USERNAME" )
	private String username;
	
	@Column( name = "PASSWORD" )
	private String password;
	
	@Column( name = "USER_DETAILS" )
	private Integer userDetails;
	
	@Column( name = "ROLE" )
	private Integer role;
	
	@Column( name = "STATUS" )
	private Integer status;
	
	public UserLogin() 
	{

	}

	public UserLogin(Date createdDate, Date updatedDate, String createdBy, String updatedBy, String updatedProgram,
			Integer id, String username, String password, Integer userDetails, Integer role, Integer status) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedProgram = updatedProgram;
		this.id = id;
		this.username = username;
		this.password = password;
		this.userDetails = userDetails;
		this.role = role;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Integer userDetails) {
		this.userDetails = userDetails;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
