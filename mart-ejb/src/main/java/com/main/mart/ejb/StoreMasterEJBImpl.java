/**
 * 
 */
package com.main.mart.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.StoreMasterTO;
import com.main.mart.entity.StoreMaster;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class StoreMasterEJBImpl implements StoreMasterEJBIf{
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addStoreMaster(StoreMaster storeMaster) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(storeMaster != null) {
				if(storeMaster.getId() != null) {
					em.merge(storeMaster);
					em.flush();
				}else {
					em.persist(storeMaster);
					em.flush();
				}
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(storeMaster.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}

	@Override
	public List<StoreMaster> getStores(StoreMasterTO storeMaster) {
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT s FROM StoreMaster s WHERE s.status=:status");
			if(!StringUtils.isNullOrEmpty(storeMaster.getLocation())) {
				hqlBuilder.append(" AND s.location LIKE :location");
			}
			if(!StringUtils.isNullOrEmpty(storeMaster.getStoreCode())) {
				hqlBuilder.append(" AND s.storeCode LIKE :storeCode");
			}
			if(!StringUtils.isNullOrEmpty(storeMaster.getStoreName())) {
				hqlBuilder.append(" AND s.storeName LIKE :storeName");
			}
			if(!StringUtils.isNullOrEmpty(storeMaster.getStoreTypeId())) {
				hqlBuilder.append(" AND s.storeTypeId.id = :storeTypeId");
			}			
			TypedQuery<StoreMaster> query = em.createQuery(hqlBuilder.toString(), StoreMaster.class);
			query.setParameter("status", StatusEnum.A);
			if(!StringUtils.isNullOrEmpty(storeMaster.getLocation())) {
				query.setParameter("location", storeMaster.getLocation().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(storeMaster.getStoreCode())) {
				query.setParameter("storeCode", storeMaster.getStoreCode().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(storeMaster.getStoreName())) {
				query.setParameter("storeName", storeMaster.getStoreName().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(storeMaster.getStoreTypeId())) {
				query.setParameter("storeTypeId", Integer.parseInt(storeMaster.getStoreTypeId()));
			}
			return query.getResultList();
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}

	@Override
	public StoreMaster getStoreMasterById(Integer id) {
		try {
			return em.find(StoreMaster.class, id);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}

	@Override
	public ResponseStatus deleteStoreMaster(Integer id) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			StoreMaster master = em.find(StoreMaster.class, id);
			em.remove(master);
			em.flush();
			responseStatus.setStatus(true);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return responseStatus;
	}

}
