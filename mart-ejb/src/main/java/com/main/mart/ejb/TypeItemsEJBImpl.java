/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.main.mart.entity.TypeItems;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TypeItemsEJBImpl implements TypeItemsEJBIf {
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.main.mart.ejb.TypeItemsEJBIf#addTypeItems(com.main.mart.entity.TypeItems)
	 */
	@Override
	public ResponseStatus addTypeItems(TypeItems typeItems) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(typeItems != null) {
				em.persist(typeItems);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(typeItems.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

	/* (non-Javadoc)
	 * @see com.main.mart.ejb.TypeItemsEJBIf#updateTypeItems(com.main.mart.entity.TypeItems)
	 */
	@Override
	public ResponseStatus updateTypeItems(TypeItems typeItems) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(typeItems != null) {
				em.persist(typeItems);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(typeItems.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

	/* (non-Javadoc)
	 * @see com.main.mart.ejb.TypeItemsEJBIf#getTypeItemsById(java.lang.Integer)
	 */
	@Override
	public TypeItems getTypeItemsById(Integer id) {
		try {
			return em.find(TypeItems.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		} return null;
	}

}