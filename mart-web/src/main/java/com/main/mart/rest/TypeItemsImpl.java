/**
 * 
 */
package com.main.mart.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.main.mart.common.dto.TypeItemsTO;
import com.main.mart.dto.TypeItemsTOs;
import com.main.mart.ejb.TypeItemsEJBIf;
import com.main.mart.entity.Type;
import com.main.mart.entity.TypeItems;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TypeItemsImpl implements TypeItemsIf {
	ResponseBuilder builder;
	@EJB
	private TypeItemsEJBIf typeItemsEJBIf;
	
	@Override
	public TypeItemsTOs getAllTypeItems(String itemCode, String description, String typeId) {
		TypeItemsTOs typeItemsTOs = new TypeItemsTOs();
		try {
			TypeItemsTO typeItemsTO = new TypeItemsTO();
			typeItemsTO.setItemCode(itemCode);
			typeItemsTO.setItemDescription(description);
			typeItemsTO.setTypeId(typeId);
			Collection<TypeItemsTO> colTypeItems = new ArrayList<TypeItemsTO>();
			Collection<TypeItems> typeItems = typeItemsEJBIf.getAllTypeItems(typeItemsTO);
			if(typeItems != null && !typeItems.isEmpty()) {
				for(TypeItems items : typeItems) {
					TypeItemsTO itemsTO = new TypeItemsTO();
					itemsTO.setId(items.getId().toString());
					itemsTO.setItemCode(items.getCode());
					itemsTO.setItemDescription(items.getDescription());
					itemsTO.setTypeId(items.getTypeId().getId().toString());
					itemsTO.setTypeCode(items.getTypeId().getTypeCode());
					itemsTO.setTypeDescription(items.getTypeId().getTypeDescription());
					itemsTO.setStatus(items.getStatus().toString());
					colTypeItems.add(itemsTO);
				}
			}
			typeItemsTOs.setDraw("1");
			typeItemsTOs.setRecordsFiltered(colTypeItems.size()+"");
			typeItemsTOs.setRecordsTotal(colTypeItems.size()+"");
			typeItemsTOs.setTypeItemsTOs(colTypeItems);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return typeItemsTOs;
	}

	@Override
	public TypeItemsTO getTypeItemsById(Integer id) {
		TypeItemsTO itemsTO = null;
		try {
			TypeItems items = typeItemsEJBIf.getTypeItemsById(id);
			if(items != null) {
				itemsTO = new TypeItemsTO();
				itemsTO.setId(items.getId().toString());
				itemsTO.setItemCode(items.getCode());
				itemsTO.setItemDescription(items.getDescription());
				itemsTO.setTypeId(items.getTypeId().getId().toString());
				itemsTO.setStatus(items.getStatus().toString());
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return itemsTO;
	}

	@Override
	public Response addTypeItems(TypeItemsTO typeItemsTO) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(typeItemsTO != null) {
				TypeItems typeItems = new TypeItems();
				if(!StringUtils.isNullOrEmpty(typeItemsTO.getId())) {
					typeItems = typeItemsEJBIf.getTypeItemsById(Integer.parseInt(typeItemsTO.getId()));
				}
				if(!StringUtils.isNullOrEmpty(typeItemsTO.getTypeId())) {
					Type typeId = new Type();
					typeId.setId(Integer.parseInt(typeItemsTO.getTypeId()));
					typeItems.setTypeId(typeId);
				}else {
					typeItems.setTypeId(null);
				}
				if(!StringUtils.isNullOrEmpty(typeItemsTO.getItemCode())) {
					typeItems.setCode(typeItemsTO.getItemCode());
				}else {
					typeItems.setCode(null);
				}
				if(!StringUtils.isNullOrEmpty(typeItemsTO.getItemDescription())) {
					typeItems.setDescription(typeItemsTO.getItemDescription());
				}else {
					typeItems.setDescription(null);
				}
				typeItems.setStatus(StatusEnum.A);
				ResponseStatus responseStatus = typeItemsEJBIf.addTypeItems(typeItems);
				if(responseStatus.getStatus()) {
					response.put("id", ""+responseStatus.getPersistingId());
					builder = Response.ok(response);
				}else {
					response.put("exception", responseStatus.getErrorMessage());
					builder = Response.status(400).entity(response);
				}
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
			response.put("exception", e.getMessage());
			builder = Response.status(400).entity(response);
		}
		return null;
	}

	@Override
	public Response deleteTypeItems(Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			ResponseStatus responseStatus = typeItemsEJBIf.deleteTypeItems(id);
			if(responseStatus.getStatus()) {
				builder = Response.ok(response);
			}else {
				response.put("exception", responseStatus.getErrorMessage());
				builder = Response.status(400).entity(response);
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
			response.put("exception", e.getMessage());
			builder = Response.status(400).entity(response);
		}
		return builder.build();
	}

	@Override
	public TypeItemsTOs getTypeItemsTypeCode(String typeCode) {
		TypeItemsTOs typeItemsTOs = new TypeItemsTOs();
		try {
			Collection<TypeItemsTO> colTypeItems = new ArrayList<TypeItemsTO>();
			List<TypeItems> typeItems = typeItemsEJBIf.getTypeItemsByTypeCode(typeCode);
			if(typeItems != null && !typeItems.isEmpty()) {
				for(TypeItems items : typeItems) {
					TypeItemsTO itemsTO = new TypeItemsTO();
					itemsTO.setId(items.getId().toString());
					itemsTO.setItemCode(items.getCode());
					itemsTO.setItemDescription(items.getDescription());
					/*itemsTO.setTypeId(items.getTypeId().getId().toString());
					itemsTO.setTypeCode(items.getTypeId().getTypeCode());
					itemsTO.setTypeDescription(items.getTypeId().getTypeDescription());
					itemsTO.setStatus(items.getStatus().toString());*/
					colTypeItems.add(itemsTO);
				}
			}
			typeItemsTOs.setDraw("1");
			typeItemsTOs.setRecordsFiltered(colTypeItems.size()+"");
			typeItemsTOs.setRecordsTotal(colTypeItems.size()+"");
			typeItemsTOs.setTypeItemsTOs(colTypeItems);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return typeItemsTOs;
	}

}
