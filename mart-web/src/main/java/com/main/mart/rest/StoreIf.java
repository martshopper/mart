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

import com.main.mart.common.dto.StoreMasterTO;
import com.main.mart.dto.StoreMasterTOs;

/**
 * @author Hitesh
 *
 */
@Path("/")
public interface StoreIf {
	
	@GET
	@Path("/store")
	@Produces(MediaType.APPLICATION_JSON)
	public StoreMasterTOs getStores(@QueryParam("location") String location, @QueryParam("storeCode") String storeCode, @QueryParam("storeName") String storeName, @QueryParam("storeTypeId") String storeTypeId);
	
	@POST
	@Path("/store")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStore(StoreMasterTO store);
	
	@GET
	@Path("/store/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public StoreMasterTO getStoreById(@PathParam("id") Integer id);
	
	@DELETE
	@Path("/store/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStore(@PathParam("id") Integer id);
}
