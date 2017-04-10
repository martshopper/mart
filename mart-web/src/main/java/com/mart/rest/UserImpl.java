/**
 * 
 */
package com.mart.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.mart.dto.UserTO;
import com.mart.ejb.UserEJBIf;
import com.mart.entity.User;

/**
 * @author Hitesh
 *
 */
@Stateless
public class UserImpl implements UserIf {

	@EJB
	private UserEJBIf userEJBIf;
	
	@Override
	public Response addUser(UserTO userTO) {
		userEJBIf.addUser(new User());
		return null;
	}

}
