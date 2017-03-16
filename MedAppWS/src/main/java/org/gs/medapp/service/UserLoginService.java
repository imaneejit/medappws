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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
			if ( user.getStatus() > 1 )
			{
				if ( BCrypt.checkpw(password, user.getPassword()) )
				{
					// generate token
					String token = jwtUtil.generateToken(user);
					jwtToken = new JwtAuthenticationToken(token, user);
				}
				else
				{
					_log.error("-----> Credentials invalid!");
				}
			}
			else
			{
				if ( password.equals( user.getPassword() ) )
				{
					// generate token
					String token = jwtUtil.generateToken(user);
					jwtToken = new JwtAuthenticationToken(token, user);
				}
				else
				{
					_log.error("-----> Credentials invalid!");
				}
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
	
	public boolean changePassword( String username, String oldPassword, String newPassword )
	{
		boolean result = true;
		
		// fetch the user
		UserLogin user = userLoginDAO.get(username);
		if ( null != user )
		{
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			
			// using encryption depends on status
			if ( user.getStatus() > 1 )
			{
				// old user
				if ( BCrypt.checkpw(oldPassword, user.getPassword()) )
				{
					user.setPassword(encoder.encode(newPassword));
					userLoginDAO.update(user);
				}
				else
				{
					_log.error("-----> old password incorrect!");
					result = false;
				}
			}
			else
			{
				// new user
				if ( user.getPassword().equals(oldPassword) )
				{
					user.setPassword(encoder.encode(newPassword));
					user.setStatus(UserStatus.STEP2.getNum());
					userLoginDAO.update(user);
				}
				else
				{
					_log.error("-----> old password incorrect!");
					result = false;
				}
			}
		}
		else
		{
			_log.error("-----> User not found! Unable to change password!");
			result = false;
		}
		
		return result;
	}
}
