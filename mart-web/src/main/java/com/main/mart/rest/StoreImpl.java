/**
 * 
 */
package com.main.mart.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.main.mart.common.dto.StoreMasterTO;
import com.main.mart.dto.StoreMasterTOs;
import com.main.mart.ejb.StoreMasterEJBIf;
import com.main.mart.entity.StoreMaster;
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
public class StoreImpl implements StoreIf {
	@EJB
	private StoreMasterEJBIf storeMasterEJBIf;
	ResponseBuilder builder;
	@Override
	public StoreMasterTOs getStores(String location, String storeCode, String storeName, String storeTypeId) {
		StoreMasterTOs masterTOs = new StoreMasterTOs();
		try {
			StoreMasterTO masterTO = new StoreMasterTO();
			masterTO.setLocation(location);
			masterTO.setStoreCode(storeCode);
			masterTO.setStoreName(storeName);
			masterTO.setStoreTypeId(storeTypeId);
			List<StoreMaster> storeMasters = storeMasterEJBIf.getStores(masterTO);
			List<StoreMasterTO> lstStoreMasters = new ArrayList<StoreMasterTO>();
			if(storeMasters != null && !storeMasters.isEmpty()) {
				for(StoreMaster master : storeMasters) {
					StoreMasterTO storeMasterTO = this.transformStoreMasterTofromEntity(master);
					if(storeMasterTO != null) {
						lstStoreMasters.add(storeMasterTO);
					}
				}
			}
			masterTOs.setDraw("1");
			masterTOs.setRecordsFiltered(lstStoreMasters.size()+"");
			masterTOs.setRecordsTotal(lstStoreMasters.size()+"");
			masterTOs.setStoreMaster(lstStoreMasters);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return masterTOs;
	}
	@Override
	public Response addStore(StoreMasterTO store) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(store != null) {
				StoreMaster storeMaster = new StoreMaster();
				if(!StringUtils.isNullOrEmpty(store.getId())) {
					storeMaster = storeMasterEJBIf.getStoreMasterById(Integer.parseInt(store.getId()));
				}
				if(!StringUtils.isNullOrEmpty(store.getLocation())) {
					storeMaster.setLocation(store.getLocation());
				}else {
					storeMaster.setLocation(null);
				}
				if(!StringUtils.isNullOrEmpty(store.getStoreCode())) {
					storeMaster.setStoreCode(store.getStoreCode());
				}else {
					storeMaster.setStoreCode(null);
				}
				if(!StringUtils.isNullOrEmpty(store.getStoreName())) {
					storeMaster.setStoreName(store.getStoreName());
				}else {
					storeMaster.setStoreName(null);
				}
				if(!StringUtils.isNullOrEmpty(store.getStoreTypeId())){
					TypeItems storeTypeId = new TypeItems();
					storeTypeId.setId(Integer.parseInt(store.getStoreTypeId()));
					storeMaster.setStoreTypeId(storeTypeId);
				}else {
					storeMaster.setStoreTypeId(null);
				}
				storeMaster.setStatus(StatusEnum.A);
				ResponseStatus responseStatus = storeMasterEJBIf.addStoreMaster(storeMaster);
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
	public StoreMasterTO getStoreById(Integer id) {
		try {
			StoreMaster storeMaster = storeMasterEJBIf.getStoreMasterById(id);
			return this.transformStoreMasterTofromEntity(storeMaster);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}
	@Override
	public Response deleteStore(Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			ResponseStatus responseStatus = storeMasterEJBIf.deleteStoreMaster(id);
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
	
	private StoreMasterTO transformStoreMasterTofromEntity(StoreMaster storeMaster) {
		try {
			if(storeMaster != null) {
				StoreMasterTO masterTO = new StoreMasterTO();
				masterTO.setId(String.valueOf(storeMaster.getId()));
				if(!StringUtils.isNullOrEmpty(storeMaster.getLocation())) {
					masterTO.setLocation(storeMaster.getLocation());
				}
				masterTO.setStatus(String.valueOf(storeMaster.getStatus()));
				masterTO.setStoreCode(storeMaster.getStoreCode());
				masterTO.setStoreName(storeMaster.getStoreName());
				if(storeMaster.getStoreTypeId() != null) {
					masterTO.setStoreTypeId(storeMaster.getStoreTypeId().getId()+"");
					masterTO.setStoreType(storeMaster.getStoreTypeId().getDescription());
				}
				return masterTO;
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		} return null;
	}
}
