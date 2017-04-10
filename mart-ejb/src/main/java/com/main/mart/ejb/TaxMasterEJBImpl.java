/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.TaxMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TaxMasterEJBImpl implements TaxMasterEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addTaxMaster(TaxMaster taxMaster) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(taxMaster != null) {
				em.persist(taxMaster);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(taxMaster.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

}
