/**
 * 
 */
package com.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mart.entity.User;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class UserEJBImpl implements UserEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addUser(User user) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(user != null) {
				em.persist(user);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(user.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

}
