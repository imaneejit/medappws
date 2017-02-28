package org.gs.medapp.security;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2644871060717678433L;

	public JwtTokenMissingException(String msg)
	{
		super(msg);
	}

}
