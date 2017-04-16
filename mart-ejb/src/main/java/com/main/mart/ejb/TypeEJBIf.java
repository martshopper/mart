/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import com.main.mart.common.dto.TypeTO;
import com.main.mart.entity.Type;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface TypeEJBIf {
	public ResponseStatus addType(Type type);
	public ResponseStatus updateType(Type type);
	public Type getTypeById(Integer id);
	public Collection<Type> getTypes(TypeTO typeTO);
	public ResponseStatus deleteType(Integer id);
}
