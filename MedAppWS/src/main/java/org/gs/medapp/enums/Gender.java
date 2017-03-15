package org.gs.medapp.enums;

public enum Gender 
{
	M	( 1, "Male" ),
	F	( 2, "Female" );
	
	private Integer code;
	private String description;
	private Gender( Integer code, String description )
	{
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode()
	{
		return code;
	}
	
	public String getDescription()
	{
		return description;
	}
}
