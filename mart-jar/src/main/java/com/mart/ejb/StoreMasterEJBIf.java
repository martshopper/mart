/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.StoreMaster;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface StoreMasterEJBIf {
	
	public ResponseStatus addStoreMaster(StoreMaster storeMaster);
}
