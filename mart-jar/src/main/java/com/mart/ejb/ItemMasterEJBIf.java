/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.ItemMaster;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ItemMasterEJBIf {
	
	public ResponseStatus addItemMaster(ItemMaster itemMaster);
}
