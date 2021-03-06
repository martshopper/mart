/**
 * 
 */
package com.main.mart.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.main.mart.common.dto.UserTO;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface CommonIf {
	

	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logoutUser(@Context HttpServletRequest req);
	
	@GET
	@Path("/loginuser")
	@Produces(MediaType.APPLICATION_JSON)
	public UserTO getLoginUser();
	
}
