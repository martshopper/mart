/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import com.main.mart.common.dto.ItemMasterTO;
import com.main.mart.entity.ItemMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ItemMasterEJBIf {	
	public ResponseStatus addUpdateItemMaster(ItemMaster itemMaster);
	public Collection<ItemMaster> getItemMaster(ItemMasterTO itemMaster);
	public ItemMaster getItemMasterById(Integer id);
	public ResponseStatus deleteItemMaster(Integer id);
}
