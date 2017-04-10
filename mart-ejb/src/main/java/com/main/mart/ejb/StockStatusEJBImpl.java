/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.StockStatus;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
@Stateless
public class StockStatusEJBImpl implements StockStatusEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addStockStatus(StockStatus stockStatus) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(stockStatus != null) {
				em.persist(stockStatus);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(stockStatus.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

}
