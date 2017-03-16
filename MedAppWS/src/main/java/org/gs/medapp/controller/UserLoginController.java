package org.gs.medapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.gs.medapp.dto.ChangePasswordDTO;
import org.gs.medapp.dto.UserCreationDTO;
import org.gs.medapp.model.UserDoctorDetail;
import org.gs.medapp.model.UserLogin;
import org.gs.medapp.security.JwtAuthenticationToken;
import org.gs.medapp.service.UserDoctorDetailsService;
import org.gs.medapp.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController 
{
	// logger
	private final Logger _log = Logger.getLogger( UserLoginController.class );
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private UserDoctorDetailsService userDoctorDetailService;
	
	/**
	 * request type: 	GET <br>
	 * request url: 	/users <br>
	 * 
	 * <br>
	 * fetch all user records
	 * 
	 * @return List&lt;UserLogin&gt;	list of UserLogin object
	 */
	@GetMapping( "/users" )
	public ResponseEntity<List<UserLogin>> getUsers()
	{
		_log.info("-----> retrieving users..");
		
		List<UserLogin> users = userLoginService.getAllUsers();
		
		return new ResponseEntity<List<UserLogin>>( users, HttpStatus.OK );
	}
	
	/**
	 * request type:	GET <br>
	 * request url: 	/users/{id} <br>
	 * 
	 * <br>
	 * fetch user record by id
	 * 
	 * @param	id			primary key
	 * @return	user		UserLogin record
	 */
	@GetMapping( "/users/{id}" )
	public ResponseEntity<UserLogin> getUserById( @PathVariable Integer id )
	{
		_log.info("-----> retrieving user identified by: " + id);
		
		UserLogin user = userLoginService.getUserById(id);
		HttpStatus httpStatus = user == null ? HttpStatus.I_AM_A_TEAPOT : HttpStatus.OK;
		
		_log.info("-----> retrieve result: " + httpStatus);
		
		return new ResponseEntity<UserLogin>( user, httpStatus );
	}
	
	/**
	 * request type:	GET <br>
	 * request url: 	/users/name?username={username}<br>
	 * 
	 * <br>
	 * fetch user record by username
	 * 
	 * @param	username	user name of the record
	 * @return	user		UserLogin record
	 */
	@GetMapping( "/users/name" )
	public ResponseEntity<UserLogin> getUserByUsername( @RequestParam( value="username" ) String username )
	{
		_log.info("-----> retrieving user identified by: " + username);
		
		UserLogin user = userLoginService.getUserByUsername(username);
		HttpStatus httpStatus = user == null ? HttpStatus.I_AM_A_TEAPOT : HttpStatus.OK;
		
		_log.info("-----> retrieve result: " + httpStatus);
		
		return new ResponseEntity<UserLogin>( user, httpStatus );
	}
	
	/**
	 * request type: 	POST <br>
	 * request url: 	/users <br>
	 * 
	 * <br>
	 * insert user record
	 * 
	 * @param	UserCreationDTO		data transfer object for user creation		
	 * @return 	generatedPassword	generated password
	 */
	@PostMapping( "/users" )
	public ResponseEntity<String> addUser( @RequestBody UserCreationDTO userCreationDTO )
	{
		_log.info("-----> creating user record..");

		String generatedPassword = null;
		HttpStatus httpStatus = HttpStatus.OK;
		
		UserLogin user = new UserLogin();
		user.setUsername(userCreationDTO.getUsername());
		user.setRole(userCreationDTO.getRole());
		
		// create user login record
		userLoginService.createUser(user);
		
		if ( null != user.getId() )
		{
			if ( null != userCreationDTO.getNoOfAssistant() )
			{
				// create user doctor details
				UserDoctorDetail userDoctorDetail = new UserDoctorDetail();
				userDoctorDetail.setNoOfassistant(userCreationDTO.getNoOfAssistant());
				userDoctorDetail.setUserLoginId(user.getId());
				
				Integer userDocDetailsId = userDoctorDetailService.addDoctorDetails(userDoctorDetail);
				if ( null == userDocDetailsId )
				{
					_log.error("-----> There was an error creating the user doctor details record.");
					httpStatus = HttpStatus.I_AM_A_TEAPOT;
					userLoginService.deleteUser(user.getId());
				}
				else
				{
					generatedPassword = user.getPassword();
				}
			}
			else
			{
				generatedPassword = user.getPassword();
			}
		}
		else
		{
			_log.error("-----> There was an error creating the user login record.");
			httpStatus = HttpStatus.I_AM_A_TEAPOT;
		}
		
		_log.info("-----> creating user record result: " + httpStatus);
		
		return new ResponseEntity<String>( generatedPassword, httpStatus );
	}
	
	@PostMapping( "/users/authenticate" )
	public ResponseEntity<JwtAuthenticationToken> authenticateUser( @RequestBody UserLogin user )
	{
		_log.info("-----> authenticating user: " + user.getUsername());
		
		JwtAuthenticationToken jwtToken = userLoginService.authenticate( user.getUsername(), user.getPassword() );
		HttpStatus httpStatus = jwtToken == null ? HttpStatus.I_AM_A_TEAPOT : HttpStatus.OK;
		
		_log.info("-----> user authentication result: " + httpStatus);
		return new ResponseEntity<JwtAuthenticationToken>( jwtToken, httpStatus );
	}
	
	/**
	 * request type: 	PUT <br>
	 * request url: 	/users <br>
	 * 
	 * <br>
	 * update user record
	 * 
	 * @param	user			UserLogin object JSON
	 * @return	empty string
	 */
	@PutMapping( "/users" )
	public ResponseEntity<String> updateUser( @RequestBody UserLogin user )
	{
		_log.info("-----> updating user record..");
		
		userLoginService.updateUser(user);
		
		return new ResponseEntity<String>( "", HttpStatus.OK );
	}
	
	/**
	 * request type: 	DELETE <br>
	 * request url: 	/users/{id} <br>
	 * 
	 * <br>
	 * physical delete user record
	 * 
	 * @param	id	primary key
	 * @return	empty string
	 */
	@DeleteMapping( "/users/{id}" )
	public ResponseEntity<String> deleteUser( @PathVariable Integer id )
	{
		_log.info("-----> deleting user record identified by: " + id);
		
		userLoginService.deleteUser(id);
		
		return new ResponseEntity<String>( "", HttpStatus.OK );
	}
	
	/**
	 * request type:	POST <br>
	 * request url:		/users/changepass <br>
	 * 
	 * (1) facility for changing password <br>
	 * (2) https status 200 for successful transaction; 418 otherwise
	 * 
	 * @param changePasswordDTO	data transfer object for change password
	 * @return empty			empty string
	 */
	@PostMapping( "/users/changepass" )
	public ResponseEntity<String> changePassword( @RequestBody ChangePasswordDTO changePasswordDTO )
	{
		_log.info("-----> changing password for user: " + changePasswordDTO.getUsername());
		
		HttpStatus httpStatus = null;
		
		boolean result = userLoginService.changePassword( changePasswordDTO.getUsername(), 
															changePasswordDTO.getOldPassword(), 
															changePasswordDTO.getNewPassword());
		
		httpStatus = result ? HttpStatus.OK : HttpStatus.I_AM_A_TEAPOT;
		
		_log.info("-----> change password result: " + httpStatus);
		
		return new ResponseEntity<String>( "", httpStatus );
	}
}
