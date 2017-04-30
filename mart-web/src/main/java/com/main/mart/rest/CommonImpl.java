/**
 * 
 */
package com.main.mart.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.main.mart.common.dto.UserTO;
import com.main.mart.ejb.UserEJBIf;
import com.main.mart.entity.User;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.StringUtils;


@Stateless
public class CommonImpl implements CommonIf {
	
	ResponseBuilder builder;
	@EJB
	private UserEJBIf userEJBIf;
	
	
	@Override
	public Response logoutUser(HttpServletRequest req) {
		Map<String, String> response = new HashMap<String, String>();
		try{
			HttpSession session = req.getSession();
			session.invalidate();
			response.put("Succes", "Session Destroyed");
			builder = Response.ok(response);
			
		}catch(Exception e){
			e.printStackTrace();
			response.put("Error", e.getMessage());
			builder = Response.status(400).entity(response);
		}
		return builder.build();
	}


	@Override
	public UserTO getLoginUser() {
		try {
			User user = userEJBIf.getUserById(MartUtilities.getUserId());
			if(user != null) {
				UserTO userTO = new UserTO();
				userTO.setId(user.getId().toString());
				if(!StringUtils.isNullOrEmpty(user.getEmail())) {
					userTO.setEmail(user.getEmail());
				}
				userTO.setUserFullName(MartUtilities.getUserFullName(user));
				if(!StringUtils.isNullOrEmpty(user.getUserName())) {
					userTO.setUsername(user.getUserName());
				}
				if(!StringUtils.isNullOrEmpty(user.getFirstName())) {
					userTO.setFirstName(user.getFirstName());
				}
				if(!StringUtils.isNullOrEmpty(user.getLastName())) {
					userTO.setLastName(user.getLastName());
				}
				if(!StringUtils.isNullOrEmpty(user.getMiddleName())) {
					userTO.setMiddleName(user.getMiddleName());
				}
				if(user.getDob() != null) {
					userTO.setDob(MartUtilities.cnvtDBDateToUIDate(user.getDob()));
				}
				if(user.getLevel() != null) {
					userTO.setLevel(user.getLevel().getId().toString());
				}
				if(user.getSex() != null) {
					userTO.setSex(user.getSex().toString());
				}				
				return userTO;			
			}
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}
	
	
}
