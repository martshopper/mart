/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.ChargeSlip;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ChargeSlipEJBIf {
	
	public ResponseStatus addChargeSlip(ChargeSlip chargeSlip);
}
