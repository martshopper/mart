/**
 * 
 */
package com.main.mart.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.main.mart.common.dto.UserTO;
import com.main.mart.dto.UserTOs;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface UserIf {
	
	@POST
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(UserTO userTO);
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public UserTOs getAllUsers(
			@QueryParam("username") String username,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("lastName") String middleName,
			@QueryParam("phone") String phone,
			@QueryParam("dob") String dob,
			@QueryParam("sex") String sex,
			@QueryParam("level") String level
			);
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserTO getUserById(@PathParam("id") Integer id);
	
	@DELETE
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteType(@PathParam("id") Integer id);
}
