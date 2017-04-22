/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import com.main.mart.common.dto.OwnerTO;
import com.main.mart.entity.Owner;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface OwnerEJBIf {
	public Collection<Owner> getAllOwnwes(OwnerTO ownerTO);
	public ResponseStatus addUpdateOwner(Owner owner);
	public Owner getOwnerById(Integer id);
	public ResponseStatus deleteOwner(Integer id);
}
