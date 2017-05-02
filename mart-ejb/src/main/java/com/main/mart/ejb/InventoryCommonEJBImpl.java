/**
 * 
 */
package com.main.mart.ejb;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.main.mart.common.dto.SeriesKeyTO;
import com.main.mart.entity.ModuleSeries;
import com.main.mart.entity.Modules;
import com.main.mart.entity.OptionConfig;
import com.main.mart.entity.SeriesMaster;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class InventoryCommonEJBImpl implements InventoryCommonEJBIf {
	@PersistenceContext(unitName ="martUnit")
	private EntityManager em;
	
	@javax.annotation.Resource
	private SessionContext sctx;
	
	@EJB
	private ModulesEJBIf moduleEJBIf;
	@EJB
	private SeriesMasterEJBIf seriesMasterEJBIf;

	@EJB
	private CommonEJBIf commonEJBIf;
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryCommonEJBImpl.class);
	
	@Override
	public SeriesKeyTO getInventorySeriesNoByEntityName(String entityName) {
		SeriesKeyTO seriesKeyTO = new SeriesKeyTO();
		try {
			if(!StringUtils.isNullOrEmpty(entityName)) {
				Modules modules = moduleEJBIf.getModulesByModuleClass(entityName);
				if(modules != null) {
					TypedQuery<ModuleSeries> typedQuery = em.createQuery("SELECT ms FROM ModuleSeries ms WHERE ms.moduleId.id=:moduleId", ModuleSeries.class);
					typedQuery.setParameter("moduleId", modules.getId());
					ModuleSeries moduleSeries = null;
					List<ModuleSeries> lstModuleSeries = typedQuery.getResultList();
					if(lstModuleSeries != null && lstModuleSeries.size() > 0){
						moduleSeries =  lstModuleSeries.get(0);
					}
					if(moduleSeries != null) {
						SeriesMaster seriesMaster = moduleSeries.getSeriesMasterId();
						if(seriesMaster != null) {
							Boolean ENTERPRISE_SERIES = false;
							OptionConfig enterpriseSeries = commonEJBIf.getOptionConfig("ENTERPRISE_SERIES");
							StringBuilder keyBuilder = new StringBuilder();
							if(enterpriseSeries != null 
								&& !StringUtils.isNullOrEmpty(enterpriseSeries.getOptionKey()) 
									&& "Y".equalsIgnoreCase(enterpriseSeries.getOptionKey())) {
								ENTERPRISE_SERIES = true;
								if(!StringUtils.isNullOrEmpty(seriesMaster.getCreatedYear())) {
									int seriesDate = Integer.parseInt(seriesMaster.getCreatedYear());
									String currentYear = MartUtilities.getCurrentYear();
									int currYear = Integer.parseInt(currentYear);
									if(currYear!=seriesDate) {
										keyBuilder.append("/").append(currYear);
									}else {
										keyBuilder.append("/").append(seriesDate);
									}
								}
							}							
							if(!StringUtils.isNullOrEmpty(seriesMaster.getSeries())) {
								if(ENTERPRISE_SERIES) {									
									keyBuilder.append("/").append(seriesMaster.getSeries());
								}else {
									keyBuilder.append(seriesMaster.getSeries());
								}
							}
							if(seriesMaster.getSequenceNo() != null) {
								keyBuilder.append("-").append(seriesMaster.getSequenceNo());
							}
							seriesKeyTO.setSeriesKey(keyBuilder.toString());
							seriesKeyTO.setSeriesMasterId(seriesMaster.getId());
						}else {
							seriesKeyTO.setErrorMessage("Series Master not found for Entity : "+entityName);
						}
					}else {
						seriesKeyTO.setErrorMessage("Module Series not found for Entity : "+entityName);
					}
				}else {
					seriesKeyTO.setErrorMessage("Module not found for Entity : "+entityName);
				}
			}
		}catch (Exception exception) {
			seriesKeyTO.setErrorMessage(exception.getMessage());
			logger.error(this.getClass().getName(), exception);
		}
		return seriesKeyTO;
	}

	
}
