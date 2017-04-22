/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.ItemMasterTO;
import com.main.mart.entity.ItemMaster;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Stateless
public class ItemMasterEJBImpl implements ItemMasterEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addUpdateItemMaster(ItemMaster itemMaster) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(itemMaster != null) {
				if(itemMaster.getId() != null) {
					itemMaster.setUpdatedDateTime(MartUtilities.getCurrentDateTime());
					em.merge(itemMaster);
					em.flush();
				}else {
					itemMaster.setCreatedDateTime(MartUtilities.getCurrentDateTime());
					em.persist(itemMaster);
					em.flush();
				}
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(itemMaster.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showLog(e);
		}
		return responseStatus;
	}

	@Override
	public Collection<ItemMaster> getItemMaster(ItemMasterTO itemMaster) {
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT im FROM ItemMaster im WHERE im.status=:status");
			TypedQuery<ItemMaster> typedQuery = em.createQuery(hqlBuilder.toString(), ItemMaster.class);
			typedQuery.setParameter("status", StatusEnum.A);
			return typedQuery.getResultList();
		}catch (Exception e) {
			MartUtilities.showLog(e);
		}
		return null;
	}

	@Override
	public ItemMaster getItemMasterById(Integer id) {
		try {
			return em.find(ItemMaster.class, id);
		}catch (Exception e) {
			MartUtilities.showLog(e);
		}
		return null;
	}

	@Override
	public ResponseStatus deleteItemMaster(Integer id) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			ItemMaster itemMaster = em.find(ItemMaster.class, id);
			em.remove(itemMaster);
			em.flush();
			responseStatus.setStatus(true);
		}catch (Exception e) {
			MartUtilities.showLog(e);
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return null;
	}

}
