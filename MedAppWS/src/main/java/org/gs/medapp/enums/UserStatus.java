package org.gs.medapp.enums;

public enum UserStatus 
{
	ACTIVE	( 1 ),
	INACTIVE( 2 ),
	LOCKED	( 3 );
	
	private Integer num;
	private UserStatus( Integer num )
	{
		this.num = num;
	}
	
	public Integer  getNum()
	{
		return num;
	}
}
