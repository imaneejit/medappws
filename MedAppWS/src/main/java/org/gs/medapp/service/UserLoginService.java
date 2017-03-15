package org.gs.medapp.service;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.gs.medapp.dao.UserLoginDAO;
import org.gs.medapp.enums.UserStatus;
import org.gs.medapp.model.UserLogin;
import org.gs.medapp.security.JwtAuthenticationToken;
import org.gs.medapp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService 
{
	// logger
	private final Logger _log = Logger.getLogger( UserLoginService.class );
	
	@Autowired
	private UserLoginDAO userLoginDAO;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	// get all users
	public List<UserLogin> getAllUsers()
	{
		return userLoginDAO.list();
	}
	
	// get specific user using id
	public UserLogin getUserById( Integer id )
	{
		return userLoginDAO.get(id);
	}
	
	// get specific user using username
	public UserLogin getUserByUsername( String username )
	{
		return userLoginDAO.get(username);
	}
	
	// create user
	public void createUser( UserLogin user )
	{
		String generatedPassword = null;
				
		// check if username is already used
		UserLogin fetchUser = userLoginDAO.get(user.getUsername());
		if ( null == fetchUser )
		{
			user.setUsername(user.getUsername());
			user.setRole(user.getRole());
			user.setStatus(UserStatus.INITIAL.getNum());

			// password generator
			String uuid = UUID.randomUUID().toString();
			generatedPassword = uuid.substring(0, 8);
			user.setPassword(generatedPassword);
			
			userLoginDAO.create(user);
		}
		else
		{
			_log.error("-----> Username is already taken!");
		}
	}
	
	// authenticate user
	public JwtAuthenticationToken authenticate(String username, String password) 
	{
		JwtAuthenticationToken jwtToken = null;
		
		UserLogin user = userLoginDAO.get(username);
		if ( null == user )
		{
			_log.error("-----> User not found!");
		}
		else
		{
			if ( BCrypt.checkpw(password, user.getPassword()) )
			{
				// generate token
				String token = jwtUtil.generateToken(user);
				jwtToken = new JwtAuthenticationToken(token);
			}
			else
			{
				_log.error("-----> Credentials invalid!");
			}
		}
		
		return jwtToken;
	}
	
	// update user
	public void updateUser( UserLogin user )
	{
		userLoginDAO.update(user);
	}
	
	// delete user
	public void deleteUser( Integer id )
	{
		userLoginDAO.delete(id);
	}
}
