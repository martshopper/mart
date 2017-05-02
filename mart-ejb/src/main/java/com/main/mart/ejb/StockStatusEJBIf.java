/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import com.main.mart.common.dto.StockStatusTO;
import com.main.mart.entity.StockStatus;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface StockStatusEJBIf {
	
	public ResponseStatus addStockStatus(StockStatus stockStatus);
	
	public Collection<StockStatus> getStockStatus(StockStatusTO stockStatusTO);
	/**
	 * Filtering StockStatus for GrnDetails
	 * @param stockStatusTO
	 * @return StockStatus
	 */
	public StockStatus getStockStatusByGrnDetails(StockStatusTO stockStatusTO);
	
	/**
	 * Merge StockStatus Entity
	 * @param stockStatus
	 * @return ResponseStatusTO
	 */
	public ResponseStatus updateStockStatus(StockStatus stockStatus);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public StockStatus getStockStatusbyId(Integer id);
}
