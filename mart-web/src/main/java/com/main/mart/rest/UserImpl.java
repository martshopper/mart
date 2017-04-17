/**
 * 
 */
package com.main.mart.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.main.mart.common.dto.UserTO;
import com.main.mart.common.dto.UserTOs;
import com.main.mart.ejb.UserEJBIf;
import com.main.mart.entity.User;
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
		try {
			if(userTO != null) {
				User user = new User();
				if(!StringUtils.isNullOrEmpty(userTO.getId())) {
					user = userEJBIf.getUserById(Integer.parseInt(userTO.getId()));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

}
