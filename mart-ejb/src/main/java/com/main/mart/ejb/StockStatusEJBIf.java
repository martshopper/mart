/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.StockStatus;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface StockStatusEJBIf {
	
	public ResponseStatus addStockStatus(StockStatus stockStatus);
}
