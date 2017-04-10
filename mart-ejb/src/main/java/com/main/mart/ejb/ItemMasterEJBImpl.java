/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.ItemMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class ItemMasterEJBImpl implements ItemMasterEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addItemMaster(ItemMaster itemMaster) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(itemMaster != null) {
				em.persist(itemMaster);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(itemMaster.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

}
