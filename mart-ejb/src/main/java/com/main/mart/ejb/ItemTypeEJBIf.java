/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.ItemType;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ItemTypeEJBIf {
	
	public ResponseStatus addItemType(ItemType itemType);
}
