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

import com.main.mart.common.dto.UserTO;
import com.main.mart.dto.UserTOs;
import com.main.mart.ejb.UserEJBIf;
import com.main.mart.entity.TypeItems;
import com.main.mart.entity.User;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.SexEnum;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class UserImpl implements UserIf {
	
	ResponseBuilder builder;
	@EJB
	private UserEJBIf userEJBIf;
	
	@Override
	public Response addUser(UserTO userTO) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			if(userTO != null) {
				User user = new User();
				if(!StringUtils.isNullOrEmpty(userTO.getId())) {
					user = userEJBIf.getUserById(Integer.parseInt(userTO.getId()));
				}
				if(!StringUtils.isNullOrEmpty(userTO.getAddress())) {
					user.setAddress(userTO.getAddress());
				}else {
					user.setAddress(null);
				}
				if(!StringUtils.isNullOrEmpty(userTO.getCity())) {
					user.setCity(userTO.getCity());
				}else {
					user.setCity(null);
				}
				if(!StringUtils.isNullOrEmpty(userTO.getDob())) {
					user.setDob(MartUtilities.cnvtUIStringDateToDate(userTO.getDob()));
				}else {
					user.setDob(null);
				}
				if(!StringUtils.isNullOrEmpty(userTO.getEmail())) {
					user.setEmail(userTO.getEmail());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getEndDate())) {
					user.setEndDate(MartUtilities.cnvtUIStringDateToDate(userTO.getEndDate()));
				}
				if(!StringUtils.isNullOrEmpty(userTO.getFax())) {
					user.setFax(userTO.getFax());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getFirstName())) {
					user.setFirstName(userTO.getFirstName());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getHomePhone())) {
					user.setHphone(userTO.getHomePhone());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getJoinDate())) {
					user.setJoinDate(MartUtilities.cnvtUIStringDateToDate(userTO.getJoinDate()));
				}
				if(!StringUtils.isNullOrEmpty(userTO.getLastName())) {
					user.setLastName(userTO.getLastName());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getLevel())) {
					TypeItems level = new TypeItems();
					level.setId(Integer.parseInt(userTO.getLevel()));
					user.setLevel(level);
				}
				if(!StringUtils.isNullOrEmpty(userTO.getMiddleName())) {
					user.setMiddleName(userTO.getMiddleName());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getPassword())) {
					user.setPassword(MartUtilities.encrypt(userTO.getPassword()));
				}
				if(!StringUtils.isNullOrEmpty(userTO.getPhone())) {
					user.setPhone(userTO.getPhone());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getSex())) {
					user.setSex(SexEnum.valueOf(userTO.getSex()));
				}
				if(!StringUtils.isNullOrEmpty(userTO.getSsn())) {
					user.setSsn(userTO.getSsn());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getState())) {
					user.setState(userTO.getState());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getUsername())) {
					user.setUserName(userTO.getUsername());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getWorkPhone())) {
					user.setWphone(userTO.getWorkPhone());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getZip())) {
					user.setZip(userTO.getZip());
				}
				user.setStatus(StatusEnum.A);
				ResponseStatus responseStatus = userEJBIf.addUpdateUser(user);
				if(responseStatus.getStatus()) {
					response.put("id", ""+responseStatus.getPersistingId());
					builder = Response.ok(response);
				}else {
					response.put("exception", responseStatus.getErrorMessage());
					builder = Response.status(400).entity(response);
				}
			}
		}catch (Exception e) {
			response.put("exception", e.getMessage());
			builder = Response.status(400).entity(response);
			MartUtilities.showErrorLog(e);
		}
		return builder.build();
	}

	@Override
	public UserTOs getAllUsers(String username, String firstName, String lastName, String middleName, String phone,
			String dob, String sex, String level) {
		UserTOs userTOs = new UserTOs();
		try {
			UserTO userTO = new UserTO();
			userTO.setUsername(username);
			userTO.setFirstName(firstName);
			userTO.setLastName(lastName);
			userTO.setMiddleName(middleName);
			userTO.setPhone(phone);
			userTO.setDob(dob);
			userTO.setSex(sex);
			userTO.setLevel(level);
			Collection<UserTO> colUsers = new ArrayList<UserTO>();
			Collection<User> users = userEJBIf.getAllUsers(userTO);
			if(users != null && !users.isEmpty()) {
				for(User user : users) {
					UserTO userTo = this.transformUserEntityToUserTO(user);
					if(userTo != null) {
						colUsers.add(userTo);
					}
				}
			}
			userTOs.setDraw("1");
			userTOs.setRecordsFiltered(colUsers.size()+"");
			userTOs.setRecordsTotal(colUsers.size()+"");
			userTOs.setUserTOs(colUsers);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return userTOs;
	}

	@Override
	public UserTO getUserById(Integer id) {
		try {
			User user = userEJBIf.getUserById(id);
			return this.transformUserEntityToUserTO(user);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}

	@Override
	public Response deleteType(Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			ResponseStatus responseStatus = userEJBIf.deleteUser(id);
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
	
	private UserTO transformUserEntityToUserTO(User user) {
		try {
			if(user != null) {
				UserTO userTO = new UserTO();
				userTO.setId(user.getId().toString());
				if(!StringUtils.isNullOrEmpty(user.getAddress())) {
					userTO.setAddress(user.getAddress());
				}
				if(!StringUtils.isNullOrEmpty(user.getCity())) {
					userTO.setCity(user.getCity());
				}
				if(!StringUtils.isNullOrEmpty(user.getEmail())) {
					userTO.setEmail(user.getEmail());
				}
				if(!StringUtils.isNullOrEmpty(user.getFax())) {
					userTO.setFax(user.getFax());
				}
				if(!StringUtils.isNullOrEmpty(user.getFirstName())) {
					userTO.setFirstName(user.getFirstName());
				}
				if(!StringUtils.isNullOrEmpty(user.getHphone())) {
					userTO.setHomePhone(user.getHphone());
				}
				if(!StringUtils.isNullOrEmpty(user.getLastName())) {
					userTO.setLastName(user.getLastName());
				}
				if(!StringUtils.isNullOrEmpty(user.getMiddleName())) {
					userTO.setMiddleName(user.getMiddleName());
				}
				if(!StringUtils.isNullOrEmpty(user.getPhone())) {
					userTO.setPhone(user.getPhone());
				}
				if(!StringUtils.isNullOrEmpty(user.getSsn())) {
					userTO.setSsn(user.getSsn());
				}
				if(!StringUtils.isNullOrEmpty(user.getState())) {
					userTO.setState(user.getState());
				}
				if(!StringUtils.isNullOrEmpty(user.getUserName())) {
					userTO.setUsername(user.getUserName());
				}
				if(!StringUtils.isNullOrEmpty(user.getWphone())) {
					userTO.setWorkPhone(user.getWphone());
				}
				if(!StringUtils.isNullOrEmpty(user.getZip())) {
					userTO.setZip(user.getZip());
				}
				if(user.getCreatedBy() != null) {
					userTO.setCreatedBy(user.getCreatedBy().getId().toString());
					userTO.setCreatedByName(MartUtilities.getUserFullName(user.getCreatedBy()));
				}
				if(user.getCreatedDateTime() != null) {
					userTO.setCreatedDateTime(MartUtilities.cnvtDBDateTimeToUIDateTime(user.getCreatedDateTime()));
				}
				if(user.getDob() != null) {
					userTO.setDob(MartUtilities.cnvtDBDateToUIDate(user.getDob()));
				}
				if(user.getEndDate() != null) {
					userTO.setEndDate(MartUtilities.cnvtDBDateToUIDate(user.getEndDate()));
				}
				if(user.getJoinDate() != null) {
					userTO.setJoinDate(MartUtilities.cnvtDBDateToUIDate(user.getJoinDate()));
				}
				if(user.getLevel() != null) {
					userTO.setLevel(user.getLevel().getId().toString());
				}
				if(user.getSex() != null) {
					userTO.setSex(user.getSex().toString());
				}
				if(user.getStatus() != null) {
					userTO.setStatus(user.getStatus().toString());
				}
				if(user.getLastUpdatedBy() != null) {
					userTO.setUpdatedBy(user.getLastUpdatedBy().getId().toString());
					userTO.setUpdatedByName(MartUtilities.getUserFullName(user.getLastUpdatedBy()));
				}
				if(user.getLastUpdatedDateTime() != null) {
					userTO.setUpdatedDateTime(MartUtilities.cnvtDBDateTimeToUIDateTime(user.getLastUpdatedDateTime()));
				}
				if(!StringUtils.isNullOrEmpty(user.getPassword())) {
					userTO.setPassword(MartUtilities.decrypt(user.getPassword()));
				}
				return userTO;
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}return null;
	}
}
