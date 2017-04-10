/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.Grn;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface GrnEJBIf {
	
	public ResponseStatus addGrn(Grn grn);
}
