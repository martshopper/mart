/**
 * 
 */
package com.main.mart.ejb;
import com.main.mart.common.dto.SeriesKeyTO;

/**
 * @author Hitesh
 *
 */
public interface InventoryCommonEJBIf {
	/**
	 * Generating Series By Module Code
	 * @param moduleCode
	 * @return
	 */
	public SeriesKeyTO getInventorySeriesNoByEntityName(String entityName);
	
	
}
