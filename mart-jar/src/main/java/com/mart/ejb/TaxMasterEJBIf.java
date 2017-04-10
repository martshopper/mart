/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.TaxMaster;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface TaxMasterEJBIf {
	
	public ResponseStatus addTaxMaster(TaxMaster taxMaster);
}
