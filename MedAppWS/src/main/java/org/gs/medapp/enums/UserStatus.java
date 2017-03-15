package org.gs.medapp.enums;

public enum UserStatus 
{
	INITIAL	( 0 ),
	STEP1	( 1 ),
	STEP2	( 2 ),
	STEP3	( 3 ),
	STEP4	( 4 ),
	ACTIVE	( 5 ),
	INACTIVE( 6 );
	
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
