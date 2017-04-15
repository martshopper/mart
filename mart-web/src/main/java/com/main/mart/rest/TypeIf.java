/**
 * 
 */
package com.main.mart.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.main.mart.common.dto.TypeTO;
import com.main.mart.common.dto.TypeTOs;
/**
 * @author Hitesh
 *
 */
@Path("/")
public interface TypeIf {
	
	@GET
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON)
	public TypeTOs getAllType(@QueryParam("typeCode") String typeCode, @QueryParam("typeDescription") String typeDescription, @QueryParam("status") String status);
	
	@POST
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addType(TypeTO typeTO);
	
	@PUT
	@Path("/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateType(@PathParam("id") Integer id, TypeTO typeTO);
}
