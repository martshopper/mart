/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.User;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface UserEJBIf {
	
	public ResponseStatus addUser(User user);
}
