/**
 * 
 */
package com.main.mart.ejb;

import java.util.List;

import com.main.mart.common.dto.StoreMasterTO;
import com.main.mart.entity.StoreMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface StoreMasterEJBIf {	
	public ResponseStatus addStoreMaster(StoreMaster storeMaster);
	public List<StoreMaster> getStores(StoreMasterTO storeMaster);
	public StoreMaster getStoreMasterById(Integer id);
	public ResponseStatus deleteStoreMaster(Integer id);
}
