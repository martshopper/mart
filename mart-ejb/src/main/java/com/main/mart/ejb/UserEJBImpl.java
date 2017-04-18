/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.UserTO;
import com.main.mart.entity.User;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;

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
					user.setUpdatedCount(user.getUpdatedCount()+1);
					user.setLastUpdatedDateTime(MartUtilities.getCurrentDateTime());
					em.merge(user);
					em.flush();
				}else {
					user.setUpdatedCount(0);
					user.setCreatedDateTime(MartUtilities.getCurrentDateTime());
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
		try {
			return em.find(User.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<User> getAllUsers(UserTO userTO) {
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT u FROM User u WHERE u.status=:status");
			TypedQuery<User> typedQuery = em.createQuery(hqlBuilder.toString(), User.class);
			typedQuery.setParameter("status", StatusEnum.A);
			return typedQuery.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseStatus deleteUser(Integer id) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			User user = em.find(User.class, id);
			 em.remove(user);
			 em.flush();
			 responseStatus.setStatus(true);
		}catch (Exception e) {
			e.printStackTrace();
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return responseStatus;
	}

}
