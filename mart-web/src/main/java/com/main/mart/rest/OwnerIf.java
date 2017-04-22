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

import com.main.mart.common.dto.OwnerTO;
import com.main.mart.dto.OwnerTOs;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface OwnerIf {
	
	@GET
	@Path("/owner")
	@Produces(MediaType.APPLICATION_JSON)
	public OwnerTOs getOwners(@QueryParam("shortName") String shortName, @QueryParam("fullName") String fullName, @QueryParam("phone") String phone);
	
	@GET
	@Path("/owner/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OwnerTO getOwnerById(@PathParam("id") Integer id);
	
	@POST
	@Path("/owner")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOwner(OwnerTO ownerTO);
	
	@DELETE
	@Path("/owner/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOwner(@PathParam("id") Integer id);
}
