/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import com.main.mart.entity.Modules;

/**
 * @author Hitesh
 *
 */
public interface ModulesEJBIf {
	
	
	public Collection<Modules> getModulesNames(String flag);
	
	public Modules getModulesByModuleClass(String moduleClass);
}
