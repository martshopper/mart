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

import com.main.mart.common.dto.ItemMasterTO;
import com.main.mart.dto.ItemMasterTOs;
import com.main.mart.ejb.ItemMasterEJBIf;
import com.main.mart.entity.ItemMaster;
import com.main.mart.entity.ItemType;
import com.main.mart.entity.TaxMaster;
import com.main.mart.entity.TypeItems;
import com.main.mart.entity.User;
import com.main.mart.utilities.CommonEnum;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class ItemMasterImpl implements ItemMasterIf {
	@EJB
	private ItemMasterEJBIf itemMasterEJBIf;
	ResponseBuilder builder;
	@Override
	public ItemMasterTOs getItemMasters(String itemCode, String itemName, String shortName, String genericName,
			String itemTypeId, String unitOfMeasure, String dispensingUnit, String taxable, String taxId,
			String remarks) {
		ItemMasterTOs itemMasterTOs = new ItemMasterTOs();
		try {
			ItemMasterTO itemMasterTO = new ItemMasterTO();
			itemMasterTO.setItemCode(itemCode);
			itemMasterTO.setItemName(itemName);
			itemMasterTO.setShortName(shortName);
			itemMasterTO.setGenericName(genericName);
			itemMasterTO.setItemTypeId(itemTypeId);
			itemMasterTO.setUnitOfMeasure(unitOfMeasure);
			itemMasterTO.setDispensingUnit(dispensingUnit);
			itemMasterTO.setTaxable(taxable);
			itemMasterTO.setTaxId(taxId);
			itemMasterTO.setRemarks(remarks);
			Collection<ItemMaster> itemMasters = itemMasterEJBIf.getItemMaster(itemMasterTO);
			List<ItemMasterTO> lstItemMasters = new ArrayList<ItemMasterTO>();
			if(itemMasters != null && !itemMasters.isEmpty()) {
				for(ItemMaster itemMaster : itemMasters) {
					ItemMasterTO masterTO = this.transformItemMasterTOfromEntity(itemMaster);
					if(masterTO != null) {
						lstItemMasters.add(masterTO);
					}
				}
			}
			itemMasterTOs.setDraw("1");
			itemMasterTOs.setRecordsFiltered(lstItemMasters.size()+"");
			itemMasterTOs.setRecordsTotal(lstItemMasters.size()+"");
			itemMasterTOs.setItemMasters(lstItemMasters);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return itemMasterTOs;
	}
	@Override
	public Response addUpdateItemMaster(ItemMasterTO itemMasterTO) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(itemMasterTO != null) {
				ItemMaster itemMaster = new ItemMaster();
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getId())) {
					itemMaster = itemMasterEJBIf.getItemMasterById(Integer.parseInt(itemMasterTO.getId()));
				}else if(!StringUtils.isNullOrEmpty(itemMasterTO.getCreatedBy())) {
					User createdBy = new User();
					createdBy.setId(Integer.parseInt(itemMasterTO.getCreatedBy()));
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getDispensingUnit())) {
					itemMaster.setDispensingUnit(itemMasterTO.getDispensingUnit());
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getGenericName())) {
					itemMaster.setGenericName(itemMasterTO.getGenericName());
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getItemCode())) {
					itemMaster.setItemCode(itemMasterTO.getItemCode());
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getItemName())) {
					itemMaster.setItemName(itemMasterTO.getItemName());
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getItemTypeId())) {
					ItemType itemTypeId = new ItemType();
					itemTypeId.setId(Integer.parseInt(itemMasterTO.getItemTypeId()));
					itemMaster.setItemTypeId(itemTypeId);
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getRemarks())) {
					itemMaster.setRemarks(itemMasterTO.getRemarks());
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getShortName())) {
					itemMaster.setShortName(itemMasterTO.getShortName());
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getTaxable())) {
					itemMaster.setTaxable(CommonEnum.valueOf(itemMasterTO.getTaxable()));
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getTaxId())) {
					TaxMaster taxId = new TaxMaster();
					taxId.setId(Integer.parseInt(itemMasterTO.getTaxId()));
					itemMaster.setTaxId(taxId);
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getUnitOfMeasure())) {
					TypeItems unitOfMeasure = new TypeItems();
					unitOfMeasure.setId(Integer.parseInt(itemMasterTO.getUnitOfMeasure()));
					itemMaster.setUnitOfMeasure(unitOfMeasure);
				}
				if(!StringUtils.isNullOrEmpty(itemMasterTO.getUpdatedBy())) {
					User updatedBy = new User();
					updatedBy.setId(Integer.parseInt(itemMasterTO.getUpdatedBy()));
					itemMaster.setUpdatedBy(updatedBy);
				}
				itemMaster.setStatus(StatusEnum.A);
				ResponseStatus responseStatus = itemMasterEJBIf.addUpdateItemMaster(itemMaster);
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
	public ItemMasterTO getItemMasterById(Integer id) {
		try {
			ItemMaster itemMaster = itemMasterEJBIf.getItemMasterById(id);
			return this.transformItemMasterTOfromEntity(itemMaster);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}return null;
	}
	@Override
	public Response deleteItemMaster(Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			ResponseStatus responseStatus = itemMasterEJBIf.deleteItemMaster(id);
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
	private ItemMasterTO transformItemMasterTOfromEntity(ItemMaster itemMaster) {
		try {
			if(itemMaster != null) {
				ItemMasterTO itemMasterTO = new ItemMasterTO();
				itemMasterTO.setId(String.valueOf(itemMaster.getId()));
				if(itemMaster.getCreatedBy() != null) {
					itemMasterTO.setCreatedBy(itemMaster.getCreatedBy().getId().toString());
					itemMasterTO.setCreatedByName(MartUtilities.getUserFullName(itemMaster.getCreatedBy()));
				}
				if(itemMaster.getCreatedDateTime() != null) {
					itemMasterTO.setCreatedDateTime(MartUtilities.cnvtDBDateTimeToUIDateTime(itemMaster.getCreatedDateTime()));
				}
				if(!StringUtils.isNullOrEmpty(itemMaster.getDispensingUnit())) {
					itemMasterTO.setDispensingUnit(itemMaster.getDispensingUnit());
				}
				if(!StringUtils.isNullOrEmpty(itemMaster.getGenericName())) {
					itemMasterTO.setGenericName(itemMaster.getGenericName());
				}
				if(!StringUtils.isNullOrEmpty(itemMaster.getItemCode())) {
					itemMasterTO.setItemCode(itemMaster.getItemCode());
				}
				if(!StringUtils.isNullOrEmpty(itemMaster.getItemName())) {
					itemMasterTO.setItemName(itemMaster.getItemName());
				}
				if(itemMaster.getItemTypeId() != null) {
					itemMasterTO.setItemTypeId(String.valueOf(itemMaster.getItemTypeId().getId()));
				}
				if(!StringUtils.isNullOrEmpty(itemMaster.getRemarks())) {
					itemMasterTO.setRemarks(itemMaster.getRemarks());
				}
				if(!StringUtils.isNullOrEmpty(itemMaster.getShortName())){
					itemMasterTO.setShortName(itemMaster.getShortName());
				}
				if(itemMaster.getStatus() != null) {
					itemMasterTO.setStatus(itemMaster.getStatus().toString());
				}				
				if(itemMaster.getTaxable() != null) {
					itemMasterTO.setTaxable(String.valueOf(itemMaster.getTaxable()));
				}
				if(itemMaster.getTaxId() != null) {
					itemMasterTO.setTaxId(itemMaster.getTaxId().getId().toString());
				}
				if(itemMaster.getUnitOfMeasure() != null) {
					itemMasterTO.setUnitOfMeasure(itemMaster.getUnitOfMeasure().getId().toString());
				}
				if(itemMaster.getUpdatedBy() != null) {
					itemMasterTO.setUpdatedBy(itemMaster.getUpdatedBy().getId().toString());
					itemMasterTO.setUpdatedByName(MartUtilities.getUserFullName(itemMaster.getUpdatedBy()));
				}
				if(itemMaster.getUpdatedDateTime() != null) {
					itemMasterTO.setUpdatedDateTime(MartUtilities.cnvtDBDateTimeToUIDateTime(itemMaster.getUpdatedDateTime()));
				}
				return itemMasterTO;
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}return null;
	}
}
