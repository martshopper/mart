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

import com.main.mart.common.dto.ItemTypeTO;
import com.main.mart.dto.ItemTypeTOs;
import com.main.mart.ejb.ItemTypeEJBIf;
import com.main.mart.entity.ItemType;
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
public class ItemTypeImpl implements ItemTypeIf {
	
	@EJB
	private ItemTypeEJBIf itemTypeEJBIf;
	ResponseBuilder builder;
	@Override
	public ItemTypeTOs getItemTypes(String typeCode, String typeName, String typeCategoryId) {
		ItemTypeTOs typeTOs = new ItemTypeTOs();
		try {
			ItemTypeTO itemType = new ItemTypeTO();
			itemType.setTypeCode(typeCode);
			itemType.setTypeName(typeName);
			itemType.setTypeCategoryId(typeCategoryId);
			Collection<ItemType> itemTypes = itemTypeEJBIf.getItemTypes(itemType);
			List<ItemTypeTO> itemTypeTOs = new ArrayList<ItemTypeTO>();
			if(itemTypes != null && !itemTypes.isEmpty()) {
				for(ItemType type : itemTypes) {
					ItemTypeTO itemTypeTO = this.transformItemTypeTOfromEntity(type);
					if(itemTypeTO != null) {
						itemTypeTOs.add(itemTypeTO);
					}
				}
			}
			typeTOs.setDraw("1");
			typeTOs.setRecordsFiltered(itemTypeTOs.size()+"");
			typeTOs.setRecordsTotal(itemTypeTOs.size()+"");
			typeTOs.setItemTypes(itemTypeTOs);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return typeTOs;
	}

	@Override
	public Response addUpdateItemType(ItemTypeTO itemTypeTO) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(itemTypeTO != null) {
				ItemType itemType = new ItemType();
				if(!StringUtils.isNullOrEmpty(itemTypeTO.getId())) {
					itemType = itemTypeEJBIf.getItemTypeById(Integer.parseInt(itemTypeTO.getId()));
				}
				if(!StringUtils.isNullOrEmpty(itemTypeTO.getTypeCategoryId())) {
					TypeItems typeCategoryId = new TypeItems();
					typeCategoryId.setId(Integer.parseInt(itemTypeTO.getTypeCategoryId()));
					itemType.setTypeCategoryId(typeCategoryId);
				}else {
					itemType.setTypeCategoryId(null);
				}
				if(!StringUtils.isNullOrEmpty(itemTypeTO.getTypeCode())) {
					itemType.setTypeCode(itemTypeTO.getTypeCode());
				}else {
					itemType.setTypeCode(null);
				}
				if(!StringUtils.isNullOrEmpty(itemTypeTO.getTypeName())) {
					itemType.setTypeName(itemTypeTO.getTypeName());
				}else {
					itemType.setTypeName(null);
				}
				itemType.setStatus(StatusEnum.A);
				ResponseStatus responseStatus = itemTypeEJBIf.addUpdateItemType(itemType);
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
		return builder.build();
	}

	@Override
	public ItemTypeTO getItemTypeById(Integer id) {
		try {
			ItemType itemType = itemTypeEJBIf.getItemTypeById(id);
			return this.transformItemTypeTOfromEntity(itemType);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}

	@Override
	public Response deleteItemType(Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			ResponseStatus responseStatus = itemTypeEJBIf.deleteItemType(id);
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
	private ItemTypeTO transformItemTypeTOfromEntity(ItemType itemType) {
		try {
			if(itemType != null) {
				ItemTypeTO itemTypeTO = new ItemTypeTO();
				itemTypeTO.setId(itemType.getId().toString());
				itemTypeTO.setTypeCode(itemType.getTypeCode());
				itemTypeTO.setTypeName(itemType.getTypeName());
				if(itemType.getTypeCategoryId() != null) {
					itemTypeTO.setTypeCategoryId(itemType.getTypeCategoryId().getId().toString());
				}
				itemTypeTO.setStatus(String.valueOf(itemType.getStatus()));
				return itemTypeTO;
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}return null;
	}
}
