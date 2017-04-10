/**
 * 
 */
package com.mart.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.mart.dto.UserTO;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface UserIf {
	
	public Response addUser(UserTO userTO);
}
