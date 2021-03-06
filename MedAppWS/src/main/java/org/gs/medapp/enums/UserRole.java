package org.gs.medapp.enums;

public enum UserRole 
{
	ADMIN		(0),
	DOCTOR		(1),
	ASSISTANT	(2);
	
	private Integer num;
	private UserRole( Integer num )
	{
		this.num = num;
	}
	
	public Integer  getNum()
	{
		return num;
	}
}
