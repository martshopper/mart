/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.security.auth.login.LoginException;

import com.main.mart.common.dto.UserTO;
import com.main.mart.entity.User;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface UserEJBIf {
	
	public ResponseStatus addUpdateUser(User user);
	public User getUserById(Integer id);
	public Collection<User> getAllUsers(UserTO userTO);
	public ResponseStatus deleteUser(Integer id);
	
	public User checkUserAndPassword(String username, String password) throws LoginException;
}
