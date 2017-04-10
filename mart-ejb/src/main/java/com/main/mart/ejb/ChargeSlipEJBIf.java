/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.ChargeSlip;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface ChargeSlipEJBIf {
	
	public ResponseStatus addChargeSlip(ChargeSlip chargeSlip);
}
