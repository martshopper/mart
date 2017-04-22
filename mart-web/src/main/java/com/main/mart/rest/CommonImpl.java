/**
 * 
 */
package com.main.mart.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Stateless
public class CommonImpl implements CommonIf {
	
	ResponseBuilder builder;
	
	
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
	
	
}
