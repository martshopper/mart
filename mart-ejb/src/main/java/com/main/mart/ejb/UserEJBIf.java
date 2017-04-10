/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.User;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface UserEJBIf {
	
	public ResponseStatus addUser(User user);
}
