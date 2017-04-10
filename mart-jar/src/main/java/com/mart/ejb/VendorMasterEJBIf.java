/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.VendorMaster;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface VendorMasterEJBIf {
	
	public ResponseStatus addVendorMaster(VendorMaster vendorMaster);
}
