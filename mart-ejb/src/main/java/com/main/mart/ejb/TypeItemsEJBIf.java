/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import com.main.mart.common.dto.TypeItemsTO;
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
	public Collection<TypeItems> getAllTypeItems(TypeItemsTO itemsTO);
	public ResponseStatus deleteTypeItems(Integer id);
}
