package org.gs.medapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.gs.medapp.model.UserDetail;
import org.gs.medapp.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController
{
	// logger
	private final Logger _log = Logger.getLogger( UserDetailsController.class );
	
	@Autowired
	private UserDetailService userDetailsService;
	
	/**
	 * request type: 	GET <br>
	 * request url: 	/userdetails <br>
	 * 
	 * <br>
	 * fetch all user detail records
	 * 
	 * @return List&lt;UserDetail&gt;	list of UserDetail object
	 */
	@GetMapping( "/userdetails" )
	public ResponseEntity<List<UserDetail>> getUserDetails()
	{
		_log.info("-----> retrieving users details..");
		
		List<UserDetail> userDetails = userDetailsService.getAllUserDetails();
		
		return new ResponseEntity<List<UserDetail>>(userDetails, HttpStatus.OK);
	}
	
	/**
	 * request type:	GET <br>
	 * request url: 	/userdetails/{id} <br>
	 * 
	 * <br>
	 * fetch user detail record by id
	 * 
	 * @param	id			primary key
	 * @return	userDetail	UserDetail record
	 */
	@GetMapping( "/userdetails/{id}" )
	public ResponseEntity<UserDetail> getUserDetail( @PathVariable Integer id )
	{
		_log.info("-----> retrieving user details identified by: " + id);
		
		HttpStatus httpStatus = null;
		
		UserDetail userDetail = userDetailsService.getUserDetail(id);
		httpStatus = userDetail == null ? HttpStatus.I_AM_A_TEAPOT : HttpStatus.OK;
		
		_log.info("-----> retrieve result: " + httpStatus);
		
		return new ResponseEntity<UserDetail>(userDetail, httpStatus);
	}
	
	/**
	 * request type: 	POST <br>
	 * request url: 	/userdetails <br>
	 * 
	 * <br>
	 * insert user detail record
	 * 
	 * @param	userDetail	UserDetail object JSON
	 * @return 	id			generated id
	 */
	@PostMapping( "/userdetails" )
	public ResponseEntity<Integer> createUserDetail( @RequestBody UserDetail userDetail )
	{
		_log.info("-----> creating user detail record..");
		
		HttpStatus httpStatus = null;
		
		Integer userId = userDetailsService.createUserDetail(userDetail);
		httpStatus = userId == null ? HttpStatus.I_AM_A_TEAPOT : HttpStatus.OK;
		
		_log.info("-----> create user details result: " + httpStatus);
		
		return new ResponseEntity<Integer>(userId, httpStatus);
	}
	
	/**
	 * request type: 	PUT <br>
	 * request url: 	/userdetails <br>
	 * 
	 * <br>
	 * update user detail record
	 * 
	 * @param	userDetail	UserDetail object JSON
	 * @return	empty string
	 */
	@PutMapping( "/userdetails" )
	public ResponseEntity<String> updateUserDetail( @RequestBody UserDetail userDetail )
	{
		_log.info("-----> updating user details identified by: " + userDetail.getId());
		
		userDetailsService.updateUserDetail(userDetail);
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	/**
	 * request type: 	DELETE <br>
	 * request url: 	/userdetails/{id} <br>
	 * 
	 * <br>
	 * physical delete user detail record
	 * 
	 * @param	id	primary key
	 * @return	empty string
	 */
	@DeleteMapping( "/userdetails/{id}" )
	public ResponseEntity<String> deleteUserDetail( @PathVariable Integer id )
	{
		_log.info("-----> deleting user details identified by: " + id);
		
		userDetailsService.deleteUserDetail(id);
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
