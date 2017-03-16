package org.gs.medapp.security;

import org.gs.medapp.model.UserLogin;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8521130873875991231L;
	
	private String token;
	private UserLogin user;
	
	public JwtAuthenticationToken( String token, UserLogin user )
	{
		super(null, null);
		this.token = token;
		this.user = user;
	}

	public String getToken()
	{
		return token;
	}
	
	@Override
	public Object getCredentials()
	{
		return null;
	}
	
	@Override
    public Object getPrincipal() 
	{
        return user;
    }
}
