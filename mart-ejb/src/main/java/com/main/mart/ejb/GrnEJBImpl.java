/**
 * 
 */
package com.main.mart.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.main.mart.common.dto.GrnDetailForm;
import com.main.mart.common.dto.GrnForm;
import com.main.mart.common.dto.SeriesKeyTO;
import com.main.mart.entity.Grn;
import com.main.mart.entity.GrnDetails;
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
public class GrnEJBImpl implements GrnEJBIf {
	
	@PersistenceContext(unitName ="martUnit")
	private EntityManager em;
	
	@javax.annotation.Resource
	private SessionContext sctx;
	
	@EJB
	private InventoryCommonEJBIf inventoryCommonEJBIf;
	@EJB
	private SeriesMasterEJBIf seriesMasterEJBIf;
	
	private static final Logger logger = LoggerFactory.getLogger(GrnEJBImpl.class);
	@Override
	public Collection<Grn> getAllGrn(GrnForm grnForm) {
		try {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT g FROM Grn g WHERE g.status = :status");
			if(!StringUtils.isNullOrEmpty(grnForm.getRefNo())){
				sqlBuilder.append(" AND g.grnRefNo LIKE :grnRefNo");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getVendorId())){
				sqlBuilder.append(" AND g.vendorMaster.id = :vendorId");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getCategoryId())) {
				sqlBuilder.append(" AND g.service.id = :categoryId");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getStoreId())) {
				sqlBuilder.append(" AND g.storeMaster.id = :storeId");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceRefNo())){
				sqlBuilder.append(" AND g.invoiceRefNo LIKE :invoiceRefNo");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceFromDate()) && !StringUtils.isNullOrEmpty(grnForm.getInvoiceToDate())) {
				sqlBuilder.append(" AND g.invoiceDate BETWEEN date(:invoiceFromDate) AND date(:invoiceToDate)");
			}else if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceFromDate())) {
				sqlBuilder.append(" AND g.invoiceDate >= date(:invoiceFromDate)");
			}else if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceToDate())) {
				sqlBuilder.append(" AND g.invoiceDate <= date(:invoiceToDate)");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getReceiptFromDate()) && !StringUtils.isNullOrEmpty(grnForm.getReceiptToDate())) {
				sqlBuilder.append(" AND date(g.dateOfRecieptInStores) BETWEEN date(:receiptFromDate) AND date(:receiptToDate)");
			}else if(!StringUtils.isNullOrEmpty(grnForm.getReceiptFromDate())) {
				sqlBuilder.append(" AND date(g.dateOfRecieptInStores) >= date(:receiptFromDate)");
			}else if(!StringUtils.isNullOrEmpty(grnForm.getReceiptToDate())) {
				sqlBuilder.append(" AND date(g.dateOfRecieptInStores) <= date(:receiptToDate)");
			}
			TypedQuery<Grn> query = em.createQuery(sqlBuilder.toString(), Grn.class);
			query.setParameter("status", StatusEnum.A);
			if(!StringUtils.isNullOrEmpty(grnForm.getRefNo())){
				query.setParameter("grnRefNo", grnForm.getRefNo()+"%");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getVendorId())) {
				query.setParameter("vendorId", Integer.parseInt(grnForm.getVendorId()));
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getCategoryId())) {
				query.setParameter("categoryId", Integer.parseInt(grnForm.getCategoryId()));
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getStoreId())) {
				query.setParameter("storeId", Integer.parseInt(grnForm.getStoreId()));
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceRefNo())) {
				query.setParameter("invoiceRefNo", grnForm.getInvoiceRefNo()+"%");
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceFromDate())) {
				query.setParameter("invoiceFromDate", MartUtilities.getDateTimeMMMSSFromString(grnForm.getInvoiceFromDate().concat(" 00:00:00")));
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceToDate())) {
				query.setParameter("invoiceToDate", MartUtilities.getDateTimeMMMSSFromString(grnForm.getInvoiceToDate().concat(" 00:00:00")));
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getReceiptFromDate())) {
				query.setParameter("receiptFromDate", MartUtilities.getDateTimeMMMSSFromString(grnForm.getReceiptFromDate().concat(" 00:00:00")));
			}
			if(!StringUtils.isNullOrEmpty(grnForm.getReceiptToDate())) {
				query.setParameter("receiptToDate", MartUtilities.getDateTimeMMMSSFromString(grnForm.getReceiptToDate().concat(" 00:00:00")));
			}
			logger.info("Query : "+sqlBuilder.toString());
			return query.getResultList();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	@Override
	public Grn getGrnById(Integer id) {
		try {
			return em.find(Grn.class, id);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	@Override
	public ResponseStatus addGrn(Grn grn) {
		ResponseStatus ResponseStatus = new ResponseStatus();
		try {			
			if(grn != null) {
				SeriesKeyTO seriesKeyTO = inventoryCommonEJBIf.getInventorySeriesNoByEntityName(grn.getClass().getName());
				if(seriesKeyTO != null) {
					grn.setGrnNo(seriesKeyTO.getSeriesKey());
					if(!StringUtils.isNullOrEmpty(seriesKeyTO.getErrorMessage())) {
						ResponseStatus.setStatus(false);
						ResponseStatus.setErrorMessage(seriesKeyTO.getErrorMessage());
						return ResponseStatus;
					}
				}
				User createdBy = new User();
				createdBy.setId(MartUtilities.getUserId());
				grn.setCreatedBy(createdBy);
				grn.setCreatedDateTime(MartUtilities.getCurrentDateTime());
				em.persist(grn);
				em.flush();
				ResponseStatus.setStatus(true);
				ResponseStatus.setPersistingId(grn.getId());
				seriesMasterEJBIf.updateSeriesMasterSequenceNoById(seriesKeyTO.getSeriesMasterId());
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
	public ResponseStatus updateGrn(Grn grn) {
		ResponseStatus ResponseStatus = new ResponseStatus();
		try {
			if(grn != null) {
				User updatedBy = new User();
				updatedBy.setId(MartUtilities.getUserId());
				grn.setLastUpdatedBy(updatedBy);
				grn.setLastUpdatedDateTime(MartUtilities.getCurrentDateTime());
				em.merge(grn);
				em.flush();
				ResponseStatus.setStatus(true);
				ResponseStatus.setPersistingId(grn.getId());
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
	public GrnDetails getGrnDetailsById(Integer id) {
		try {
			return em.find(GrnDetails.class, id);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	@Override
	public List<GrnDetails> getGrnDetails(GrnDetailForm grnDetailForm) {
		try {
			
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT gd FROM GrnDetails gd WHERE gd.status = :status");
			
			if(!StringUtils.isNullOrEmpty(grnDetailForm.getBatchNo())){
				sqlBuilder.append(" AND gd.batchno = :batchno");
			}
			if(!StringUtils.isNullOrEmpty(grnDetailForm.getItemMasterId())){
				sqlBuilder.append(" AND gd.itemMaster.id = :itemMasterId");
			}
			
			if(!StringUtils.isNullOrEmpty(grnDetailForm.getExpiryDateNotIn())){
				sqlBuilder.append(" AND gd.expiryDate != '"+grnDetailForm.getExpiryDateNotIn()+"' ");
			}
			if(!StringUtils.isNullOrEmpty(grnDetailForm.getIdNotIn())){
				sqlBuilder.append(" AND gd.id not in ( '"+grnDetailForm.getIdNotIn()+"' ) ");
			}
			
			TypedQuery<GrnDetails> query = em.createQuery(sqlBuilder.toString(), GrnDetails.class);
			query.setParameter("status", StatusEnum.A);
			if(!StringUtils.isNullOrEmpty(grnDetailForm.getBatchNo())){
				query.setParameter("batchno", grnDetailForm.getBatchNo()+"");
			}
			if(!StringUtils.isNullOrEmpty(grnDetailForm.getItemMasterId())){
				query.setParameter("itemMasterId", Integer.parseInt(grnDetailForm.getItemMasterId()));
			}
			
			return query.getResultList();
			
		} catch (Exception e) {
		}
		return null;
	}

}
