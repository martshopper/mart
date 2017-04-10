/**
 * 
 */
package com.main.mart.ejb;

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
}
