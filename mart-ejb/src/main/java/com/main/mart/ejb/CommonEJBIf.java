/**
 * Common EJB Interface
 * 
 * Copyright (c) 2013 Edgeware Technologies. All Rights Reserved.
 */
package com.main.mart.ejb;

import com.main.mart.entity.OptionConfig;


public interface CommonEJBIf {

	public OptionConfig getOptionConfig(String optionkey);
	
	
}
