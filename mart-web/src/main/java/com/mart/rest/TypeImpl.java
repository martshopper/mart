/**
 * 
 */
package com.mart.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.mart.dto.TypeTO;
import com.mart.ejb.TypeEJBIf;
import com.mart.entity.Type;
import com.mart.utilities.ResponseStatus;
import com.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TypeImpl implements TypeIf {
	
	ResponseBuilder builder;
	
	@EJB
	private TypeEJBIf typeEJBIf;  
	/* (non-Javadoc)
	 * @see com.mart.rest.TypeIf#addType(com.mart.dto.TypeTO)
	 */
	@Override
	public Response addType(TypeTO typeTO) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(typeTO != null) {
				Type type = new Type();
				type.setTypeCode(typeTO.getTypeCode());
				type.setTypeDescription(typeTO.getTypeDescription());
				type.setStatusEnum(StatusEnum.A);
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
	 * @see com.mart.rest.TypeIf#updateType(java.lang.Integer, com.mart.dto.TypeTO)
	 */
	@Override
	public Response updateType(Integer id, TypeTO typeTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
