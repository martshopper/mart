/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.TypeTO;
import com.main.mart.entity.Type;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TypeEJBImpl implements TypeEJBIf {
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addType(Type type) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(type != null) {
				if(type.getId() != null) {
					em.merge(type);
					em.flush();
				}else {
					em.persist(type);
					em.flush();
				}
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(type.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus updateType(Type type) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(type != null) {
				em.merge(type);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(type.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}

	@Override
	public Type getTypeById(Integer id) {
		try {
			return em.find(Type.class, id);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		} return null;
	}

	@Override
	public Collection<Type> getTypes(TypeTO typeTO) {
		Collection<Type> types = null;
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT t FROM Type t WHERE t.status=:status");
			if(!StringUtils.isNullOrEmpty(typeTO.getTypeCode())) {
				hqlBuilder.append(" AND t.typeCode LIKE :code");
			}
			if(!StringUtils.isNullOrEmpty(typeTO.getTypeDescription())) {
				hqlBuilder.append(" AND t.typeDescription LIKE :desc");
			}
			TypedQuery<Type> typedQuery = em.createQuery(hqlBuilder.toString(), Type.class);
			typedQuery.setParameter("status", StatusEnum.A);
			if(!StringUtils.isNullOrEmpty(typeTO.getTypeCode())) {
				typedQuery.setParameter("code", typeTO.getTypeCode().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(typeTO.getTypeDescription())) {
				typedQuery.setParameter("desc", typeTO.getTypeDescription().concat("%"));
			}
			types = typedQuery.getResultList();
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return types;
	}

	@Override
	public ResponseStatus deleteType(Integer id) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			Type type = em.find(Type.class, id);
			 em.remove(type);
			 em.flush();
			 responseStatus.setStatus(true);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return responseStatus;
	}

}
