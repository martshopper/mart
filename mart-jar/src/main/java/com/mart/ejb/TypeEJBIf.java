/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.Type;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface TypeEJBIf {
	public ResponseStatus addType(Type type);
	public ResponseStatus updateType(Type type);
	public Type getTypeById(Integer id);
}
