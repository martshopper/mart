/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.VendorMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface VendorMasterEJBIf {
	
	public ResponseStatus addVendorMaster(VendorMaster vendorMaster);
}
