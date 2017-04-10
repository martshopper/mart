/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.StoreMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface StoreMasterEJBIf {
	
	public ResponseStatus addStoreMaster(StoreMaster storeMaster);
}
