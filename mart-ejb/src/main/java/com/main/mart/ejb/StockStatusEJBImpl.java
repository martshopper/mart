/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.main.mart.common.dto.StockStatusTO;
import com.main.mart.entity.StockStatus;
import com.main.mart.entity.User;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;

/**
 * @author Hitesh
 *
 */
@Stateless
public class StockStatusEJBImpl implements StockStatusEJBIf {

	@PersistenceContext(unitName="martUnit")
	private EntityManager em;
	
	@Override
	public ResponseStatus addStockStatus(StockStatus stockStatus) {
		ResponseStatus responseStatus = new ResponseStatus();
		try {
			if(stockStatus != null) {
				em.persist(stockStatus);
				em.flush();
				responseStatus.setStatus(true);
				responseStatus.setPersistingId(stockStatus.getId());
			}
		}catch (Exception e) {
			responseStatus.setStatus(false);
			responseStatus.setErrorMessage(e.getMessage());
			MartUtilities.showErrorLog(e);
		}
		return responseStatus;
	}
	
	
	@Override
	public Collection<StockStatus> getStockStatus(StockStatusTO stockStatusTO) {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append("Select s from StockStatus s where 1=1");
			if(stockStatusTO.getLimit() != null){ //Select2 Search
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemCode())){
					query.append(" and (s.itemMaster.itemCode like :itemCode");
					query.append(" or s.itemMaster.itemName like :itemName )");
				}
			}else{
				//Normal Search
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemTypeId())){
				query.append(" and s.itemMaster.itemTypeId.id = :itemType");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemMasterId())){
				query.append(" and s.itemMaster.id = :itemMasterId");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getStoreMasterId())){
				query.append(" and s.storeMaster.id = :storeMaster");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getMrp())){
				query.append(" and s.mrp = :mrp");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getMrpNotEqual())){
				query.append(" and s.mrp != :mrpNotEqual");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getQtyInStock())){
				if (stockStatusTO.getReportFlag() != null && "N".equalsIgnoreCase(stockStatusTO.getReportFlag())) {
					query.append(" and s.qtyInStock != :qtyInStock");
				}else {
					query.append(" and s.qtyInStock > :qtyInStock");
				}
				
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemCodeReport())){
				query.append(" and s.itemMaster.itemCode like :itemCodeReport");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemNameReport())){
				query.append(" and s.itemMaster.itemName like :itemNameReport");
			}
			
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getStatus())){
				query.append(" and s.status =:status");
			}
			
			if(stockStatusTO.isStockQtyCheck()){
				query.append(" and s.qtyInStock != 0");
			}
			
			query.append(" order by s.expiryDate asc");
			TypedQuery<StockStatus> typedQuery = em.createQuery(query.toString(),StockStatus.class);
			if(stockStatusTO.getLimit() != null){ //Select2 Search
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemCode())){
					typedQuery.setParameter("itemCode", stockStatusTO.getItemCode()+"%");
					typedQuery.setParameter("itemName", stockStatusTO.getItemCode()+"%");
				}
				typedQuery.setMaxResults(stockStatusTO.getLimit());
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemTypeId())){
				typedQuery.setParameter("itemType", Integer.parseInt(stockStatusTO.getItemTypeId()));
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemMasterId())){
				typedQuery.setParameter("itemMasterId", Integer.parseInt(stockStatusTO.getItemMasterId()));
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getStoreMasterId())){
				typedQuery.setParameter("storeMaster", Integer.parseInt(stockStatusTO.getStoreMasterId()));
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getMrp())){
				typedQuery.setParameter("mrp", Double.parseDouble(stockStatusTO.getMrp()));
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getMrpNotEqual())){
				typedQuery.setParameter("mrpNotEqual", Double.parseDouble(stockStatusTO.getMrpNotEqual()));
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getQtyInStock())){
				typedQuery.setParameter("qtyInStock", Double.parseDouble(stockStatusTO.getQtyInStock()));
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemCodeReport())){
				typedQuery.setParameter("itemCodeReport", "%"+stockStatusTO.getItemCodeReport()+"%");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemNameReport())){
				typedQuery.setParameter("itemNameReport", "%"+stockStatusTO.getItemNameReport()+"%");
			}
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getStatus())){
				typedQuery.setParameter("status", StatusEnum.valueOf(stockStatusTO.getStatus()));
			}
			
			return typedQuery.getResultList();
			
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public StockStatus getStockStatusByGrnDetails(StockStatusTO stockStatusTO) {
		try {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT ss FROM StockStatus ss WHERE ss.status=:status");
			
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getId())) {
				sqlBuilder.append(" AND ss.id = :id");
			}else{
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemMasterId())) {
					sqlBuilder.append(" AND ss.itemMaster.id = :itemMasterId");
				}
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getBatchNo())) {
					sqlBuilder.append(" AND ss.batchNo = :batchNo");
				}
				sqlBuilder.append(" AND (ss.expiryDate IS NULL");
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getExpDate())) {
					sqlBuilder.append(" OR ss.expiryDate = date(:expDate)");
				}
				sqlBuilder.append(")");
			}
			
			TypedQuery<StockStatus> query = em.createQuery(sqlBuilder.toString(), StockStatus.class);
			query.setParameter("status", StatusEnum.A);
			
			if(!StringUtils.isNullOrEmpty(stockStatusTO.getId())) {
				query.setParameter("id", Integer.parseInt(stockStatusTO.getId()));
			}else{
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getItemMasterId())) {
					query.setParameter("itemMasterId", Integer.parseInt(stockStatusTO.getItemMasterId()));
				}
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getBatchNo())) {
					query.setParameter("batchNo", stockStatusTO.getBatchNo());
				}
				if(!StringUtils.isNullOrEmpty(stockStatusTO.getOldExpDate())) {
					query.setParameter("expDate", MartUtilities.getDateTimeMMMSSFromString(stockStatusTO.getOldExpDate().concat(" 00:00:00")));
				}else if(!StringUtils.isNullOrEmpty(stockStatusTO.getExpDate())) {
					query.setParameter("expDate", MartUtilities.getDateTimeMMMSSFromString(stockStatusTO.getExpDate().concat(" 00:00:00")));
				}
			}
			query.setMaxResults(1);
			List<StockStatus> lstStockStatus = query.getResultList();
			if(lstStockStatus != null && lstStockStatus.size() > 0){
				return lstStockStatus.get(0);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	

	@Override
	public ResponseStatus updateStockStatus(StockStatus stockStatus) {
		ResponseStatus ResponseStatus = new ResponseStatus();
		try {
			if(stockStatus != null) {
				User updatedBy = new User();
				updatedBy.setId(MartUtilities.getUserId());
				stockStatus.setLastUpdatedBy(updatedBy);
				stockStatus.setLastUpdatedDateTime(MartUtilities.getCurrentDateTime());
				em.merge(stockStatus);
				em.flush();
				ResponseStatus.setStatus(true);
				ResponseStatus.setPersistingId(stockStatus.getId());
			}
		}catch (PersistenceException persistenceException) {
			persistenceException.printStackTrace();
			ResponseStatus.setStatus(false);
			ResponseStatus.setErrorMessage(persistenceException.getMessage());
		}catch (Exception exception) {
			exception.printStackTrace();
			ResponseStatus.setStatus(false);
			ResponseStatus.setErrorMessage(exception.getMessage());
		}
		return ResponseStatus;
	}

	@Override
	public StockStatus getStockStatusbyId(Integer id) {
		try {
			if(id != null) {
				return em.find(StockStatus.class, id);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

}
