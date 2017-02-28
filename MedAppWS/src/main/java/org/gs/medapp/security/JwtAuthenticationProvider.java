package org.gs.medapp.security;

import java.util.List;

import org.gs.medapp.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider  
{
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean supports(Class<?> authentication) 
	{
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException 
	{
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException 
	{
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		String token = jwtAuthenticationToken.getToken();
		
		UserLogin user = jwtUtil.parseToken(token);
		if ( null == user )
		{
			throw new JwtTokenMalformedException("JWT token is invalid!");
		}
		
		List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(user.getRole()));
		
		return new AuthenticatedUser(user.getId(), user.getUsername(), token, authorityList);
	}
}
