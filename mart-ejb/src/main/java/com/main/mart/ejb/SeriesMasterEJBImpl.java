/**
 * 
 */
package com.main.mart.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.main.mart.entity.SeriesMaster;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class SeriesMasterEJBImpl implements SeriesMasterEJBIf {
	@PersistenceContext(unitName ="martUnit")
	private EntityManager em;

	@Override
	public ResponseStatus addSeriesMaster(SeriesMaster seriesMaster) {
		ResponseStatus ResponseStatus = new ResponseStatus();
		try {
			if(seriesMaster != null) {
				em.persist(seriesMaster);
				em.flush();
				ResponseStatus.setStatus(true);
				ResponseStatus.setPersistingId(seriesMaster.getId());
			}
		}catch (Exception exception) {
			exception.printStackTrace();
			ResponseStatus.setStatus(false);
			ResponseStatus.setErrorMessage(exception.getMessage());
		}
		return ResponseStatus;
	}

	@Override
	public ResponseStatus updateSeriesMaster(SeriesMaster seriesMaster) {
		ResponseStatus ResponseStatus = new ResponseStatus();
		try {
			if(seriesMaster != null) {
				em.merge(seriesMaster);
				em.flush();
				ResponseStatus.setStatus(true);
				ResponseStatus.setPersistingId(seriesMaster.getId());
			}
		}catch (Exception exception) {
			exception.printStackTrace();
			ResponseStatus.setStatus(false);
			ResponseStatus.setErrorMessage(exception.getMessage());
		}
		return ResponseStatus;
	}

	@Override
	public SeriesMaster getSeriesMasterById(Integer id) {
		try {
			return em.find(SeriesMaster.class, id);
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseStatus updateSeriesMasterSequenceNoById(Integer id) {
		ResponseStatus ResponseStatus = new ResponseStatus();
		try {
			SeriesMaster seriesMaster = em.find(SeriesMaster.class, id);
			if(seriesMaster != null) {
				
				if(!StringUtils.isNullOrEmpty(seriesMaster.getCreatedYear())) {
					int seriesDate = Integer.parseInt(seriesMaster.getCreatedYear());
					String currentYear = MartUtilities.getCurrentYear();
					int currYear = Integer.parseInt(currentYear);
					if(currYear!=seriesDate) {
						seriesMaster.setCreatedYear(currentYear);
						seriesMaster.setSequenceNo(1);
					}else {
						seriesMaster.setSequenceNo(seriesMaster.getSequenceNo()+1);
					}
				}
				
				em.merge(seriesMaster);
				em.flush();
				ResponseStatus.setStatus(true);
				ResponseStatus.setPersistingId(seriesMaster.getId());
			}
		}catch (Exception exception) {
			exception.printStackTrace();
			ResponseStatus.setStatus(false);
			ResponseStatus.setErrorMessage(exception.getMessage());
		}
		return ResponseStatus;
	}

}
