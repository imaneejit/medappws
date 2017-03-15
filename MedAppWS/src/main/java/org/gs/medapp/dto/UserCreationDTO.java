package org.gs.medapp.dto;

public class UserCreationDTO 
{
	private String username;
	private Integer role;
	private Integer noOfAssistant;
	
	public UserCreationDTO() 
	{

	}

	public UserCreationDTO(String username, Integer role, Integer noOfAssistant)
	{
		this.username = username;
		this.role = role;
		this.noOfAssistant = noOfAssistant;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getNoOfAssistant() {
		return noOfAssistant;
	}

	public void setNoOfAssistant(Integer noOfAssistant) {
		this.noOfAssistant = noOfAssistant;
	}
}
