/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.TaxMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface TaxMasterEJBIf {
	
	public ResponseStatus addTaxMaster(TaxMaster taxMaster);
}
