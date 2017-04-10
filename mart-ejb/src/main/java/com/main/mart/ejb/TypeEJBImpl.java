/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.Type;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TypeEJBImpl implements TypeEJBIf {
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	/* (non-Javadoc)
	 * @see com.main.mart.ejb.TypeEJBIf#addType(com.main.mart.entity.Type)
	 */
	@Override
	public ResponseStatus addType(Type type) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(type != null) {
				em.persist(type);
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

	/* (non-Javadoc)
	 * @see com.main.mart.ejb.TypeEJBIf#updateType(com.main.mart.entity.Type)
	 */
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

	/* (non-Javadoc)
	 * @see com.main.mart.ejb.TypeEJBIf#getTypeById(java.lang.Integer)
	 */
	@Override
	public Type getTypeById(Integer id) {
		try {
			return em.find(Type.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		} return null;
	}

}
