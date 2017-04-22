/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import com.main.mart.common.dto.ItemTypeTO;
import com.main.mart.entity.ItemType;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ItemTypeEJBIf {	
	public ResponseStatus addUpdateItemType(ItemType itemType);
	public Collection<ItemType> getItemTypes(ItemTypeTO itemType);
	public ItemType getItemTypeById(Integer id);
	public ResponseStatus deleteItemType(Integer id);
}
