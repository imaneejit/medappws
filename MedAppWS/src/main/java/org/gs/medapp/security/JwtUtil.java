package org.gs.medapp.security;

import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.gs.medapp.constants.Constants;
import org.gs.medapp.helper.PropertiesHelper;
import org.gs.medapp.model.UserLogin;
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
	
	private Properties appProps = PropertiesHelper.getInstance().getProperties();
	
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
			String secret = appProps.getProperty(Constants._STR_JWT_SECRET);
			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
			
			Long expiration = (Long) body.get("expiration");
			
			if ( null == expiration )
			{
				_log.error("-----> Invalid JWT token.");
			}
			else
			{
				Long current = System.currentTimeMillis();
				if ( current > expiration )
				{
					_log.error("-----> Invalid JWT token. Expired!");
				}
				else
				{
					user = new UserLogin();
					user.setUsername( body.getSubject() );
					user.setId((Integer) body.get("id"));
					user.setRole((Integer) body.get("role"));
					user.setUserDetails((Integer) body.get("userdetails"));
				}
			}
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
		claims.put("id", user.getId());
		claims.put("role", user.getRole());
		claims.put("userdetails", user.getUserDetails());
		
		// add expiration in claims
		Integer expirationHour = Integer.parseInt(appProps.getProperty(Constants._STR_JWT_EXPIRATION_HOUR));
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, expirationHour);
		
		claims.put("expiration", cal.getTime().getTime());
		
		String secret = appProps.getProperty(Constants._STR_JWT_SECRET);
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
}
