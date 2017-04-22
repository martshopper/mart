/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.ChargeSlip;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class ChargeSlipEJBImpl implements ChargeSlipEJBIf {
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addChargeSlip(ChargeSlip chargeSlip) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(chargeSlip != null) {
				em.persist(chargeSlip);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(chargeSlip.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showLog(e);
		}
		return responseStatus;
	}

}
