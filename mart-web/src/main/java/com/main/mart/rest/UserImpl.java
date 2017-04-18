/**
 * 
 */
package com.main.mart.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.main.mart.common.dto.UserTO;
import com.main.mart.common.dto.UserTOs;
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
					user.setDob(null);//TODO
				}else {
					user.setDob(null);
				}
				if(!StringUtils.isNullOrEmpty(userTO.getEmail())) {
					user.setEmail(userTO.getEmail());
				}
				if(!StringUtils.isNullOrEmpty(userTO.getEndDate())) {
					user.setEndDate(null);
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
					user.setJoinDate(null);
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
				user.setStatusEnum(StatusEnum.A);
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
		}
		return builder.build();
	}

	@Override
	public UserTOs getAllUsers(String username, String firstName, String lastName, String middleName, String phone,
			String dob, String sex, String level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTO getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteType(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private UserTO transformUserEntityToUserTO(User user) {
		try {
			UserTO userTO = new UserTO();
		}catch (Exception e) {
			e.printStackTrace();
		}return null;
	}
}
