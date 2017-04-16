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
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;

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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return responseStatus;
	}

	@Override
	public Type getTypeById(Integer id) {
		try {
			return em.find(Type.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		} return null;
	}

	@Override
	public Collection<Type> getTypes(TypeTO typeTO) {
		Collection<Type> types = null;
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT t FROM Type t WHERE t.status=:status");
			if(typeTO.getTypeCode() != null) {
				hqlBuilder.append(" AND t.typeCode LIKE :code");
			}
			if(typeTO.getTypeDescription() != null) {
				hqlBuilder.append(" AND t.typeDescription LIKE :desc");
			}
			TypedQuery<Type> typedQuery = em.createQuery(hqlBuilder.toString(), Type.class);
			typedQuery.setParameter("status", StatusEnum.A);
			if(typeTO.getTypeCode() != null) {
				typedQuery.setParameter("code", typeTO.getTypeCode().concat("%"));
			}
			if(typeTO.getTypeDescription() != null) {
				typedQuery.setParameter("desc", typeTO.getTypeDescription().concat("%"));
			}
			types = typedQuery.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return responseStatus;
	}

}
