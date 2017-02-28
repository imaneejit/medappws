package org.gs.medapp.security;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8138563903034296662L;

	public JwtTokenMalformedException(String msg) 
	{
		super(msg);
	}
}
