/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.auth.login.LoginException;

import com.main.mart.common.dto.UserTO;
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
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}

	@Override
	public User getUserById(Integer id) {
		try {
			return em.find(User.class, id);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}

	@Override
	public Collection<User> getAllUsers(UserTO userTO) {
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT u FROM User u WHERE u.status=:status");
			if(!StringUtils.isNullOrEmpty(userTO.getUsername())){
				hqlBuilder.append(" AND u.userName LIKE :username");
			}
			if(!StringUtils.isNullOrEmpty(userTO.getFirstName())){
				hqlBuilder.append(" AND u.firstName LIKE :firstName");
			}
			if(!StringUtils.isNullOrEmpty(userTO.getLastName())){
				hqlBuilder.append(" AND u.lastName LIKE :lastName");
			}
			if(!StringUtils.isNullOrEmpty(userTO.getMiddleName())){
				hqlBuilder.append(" AND u.middleName LIKE :middleName");
			}
			if(!StringUtils.isNullOrEmpty(userTO.getPhone())){
				hqlBuilder.append(" AND u.phone LIKE :phone");
			}
			if(!StringUtils.isNullOrEmpty(userTO.getDob())){
				hqlBuilder.append(" AND u.dob = :dob");
			}
			if(!StringUtils.isNullOrEmpty(userTO.getSex())){
				hqlBuilder.append(" AND u.sex = :sex");
			}
			if(!StringUtils.isNullOrEmpty(userTO.getLevel())){
				hqlBuilder.append(" AND u.level.id = :level");
			}
			TypedQuery<User> typedQuery = em.createQuery(hqlBuilder.toString(), User.class);
			typedQuery.setParameter("status", StatusEnum.A);
			if(!StringUtils.isNullOrEmpty(userTO.getUsername())){
				typedQuery.setParameter("username", userTO.getUsername().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(userTO.getFirstName())){
				typedQuery.setParameter("firstName", userTO.getFirstName().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(userTO.getLastName())){
				typedQuery.setParameter("lastName", userTO.getLastName().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(userTO.getMiddleName())){
				typedQuery.setParameter("middleName", userTO.getMiddleName().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(userTO.getPhone())){
				typedQuery.setParameter("phone", userTO.getPhone().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(userTO.getDob())){
				typedQuery.setParameter("dob", MartUtilities.cnvtUIStringDateToDate(userTO.getDob()));
			}
			if(!StringUtils.isNullOrEmpty(userTO.getSex())){
				typedQuery.setParameter("sex", SexEnum.valueOf(userTO.getSex()));
			}
			if(!StringUtils.isNullOrEmpty(userTO.getLevel())){
				typedQuery.setParameter("level", Integer.parseInt(userTO.getLevel()));
			}
			return typedQuery.getResultList();
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
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
			MartUtilities.showErrorLog(e);
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return responseStatus;
	}
	
	@Override
	public User checkUserAndPassword(String username, String password) throws LoginException {
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT u FROM User u WHERE u.status=:status");
			if(!StringUtils.isNullOrEmpty(username)){
				hqlBuilder.append(" AND u.userName = :username");
			}
			if(!StringUtils.isNullOrEmpty(password)){
				hqlBuilder.append(" AND u.password = :password");
			}
			TypedQuery<User> typedQuery = em.createQuery(hqlBuilder.toString(), User.class);
			typedQuery.setParameter("status", StatusEnum.A);
			if(!StringUtils.isNullOrEmpty(username)){
				typedQuery.setParameter("username", username);
			}
			if(!StringUtils.isNullOrEmpty(password)){
				typedQuery.setParameter("password", password);
			}
			typedQuery.setParameter("status", StatusEnum.A);
			return typedQuery.getSingleResult();
		}catch (Exception e) {
			throw new LoginException(e.getMessage()); 
		}
	}

}
