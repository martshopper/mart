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

import com.main.mart.common.dto.ItemTypeTO;
import com.main.mart.dto.ItemTypeTOs;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface ItemTypeIf {
	
	@GET
	@Path("/itemtype")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemTypeTOs getItemTypes(@QueryParam("typeCode") String typeCode, @QueryParam("typeName") String typeName, @QueryParam("typeCategoryId") String typeCategoryId);
	
	@POST
	@Path("/itemtype")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUpdateItemType(ItemTypeTO itemType);
	
	@GET
	@Path("/itemtype/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemTypeTO getItemTypeById(@PathParam("id") Integer id);
	
	@DELETE
	@Path("/itemtype/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItemType(@PathParam("id") Integer id);
}
