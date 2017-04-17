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

import com.main.mart.common.dto.TypeItemsTO;
import com.main.mart.common.dto.TypeItemsTOs;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface TypeItemsIf {
	
	@GET
	@Path("/typeitems")
	@Produces(MediaType.APPLICATION_JSON)
	public TypeItemsTOs getAllTypeItems(@QueryParam("itemCode") String itemCode, @QueryParam("description") String description, @QueryParam("typeId") String typeId);
	
	@GET
	@Path("/typeitems/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TypeItemsTO getTypeItemsById(@PathParam("id") Integer id);
	
	@POST
	@Path("/typeitems")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTypeItems(TypeItemsTO typeItemsTO);
	
	@DELETE
	@Path("/typeitems/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTypeItems(@PathParam("id") Integer id);
}
