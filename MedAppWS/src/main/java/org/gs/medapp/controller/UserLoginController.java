package org.gs.medapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.gs.medapp.model.UserLogin;
import org.gs.medapp.security.JwtAuthenticationToken;
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
	 * request url: 	/users/{username} <br>
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
	 * @param	user		UserLogin object JSON
	 * @return 	id			generated id
	 */
	@PostMapping( "/users" )
	public ResponseEntity<Integer> addUser( @RequestBody UserLogin user )
	{
		_log.info("-----> creating user record..");
		
		Integer id = userLoginService.createUser(user);
		HttpStatus httpStatus = id == null ? HttpStatus.I_AM_A_TEAPOT : HttpStatus.OK;
		
		_log.info("-----> creating user record result: " + httpStatus);
		
		return new ResponseEntity<Integer>( id, httpStatus );
	}
	
	@PostMapping( "/users/authenticate" )
	public ResponseEntity<JwtAuthenticationToken> authenticateUser( @RequestBody UserLogin user )
	{
		_log.info("-----> authenticating user: " + user.getUsername());
		_log.info("-----> password: " + user.getPassword());
		
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
}
