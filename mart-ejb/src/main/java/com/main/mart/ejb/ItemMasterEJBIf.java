/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.ItemMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ItemMasterEJBIf {
	
	public ResponseStatus addItemMaster(ItemMaster itemMaster);
}
