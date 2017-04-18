/**
 * 
 */
package com.main.mart.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.main.mart.common.dto.TypeTO;
import com.main.mart.common.dto.TypeTOs;
import com.main.mart.ejb.TypeEJBIf;
import com.main.mart.entity.Type;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TypeImpl implements TypeIf {
	
	ResponseBuilder builder;
	
	@EJB
	private TypeEJBIf typeEJBIf;  
	
	@Override
	public TypeTOs getAllType(String typeCode, String typeDescription, String status) {
		TypeTOs typeTOs = new TypeTOs();
		Collection<TypeTO> colTypes = new ArrayList<TypeTO>();
		try {
			TypeTO typeTO = new TypeTO();
			typeTO.setTypeCode(typeCode);
			typeTO.setTypeDescription(typeDescription);
			typeTO.setStatus(status);
			Collection<Type> types = typeEJBIf.getTypes(typeTO);
			if(types != null && !types.isEmpty()) {
				for(Type type : types) {
					TypeTO typeTo = new TypeTO();
					typeTo.setId(type.getId().toString());
					typeTo.setTypeCode(type.getTypeCode());
					typeTo.setTypeDescription(type.getTypeDescription());
					typeTo.setStatus(type.getStatus().toString());
					colTypes.add(typeTo);
				}
			}
			typeTOs.setTypeTOs(colTypes);
			typeTOs.setDraw("1");
			typeTOs.setRecordsFiltered(colTypes.size()+"");
			typeTOs.setRecordsTotal(colTypes.size()+"");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return typeTOs;
	}
	
	@Override
	public Response addType(TypeTO typeTO) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(typeTO != null) {
				Type type = new Type();
				if(!StringUtils.isNullOrEmpty(typeTO.getId())) {
					type.setId(Integer.parseInt(typeTO.getId()));
				}
				type.setTypeCode(typeTO.getTypeCode());
				type.setTypeDescription(typeTO.getTypeDescription());
				type.setStatus(StatusEnum.A);
				ResponseStatus responseStatus = typeEJBIf.addType(type);
				if(responseStatus.getStatus()) {
					response.put("id", ""+responseStatus.getPersistingId());
					builder = Response.ok(response);
				}else {
					response.put("exception", responseStatus.getErrorMessage());
					builder = Response.status(400).entity(response);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.put("exception", e.getMessage());
			builder = Response.status(400).entity(response);
		}
		return builder.build();
	}

	@Override
	public TypeTO getTypeById(Integer id) {
		TypeTO typeTO = new TypeTO();
		try {
			Type type = typeEJBIf.getTypeById(id);
			typeTO.setId(type.getId().toString());
			typeTO.setTypeCode(type.getTypeCode());
			typeTO.setTypeDescription(type.getTypeDescription());
			typeTO.setStatus(type.getStatus().toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return typeTO;
	}

	@Override
	public Response deleteType(Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			ResponseStatus responseStatus = typeEJBIf.deleteType(id);
			if(responseStatus.getStatus()) {
				builder = Response.ok(response);
			}else {
				response.put("exception", responseStatus.getErrorMessage());
				builder = Response.status(400).entity(response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.put("exception", e.getMessage());
			builder = Response.status(400).entity(response);
		}
		return builder.build();
	}
	

}
