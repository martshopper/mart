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
				type.setTypeCode(typeTO.getTypeCode());
				type.setTypeDescription(typeTO.getTypeDescription());
				type.setStatus(StatusEnum.A);
				ResponseStatus responseStatus = typeEJBIf.addType(type);
				if(responseStatus.getStatus()) {
					response.put("id", ""+responseStatus.getPersistingId());
					builder = Response.ok(response);
				}else {
					response.put("exception", responseStatus.getErrorMessage());
					builder = Response.ok(response);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return builder.build();
	}

	/* (non-Javadoc)
	 * @see com.main.mart.rest.TypeIf#updateType(java.lang.Integer, com.main.mart.dto.TypeTO)
	 */
	@Override
	public Response updateType(Integer id, TypeTO typeTO) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
