/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.OwnerTO;
import com.main.mart.entity.Owner;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Stateless
public class OwnerEJBImpl implements OwnerEJBIf {
	
	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public Collection<Owner> getAllOwnwes(OwnerTO ownerTO) {
		try {
			StringBuilder hqlBuilder = new StringBuilder();
			hqlBuilder.append("SELECT o FROM Owner o WHERE o.status=:status");
			TypedQuery<Owner> typedQuery = em.createQuery(hqlBuilder.toString(), Owner.class);
			typedQuery.setParameter("status", StatusEnum.A);
			return typedQuery.getResultList();
		}catch (Exception e) {
			MartUtilities.showLog(e);
		}
		return null;
	}

	@Override
	public ResponseStatus addUpdateOwner(Owner owner) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(owner != null) {
				if(owner.getId() != null) {
					owner.setLastUpdatedDateTime(MartUtilities.getCurrentDateTime());
					em.merge(owner);
					em.flush();
				}else {
					owner.setCreatedDateTime(MartUtilities.getCurrentDateTime());
					em.persist(owner);
					em.flush();
				}
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(owner.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showLog(e);
		}
		return responseStatus;
	}

	@Override
	public Owner getOwnerById(Integer id) {
		try {
			return em.find(Owner.class, id);
		}catch (Exception e) {
			MartUtilities.showLog(e);
		} return null;
	}

	@Override
	public ResponseStatus deleteOwner(Integer id) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			Owner owner = em.find(Owner.class, id);
			em.remove(owner);
			em.flush();
			responseStatus.setStatus(true);
		}catch (Exception e) {
			MartUtilities.showLog(e);
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
		}
		return responseStatus;
	}

}
