/**
 * 
 */
package com.main.mart.ejb;

import com.main.mart.entity.SeriesMaster;
import com.main.mart.utilities.ResponseStatus;

/**
 * @author Hitesh
 *
 */
public interface SeriesMasterEJBIf {
	/**
	 * 
	 * @param seriesMaster
	 * @return
	 */
	public ResponseStatus addSeriesMaster(SeriesMaster seriesMaster);
	/**
	 * 
	 * @param seriesMaster
	 * @return
	 */
	public ResponseStatus updateSeriesMaster(SeriesMaster seriesMaster);
	
	/**
	 * Getting ServiceMaster by Id
	 * @param id
	 * @return
	 */
	public SeriesMaster getSeriesMasterById(Integer id);
	/**
	 * Reset Series key
	 * @param id
	 * @return
	 */
	public ResponseStatus updateSeriesMasterSequenceNoById(Integer id);
}
