/**
 * 
 */
package com.main.mart.ejb;


import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.main.mart.entity.Modules;

@Stateless
public class ModulesEJBImpl implements ModulesEJBIf{

	@PersistenceContext(unitName = "martUnit")
	EntityManager em;
	
	private static final Logger logger = LoggerFactory.getLogger(ModulesEJBImpl.class);
	

	@Override
	public Collection<Modules> getModulesNames(String flag) {
		try{
			String jplQuery = "SELECT m FROM Modules m where (m.flag='"+flag+"' or m.flag='B') and status='A' order by m.moduleCode asc";
			TypedQuery<Modules> q = em.createQuery(jplQuery,Modules.class);
			return q.getResultList();
		}catch(Exception e){
			logger.error("Exception :"+e.getMessage());
			return null;
		}
	}

	@Override
	public Modules getModulesByModuleClass(String moduleClass) {
		try {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT m FROM Modules m WHERE m.className = :className");
			TypedQuery<Modules> query = em.createQuery(sqlBuilder.toString(), Modules.class);
			query.setParameter("className", moduleClass);
			List<Modules> lstModules = query.getResultList();
			if(lstModules != null && lstModules.size() > 0){
				return lstModules.get(0);
			}
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
}
