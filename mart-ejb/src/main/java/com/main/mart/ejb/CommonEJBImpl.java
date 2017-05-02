/**
 * Common EJB Implementation
 * 
 * Copyright (c) 2013 Edgeware Technologies. All Rights Reserved.
 */
package com.main.mart.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.main.mart.entity.OptionConfig;

@Stateless
public class CommonEJBImpl implements CommonEJBIf {
	@PersistenceContext(unitName = "martUnit")
	EntityManager em;

	private static final Logger logger = LoggerFactory.getLogger(CommonEJBImpl.class);

	@Override
	public OptionConfig getOptionConfig(String optionkey) {
		OptionConfig value = null;

		String sql = "Select o  FROM  OptionConfig o  where  o.key = '"+optionkey+"' ";

		TypedQuery<OptionConfig> q = em.createQuery(sql,OptionConfig.class);
		List<OptionConfig> lstData = q.getResultList();
		if(lstData != null && !lstData.isEmpty()){
			value = lstData.get(0);
		}		
		return value;
	}


}
