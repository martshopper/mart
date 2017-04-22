/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.ItemType;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class ItemTypeEJBImpl implements ItemTypeEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addItemType(ItemType itemType) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(itemType != null) {
				em.persist(itemType);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(itemType.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showLog(e);
		}
		return responseStatus;
	}

}
