/**
 * 
 */
package com.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mart.entity.StoreMaster;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class StoreMasterEJBImpl implements StoreMasterEJBIf{
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addStoreMaster(StoreMaster storeMaster) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(storeMaster != null) {
				em.persist(storeMaster);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(storeMaster.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

}
