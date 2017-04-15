/**
 * 
 */
package com.main.mart.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.main.mart.common.dto.UserTO;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface UserIf {
	
	public Response addUser(UserTO userTO);
}
