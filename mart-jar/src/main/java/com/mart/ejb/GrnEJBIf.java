/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.Grn;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface GrnEJBIf {
	
	public ResponseStatus addGrn(Grn grn);
}
