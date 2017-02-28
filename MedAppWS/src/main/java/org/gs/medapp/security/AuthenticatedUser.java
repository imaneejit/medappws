package org.gs.medapp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser implements UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3398357455274564526L;
	
	private final Integer id;
	private final String username;
	private final String token;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public AuthenticatedUser(Integer id, 
								String username, 
								String token,
								Collection<? extends GrantedAuthority> authorities)
	{
		super();
		this.id = id;
		this.username = username;
		this.token = token;
		this.authorities = authorities;
	}

	public Integer getId() 
	{
        return id;
    }
	
	public String getToken() 
	{
		return token;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return authorities;
	}

	@Override
	public String getPassword() 
	{
		return null;
	}

	@Override
	public String getUsername() 
	{
		return username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked() 
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() 
	{
		return true;
	}

	@Override
	public boolean isEnabled() 
	{
		return true;
	}

}
