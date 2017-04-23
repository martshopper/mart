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

import com.main.mart.common.dto.OwnerTO;
import com.main.mart.dto.OwnerTOs;
import com.main.mart.ejb.OwnerEJBIf;
import com.main.mart.entity.Owner;
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
public class OwnerImpl implements OwnerIf{
	ResponseBuilder builder;
	@EJB
	private OwnerEJBIf ownerEJBIf;
	
	@Override
	public OwnerTOs getOwners(String shortName, String fullName, String phone) {
		OwnerTOs ownerTOs = new OwnerTOs();
		try {
			OwnerTO filter = new OwnerTO();
			filter.setShortName(shortName);
			filter.setFullName(fullName);
			filter.setPhone(phone);
			Collection<Owner> colOwners = ownerEJBIf.getAllOwnwes(filter);
			Collection<OwnerTO> owners = new ArrayList<OwnerTO>();
			if(colOwners != null && !colOwners.isEmpty()) {
				for(Owner owner : colOwners) {
					OwnerTO ownerTO = this.transformOwnerTOfromEntity(owner);
					if(ownerTO !=  null) {
						owners.add(ownerTO);
					}
				}				
			}
			ownerTOs.setDraw("1");
			ownerTOs.setOwners(owners);
			ownerTOs.setRecordsFiltered(owners.size()+"");
			ownerTOs.setRecordsTotal(owners.size()+"");
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return ownerTOs;
	}

	@Override
	public OwnerTO getOwnerById(Integer id) {
		try {
			Owner owner = ownerEJBIf.getOwnerById(id);
			return this.transformOwnerTOfromEntity(owner);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}return null;
	}

	@Override
	public Response addOwner(OwnerTO ownerTO) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(ownerTO != null) {
				Owner owner = new Owner();
				if(!StringUtils.isNullOrEmpty(ownerTO.getId())) {
					owner = ownerEJBIf.getOwnerById(Integer.parseInt(ownerTO.getId()));
				}else if(!StringUtils.isNullOrEmpty(ownerTO.getCreatedBy())) {
					User createdBy = new User();
					createdBy.setId(Integer.parseInt(ownerTO.getCreatedBy()));
					owner.setCreatedBy(createdBy);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getAddress())) {
					owner.setAddress(ownerTO.getAddress());
				}else {
					owner.setAddress(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getCity())) {
					owner.setCity(ownerTO.getCity());
				}else {
					owner.setCity(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getClosingTime())) {
					owner.setClosingTime(ownerTO.getClosingTime());
				}else {
					owner.setClosingTime(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getEmail())) {
					owner.setEmail(ownerTO.getEmail());
				}else {
					owner.setEmail(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getFriday())) {
					owner.setFriday(CommonEnum.valueOf(ownerTO.getFriday()));
				}else {
					owner.setFriday(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getFullName())) {
					owner.setFullName(ownerTO.getFullName());
				}else {
					owner.setFullName(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getUpdatedBy())) {
					User lastUpdatedBy = new User();
					lastUpdatedBy.setId(Integer.parseInt(ownerTO.getUpdatedBy()));
					owner.setLastUpdatedBy(lastUpdatedBy);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getMonday())) {
					owner.setMonday(CommonEnum.valueOf(ownerTO.getMonday()));
				}else {
					owner.setMonday(CommonEnum.N);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getOpeningTime())) {
					owner.setOpeningTime(ownerTO.getOpeningTime());
				}else {
					owner.setOpeningTime(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getPhone())) {
					owner.setPhone(ownerTO.getPhone());
				}else {
					owner.setPhone(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getSaturday())) {
					owner.setSaturday(CommonEnum.valueOf(ownerTO.getSaturday()));
				}else {
					owner.setSaturday(CommonEnum.N);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getShortName())) {
					owner.setShortName(ownerTO.getShortName());
				}else {
					owner.setShortName(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getState())) {
					owner.setState(ownerTO.getState());
				}else {
					owner.setState(null);
				}
				owner.setStatus(StatusEnum.A);
				if(!StringUtils.isNullOrEmpty(ownerTO.getSunday())) {
					owner.setSunday(CommonEnum.valueOf(ownerTO.getSunday()));
				}else {
					owner.setSunday(CommonEnum.N);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getThursday())) {
					owner.setThursday(CommonEnum.valueOf(ownerTO.getThursday()));
				}else {
					owner.setThursday(CommonEnum.N);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getTuesday())) {
					owner.setTuesday(CommonEnum.valueOf(ownerTO.getTuesday()));
				}else {
					owner.setTuesday(CommonEnum.N);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getTypeId())) {
					TypeItems typeId = new TypeItems();
					typeId.setId(Integer.parseInt(ownerTO.getTypeId()));
					owner.setTypeId(typeId);
				}else {
					owner.setTypeId(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getWednesday())) {
					owner.setWednesday(CommonEnum.valueOf(ownerTO.getWednesday()));
				}else {
					owner.setWednesday(CommonEnum.N);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getwPhone())) {
					owner.setwPhone(ownerTO.getwPhone());
				}else {
					owner.setwPhone(null);
				}
				if(!StringUtils.isNullOrEmpty(ownerTO.getZip())) {
					owner.setZip(ownerTO.getZip());
				}else {
					owner.setZip(null);
				}
				ResponseStatus responseStatus = ownerEJBIf.addUpdateOwner(owner);
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
	public Response deleteOwner(Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			ResponseStatus responseStatus = ownerEJBIf.deleteOwner(id);
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
	
	private OwnerTO transformOwnerTOfromEntity(Owner owner) {
		try {
			if(owner != null) {
				OwnerTO ownerTO = new OwnerTO();
				ownerTO.setId(String.valueOf(owner.getId()));
				ownerTO.setAddress(ownerTO.getAddress());
				ownerTO.setCity(ownerTO.getCity());
				ownerTO.setClosingTime(ownerTO.getClosingTime());
				if(owner.getCreatedBy() != null) {
					ownerTO.setCreatedBy(String.valueOf(owner.getCreatedBy().getId()));
				}
				ownerTO.setCreatedDateTime(MartUtilities.cnvtDBDateTimeToUIDateTime(owner.getCreatedDateTime()));
				ownerTO.setEmail(owner.getEmail());
				ownerTO.setFriday(String.valueOf(owner.getFriday()));
				ownerTO.setFullName(owner.getFullName());
				ownerTO.setMonday(String.valueOf(owner.getMonday()));
				ownerTO.setOpeningTime(owner.getOpeningTime());
				ownerTO.setPhone(owner.getPhone());
				ownerTO.setSaturday(String.valueOf(owner.getSaturday()));
				ownerTO.setShortName(owner.getShortName());
				ownerTO.setState(owner.getState());
				ownerTO.setStatus(String.valueOf(ownerTO.getStatus()));
				ownerTO.setSunday(String.valueOf(owner.getSunday()));
				ownerTO.setThursday(String.valueOf(owner.getThursday()));
				ownerTO.setTuesday(String.valueOf(owner.getTuesday()));
				if(owner.getTypeId() != null) {
					ownerTO.setTypeId(String.valueOf(owner.getTypeId().getId()));
				}
				if(owner.getLastUpdatedBy() != null) {
					ownerTO.setUpdatedBy(String.valueOf(owner.getLastUpdatedBy().getId()));
					ownerTO.setUpdatedByName(MartUtilities.getUserFullName(owner.getLastUpdatedBy()));
					ownerTO.setUpdatedDateTime(MartUtilities.cnvtDBDateTimeToUIDateTime(owner.getLastUpdatedDateTime()));
				}
				ownerTO.setWednesday(String.valueOf(owner.getWednesday()));
				ownerTO.setwPhone(owner.getwPhone());
				ownerTO.setZip(owner.getZip());
				return ownerTO;
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}return null;
	}
}
