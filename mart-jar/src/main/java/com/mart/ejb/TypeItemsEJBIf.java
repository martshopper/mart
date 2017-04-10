/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.TypeItems;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface TypeItemsEJBIf {
	public ResponseStatus addTypeItems(TypeItems typeItems);
	public ResponseStatus updateTypeItems(TypeItems typeItems);
	public TypeItems getTypeItemsById(Integer id);
}
