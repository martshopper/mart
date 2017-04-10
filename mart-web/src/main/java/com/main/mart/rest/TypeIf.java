/**
 * 
 */
package com.main.mart.rest;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.main.mart.dto.TypeTO;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface TypeIf {
	
	@POST
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addType(TypeTO typeTO);
	
	@PUT
	@Path("/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateType(@PathParam("id") Integer id, TypeTO typeTO);
}
