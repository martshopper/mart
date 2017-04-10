/**
 * 
 */
package com.main.mart.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.main.mart.dto.UserTO;
import com.main.mart.ejb.UserEJBIf;
import com.main.mart.entity.User;

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
