/**
 * 
 */
package com.main.mart.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Hashtable;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import com.main.mart.ejb.UserEJBIf;
import com.main.mart.entity.User;

@Stateless
public class MartLoginModule extends UsernamePasswordLoginModule{

	private SecurityPrincipal sp = null;
	Subject sub ;


	@Override
	protected boolean validatePassword(String username, String password)
	{	Principal p = this.getIdentity();

	try{		
System.out.println("Trying to login");
		sub = new Subject();
		if(password==null){
			password = username;
			username = p.getName();
		}

		if(p instanceof SecurityPrincipal) {					  

			try {
				sp = (SecurityPrincipal)p;
				sp.setSubj(sub);	
				sp.setColRole(null);

				sp.setUsername(username);
				sp.setPassword(password);
				return isValidUser(username, password);
			}catch(Exception e) {
				e.printStackTrace();
				return false;					  		  
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		return false;
		
	}
	return false;
	}

	@Override
	protected String getUsersPassword() throws LoginException {
		return null;
	}

	@Override
	protected Group[] getRoleSets() throws LoginException {
		try {			
			Group callerPrincipal = new SimpleGroup("CallerPrincipal");
			Group roles = new SimpleGroup("Roles");
			Group[] groups = {roles,callerPrincipal};	        
			roles.addMember(new SecurityPrincipal("SecurityAdmin"));
			callerPrincipal.addMember(sp);
			return groups;
		}
		catch(Exception e) {
			throw new LoginException(e.getMessage());
		}

	}


	public Boolean isValidUser(String username, String password) throws LoginException  {
		Boolean status = false;		
		try{
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			UserEJBIf lif = (UserEJBIf) context.lookup("java:app/mart-ejb-1.0-SNAPSHOT/UserEJBImpl");
			password = hashPassword(password);
			User user = lif.checkUserAndPassword(username, password);
			if(user != null && user.getUserName() != null){
				status = true;
			}
		}catch(Exception e){
			return status;
		}
		return status;
	}

	private String hashPassword(String password) throws LoginException {
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");            
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		}catch (Exception e) {
			throw new LoginException(e.getMessage());
		}	        
		return hashword;
	}

}
