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

import com.main.mart.common.dto.ItemMasterTO;
import com.main.mart.dto.ItemMasterTOs;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface ItemMasterIf {
	
	@GET
	@Path("/itemmaster")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemMasterTOs getItemMasters(
			@QueryParam("itemCode") String itemCode,
			@QueryParam("itemName") String itemName,
			@QueryParam("shortName") String shortName,
			@QueryParam("genericName") String genericName,
			@QueryParam("itemTypeId") String itemTypeId,
			@QueryParam("unitOfMeasure") String unitOfMeasure,
			@QueryParam("dispensingUnit") String dispensingUnit,
			@QueryParam("taxable") String taxable,
			@QueryParam("taxId") String taxId,
			@QueryParam("remarks") String remarks);
	
	@POST
	@Path("/itemmaster")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUpdateItemMaster(ItemMasterTO itemMasterTO);
	
	@GET
	@Path("/itemmaster/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemMasterTO getItemMasterById(@PathParam("id") Integer id);
	
	@DELETE
	@Path("/itemmaster/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItemMaster(@PathParam("id") Integer id);
}
