/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.ItemTypeTO;
import com.main.mart.entity.ItemType;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class ItemTypeEJBImpl implements ItemTypeEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addUpdateItemType(ItemType itemType) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(itemType != null) {
				if(itemType.getId() != null) {
					em.merge(itemType);
					em.flush();
				}else {
					em.persist(itemType);
					em.flush();
				}
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(itemType.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}

	@Override
	public Collection<ItemType> getItemTypes(ItemTypeTO itemType) {
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT it FROM ItemType it WHERE it.status=:status");
			if(!StringUtils.isNullOrEmpty(itemType.getTypeCategoryId())) {
				hqlBuilder.append(" AND it.typeCategoryId.id=:typeCategoryId");
			}
			if(!StringUtils.isNullOrEmpty(itemType.getTypeCode())) {
				hqlBuilder.append(" AND it.typeCode LIKE :typeCode");
			}
			if(!StringUtils.isNullOrEmpty(itemType.getTypeName())) {
				hqlBuilder.append(" AND it.typeName LIKE :typeName");
			}
			TypedQuery<ItemType> typedQuery = em.createQuery(hqlBuilder.toString(), ItemType.class);
			typedQuery.setParameter("status", StatusEnum.A);
			if(!StringUtils.isNullOrEmpty(itemType.getTypeCategoryId())) {
				typedQuery.setParameter("typeCategoryId", Integer.parseInt(itemType.getTypeCategoryId()));
			}
			if(!StringUtils.isNullOrEmpty(itemType.getTypeCode())) {
				typedQuery.setParameter("typeCode", itemType.getTypeCode().concat("%"));
			}
			if(!StringUtils.isNullOrEmpty(itemType.getTypeName())) {
				typedQuery.setParameter("typeName", itemType.getTypeName().concat("%"));
			}
			return typedQuery.getResultList();
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}

	@Override
	public ItemType getItemTypeById(Integer id) {
		try {
			return em.find(ItemType.class, id);
		}catch (Exception e) {
			MartUtilities.showErrorLog(e);
		}
		return null;
	}

	@Override
	public ResponseStatus deleteItemType(Integer id) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			ItemType itemType = em.find(ItemType.class, id);
			em.remove(itemType);
			em.flush();
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}

}
