/**
 * 
 */
package com.mart.ejb;

import com.mart.entity.StockStatus;
import com.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface StockStatusEJBIf {
	
	public ResponseStatus addStockStatus(StockStatus stockStatus);
}
