/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.TypeItems;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface TypeItemsEJBIf {
	public ResponseStatus addTypeItems(TypeItems typeItems);
	public ResponseStatus updateTypeItems(TypeItems typeItems);
	public TypeItems getTypeItemsById(Integer id);
}
