/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.ItemType;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ItemTypeEJBIf {
	
	public ResponseStatus addItemType(ItemType itemType);
}
