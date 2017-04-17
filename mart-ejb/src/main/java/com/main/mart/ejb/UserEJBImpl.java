/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.User;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class UserEJBImpl implements UserEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addUpdateUser(User user) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(user != null) {
				if(user.getId() != null) {
					em.merge(user);
					em.flush();
				}else {
					em.persist(user);
					em.flush();
				}
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

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
