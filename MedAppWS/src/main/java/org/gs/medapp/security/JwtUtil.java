package org.gs.medapp.security;

import org.apache.log4j.Logger;
import org.gs.medapp.model.UserLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil 
{
	// logger
	private final Logger _log = Logger.getLogger( JwtUtil.class );
	
	@Value("${jwt.secret}")
	private String secret;
	
	/**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
	public UserLogin parseToken( String token )
	{
		UserLogin user = null;
		
		try 
		{
			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
	
			user = new UserLogin();
			user.setUsername( body.getSubject() );
			user.setId((Integer) body.get("id"));
			user.setRole((Integer) body.get("role"));
		} 
		catch (JwtException je) 
		{
			_log.error("Unable to parse token.", je);
		}
		catch (ClassCastException cce)
		{
			_log.error("Unable to parse token.", cce);
		}
		finally
		{
			;
		}
		
		return user;
	}
	
	 /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
	public String generateToken( UserLogin user )
	{
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("id", String.valueOf(user.getId()));
		claims.put("role", String.valueOf(user.getRole()));
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
}
