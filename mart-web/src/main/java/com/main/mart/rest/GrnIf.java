package com.main.mart.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.main.mart.common.dto.GrnDetailForm;
import com.main.mart.common.dto.GrnForm;
import com.main.mart.common.dto.GrnForms;


@Path("/")
public interface GrnIf {

	@GET
	@Path("/grns")
	@Produces({MediaType.APPLICATION_JSON})
	public GrnForms  getGrnForms(@QueryParam("grnRefNo") String grnRefNo,
			@QueryParam("vendorId") String vendorId,
			@QueryParam("serviceId") String serviceId,
			@QueryParam("storeId") String storeId,
			@QueryParam("invoiceNo") String invoiceNo,
			@QueryParam("invoiceFromDate") String invoiceFromDate,
			@QueryParam("invoiceToDate") String invoiceToDate,
			@QueryParam("receiptFromDate") String receiptFromDate,
			@QueryParam("receiptToDate") String receiptToDate);	
	
	@GET
	@Path("/grns/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public GrnForm getGrnById(@PathParam("id") Integer id); 
	
	@POST
	@Path("/grns")
	@Produces({MediaType.APPLICATION_JSON})
	public Response addGrnForm(GrnForm grnForm);
	
	@POST
	@Path("/grnDetails")
	@Produces({MediaType.APPLICATION_JSON})
	public Response validateGrnDetails(GrnDetailForm grnDetailForm);
	
	@PUT
	@Path("/grns/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateGrnForm(GrnForm grnForm, @PathParam("id") Integer id); 
	
	@DELETE
	@Path("/grns/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteGrn(@PathParam("id") Integer id);
	
}
