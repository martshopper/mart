/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.StoreMaster;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;

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
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}

}
