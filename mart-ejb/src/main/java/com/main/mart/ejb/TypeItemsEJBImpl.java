/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.TypeItemsTO;
import com.main.mart.entity.TypeItems;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Stateless
public class TypeItemsEJBImpl implements TypeItemsEJBIf {
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addTypeItems(TypeItems typeItems) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(typeItems != null) {
				if(typeItems.getId() != null) {
					em.merge(typeItems);
					em.flush();
				}else {
					em.persist(typeItems);
					em.flush();
				}
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(typeItems.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus updateTypeItems(TypeItems typeItems) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(typeItems != null) {
				em.merge(typeItems);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(typeItems.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}

	@Override
	public TypeItems getTypeItemsById(Integer id) {
		try {
			return em.find(TypeItems.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		} return null;
	}

	@Override
	public Collection<TypeItems> getAllTypeItems(TypeItemsTO itemsTO) {
		Collection<TypeItems> types = null;
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT t FROM TypeItems t WHERE t.status=:status");
			if(itemsTO.getItemCode() != null) {
				hqlBuilder.append(" AND t.code LIKE :code");
			}
			if(itemsTO.getItemDescription() != null) {
				hqlBuilder.append(" AND t.description LIKE :desc");
			}
			if(itemsTO.getTypeId() != null) {
				hqlBuilder.append(" AND t.typeId.id :typeId");
			}
			TypedQuery<TypeItems> typedQuery = em.createQuery(hqlBuilder.toString(), TypeItems.class);
			typedQuery.setParameter("status", StatusEnum.A);
			if(itemsTO.getItemCode() != null) {
				typedQuery.setParameter("code", itemsTO.getItemCode().concat("%"));
			}
			if(itemsTO.getItemDescription() != null) {
				typedQuery.setParameter("desc", itemsTO.getItemDescription().concat("%"));
			}
			if(itemsTO.getTypeId() != null) {
				typedQuery.setParameter("typeId", Integer.parseInt(itemsTO.getTypeId()));
			}
			types = typedQuery.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return types;
	}

	@Override
	public ResponseStatus deleteTypeItems(Integer id) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			TypeItems typeitems = em.find(TypeItems.class, id);
			 em.remove(typeitems);
			 em.flush();
			 responseStatus.setStatus(true);
		}catch (Exception e) {
			e.printStackTrace();
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return responseStatus;
	}

}
