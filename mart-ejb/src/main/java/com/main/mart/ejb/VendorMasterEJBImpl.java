/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.VendorMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class VendorMasterEJBImpl implements VendorMasterEJBIf {
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addVendorMaster(VendorMaster vendorMaster) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(vendorMaster != null) {
				em.persist(vendorMaster);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(vendorMaster.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

}
