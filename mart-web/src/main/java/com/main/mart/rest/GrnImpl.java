package com.main.mart.rest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.main.mart.common.dto.GrnDetailForm;
import com.main.mart.common.dto.GrnForm;
import com.main.mart.common.dto.GrnForms;
import com.main.mart.common.dto.SeriesKeyTO;
import com.main.mart.common.dto.StockStatusTO;
import com.main.mart.ejb.GrnEJBIf;
import com.main.mart.ejb.InventoryCommonEJBIf;
import com.main.mart.ejb.StockStatusEJBIf;
import com.main.mart.entity.Grn;
import com.main.mart.entity.GrnDetails;
import com.main.mart.entity.ItemMaster;
import com.main.mart.entity.StockGrnDetails;
import com.main.mart.entity.StockStatus;
import com.main.mart.entity.StoreMaster;
import com.main.mart.entity.VendorMaster;
import com.main.mart.utilities.MartUtilities;
import com.main.mart.utilities.ResponseStatus;
import com.main.mart.utilities.StatusEnum;
import com.main.mart.utilities.StringUtils;


@Stateless
public class GrnImpl implements GrnIf { 

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(GrnImpl.class);
	
	@EJB
	private GrnEJBIf grnEJBIf;
	@EJB
	private StockStatusEJBIf stockStatusEJBIf;
	@EJB
	private InventoryCommonEJBIf inventoryCommonEJBIf;
	@Inject
	private Validator validator;
	@Override
	public GrnForms getGrnForms(String grnRefNo,
			String vendorId, String serviceId, String storeId, String invoiceNo,
			String invoiceFromDate, String invoiceToDate,
			String receiptFromDate, String receiptToDate) {
		GrnForms grnForms = new GrnForms();
		try {
			GrnForm grnForm = new GrnForm();
			if(!StringUtils.isNullOrEmpty(grnRefNo)) {
				grnForm.setRefNo(grnRefNo);
			}
			if(!StringUtils.isNullOrEmpty(vendorId)) {
				grnForm.setVendorId(vendorId);
			}
			if(!StringUtils.isNullOrEmpty(serviceId)) {
				grnForm.setCategoryId(serviceId);
			}
			if(!StringUtils.isNullOrEmpty(storeId)) {
				grnForm.setStoreId(storeId);
			}
			if(!StringUtils.isNullOrEmpty(invoiceNo)) {
				grnForm.setInvoiceRefNo(invoiceNo);
			}
			if(!StringUtils.isNullOrEmpty(invoiceFromDate)) {
				grnForm.setInvoiceFromDate(invoiceFromDate);
			}
			if(!StringUtils.isNullOrEmpty(invoiceToDate)) {
				grnForm.setInvoiceToDate(invoiceToDate);
			}
			if(!StringUtils.isNullOrEmpty(receiptFromDate)) {
				grnForm.setReceiptFromDate(receiptFromDate);
			}
			if(!StringUtils.isNullOrEmpty(receiptToDate)) {
				grnForm.setReceiptToDate(receiptToDate);
			}
			Collection<Grn> grns = grnEJBIf.getAllGrn(grnForm);
			List<GrnForm> forms = new ArrayList<GrnForm>();
			if(grns != null && !grns.isEmpty() && grns.size()>0) {				
				for(Grn grn : grns) {
					GrnForm form = this.transformGrnFormFromEntity(grn);
					if(form != null) {
						forms.add(form);
					}
				}		
			}
			grnForms.setGrnForm(forms);
			grnForms.setiTotalDisplayRecords(forms.size());
			grnForms.setiTotalRecords(forms.size());
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return grnForms;
	}
	
	@Override
	public GrnForm getGrnById(Integer id) {
		try {
			if(id != null) {
				return this.transformGrnFormFromEntity(grnEJBIf.getGrnById(id));
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public Response addGrnForm(GrnForm grnForm) {
		Map<String, String> responseObj = new HashMap<String, String>();
		try {
			if(grnForm != null) {
					responseObj = this.validateGrnForm(grnForm);
					if(responseObj != null && responseObj.size() > 0){
						builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
						return builder.build();
					}
					Grn grn = this.transformGrnEntityFromGrnForm(grnForm);
					ResponseStatus ResponseStatus = grnEJBIf.addGrn(grn);
					if(ResponseStatus.getStatus()) {
						responseObj.put("id", String.valueOf(ResponseStatus.getPersistingId()));
						builder = Response.status(Response.Status.OK).entity(responseObj);
					}else {
						responseObj.put("exception", ResponseStatus.getErrorMessage());
						builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					}
			}else {
				responseObj.put("exception", "GrnForm should not be empty");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
			responseObj.put("exception", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Override
	public Response updateGrnForm(GrnForm grnForm, Integer id) {
		Map<String, String> responseObj = new HashMap<String, String>();
		try {
			if(grnForm != null && id != null) {
				grnForm.setId(String.valueOf(id));
				responseObj = this.validateGrnForm(grnForm);
				if(responseObj != null && responseObj.size() > 0){
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					return builder.build();
				}
				Grn grn = this.transformGrnEntityFromGrnForm(grnForm);
				ResponseStatus ResponseStatus = grnEJBIf.updateGrn(grn);
				if(ResponseStatus.getStatus()) {
					responseObj.put("id", String.valueOf(ResponseStatus.getPersistingId()));
					builder = Response.status(Response.Status.OK).entity(responseObj);
				}else {
					responseObj.put("exception", ResponseStatus.getErrorMessage());
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
				}
			}else {
				responseObj.put("exception", "GrnForm should not be empty");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
			responseObj.put("exception", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Override
	public Response deleteGrn(Integer id) {
		Map<String, String> responseObj = new HashMap<String, String>();
		try {
			if(id != null) {
				Grn grn = grnEJBIf.getGrnById(id);
				if(grn != null) {
					List<String> resetStocks = new ArrayList<String>();
					List<String> inactiveGrnDetails = new ArrayList<String>();
					List<GrnDetails> grnDetails = grn.getLstGrnDetails();
					if(grnDetails != null && !grnDetails.isEmpty() && grnDetails.size()>0) {
						grnDetails = new ArrayList<GrnDetails>();
						for(GrnDetails details : grnDetails) {
							if(details.getStatus() != null && details.getStatus().compareTo(StatusEnum.A)==0) {
								GrnDetailForm detailForm = this.transformGrnDetailFormFromEntity(details);
								if(detailForm != null) {
									detailForm.setStatus("I");
									ResponseStatus ResponseStatus = this.addUpdateStockStatus(detailForm);
									if(ResponseStatus.getStatus()) {
										resetStocks.add(String.valueOf(ResponseStatus.getPersistingId()));
									}else {
										responseObj.put("exception", ResponseStatus.getErrorMessage());
										builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
										return builder.build();
									}
								}
								details.setStatus(StatusEnum.I);
								inactiveGrnDetails.add(String.valueOf(details.getId()));
							}							
							grnDetails.add(details);
						}
					}
					grn.setLstGrnDetails(grnDetails);
					grn.setStatus(StatusEnum.I);
					ResponseStatus ResponseStatus = grnEJBIf.updateGrn(grn);
					if(ResponseStatus.getStatus()) {
						responseObj.put("id", String.valueOf(ResponseStatus.getPersistingId()));
						if (inactiveGrnDetails.size() > 0) {
							responseObj.put("inactiveGrnDetails", inactiveGrnDetails.toString());
						}
						if (resetStocks.size() > 0) {
							responseObj.put("resetStocks", resetStocks.toString());
						}
						builder = Response.status(Response.Status.OK).entity(responseObj);
					}else {
						responseObj.put("exception", ResponseStatus.getErrorMessage());
						builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					}
				}else {
					responseObj.put("exception", "Grn is not exists for id "+id);
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
				}
			}else {
				responseObj.put("exception", "Id should not be NULL");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		}catch (Exception exception) {
			exception.printStackTrace();
			responseObj.put("exception", exception.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	/**
	 * Validate GrnForm Custom or Automatically
	 * @param grnForm
	 * @return
	 */
	private Map<String, String> validateGrnForm(GrnForm grnForm) {
		Map<String, String> responseObj = new HashMap<String, String>();
		try {
			Set<ConstraintViolation<GrnForm>> violations = validateGrn(grnForm);	
			if(!violations.isEmpty()){
				for (ConstraintViolation<?> violation : violations) {
					responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
				}
			}
			List<GrnDetailForm> grnDetailForms = grnForm.getLstGrnDetailForm();
			if(grnDetailForms == null || (grnDetailForms != null && grnDetailForms.isEmpty()) ) {
				responseObj.put("grnDetails","Please enter atleast one Item");
			}
			
			// Case of Save GRN
			if(StringUtils.isNullOrEmpty(grnForm.getId())){
				SeriesKeyTO seriesKeyTO = inventoryCommonEJBIf.getInventorySeriesNoByEntityName(new Grn().getClass().getName());
				if(seriesKeyTO != null) {
					if(!StringUtils.isNullOrEmpty(seriesKeyTO.getErrorMessage())) {
						responseObj.put("grnRefNo","Error while Generating GRN #");
					}
				}
			}
			
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return responseObj;
	}
	/**
	 * Validate GrnForm Automatically
	 * @param grnForm
	 * @return
	 */
	private Set<ConstraintViolation<GrnForm>> validateGrn(GrnForm grnForm){		
		return  validator.validate(grnForm);
	}
	/**
	 * Transforming Grn Entity to GrnForm
	 * @param grn
	 * @return GrnForm
	 */
	private GrnForm transformGrnFormFromEntity(Grn grn) {
		GrnForm grnForm = null;
		try {
			if(grn != null) {
				Double grnTotalAmt = 0.00;
				Double grnTotalAmtWithTax = 0.00;
				grnForm = new GrnForm();				
				if(grn.getCreatedBy() != null) {
					grnForm.setCreatedById(String.valueOf(grn.getCreatedBy().getId()));
					grnForm.setCreatedByName(MartUtilities.getUserFullName(grn.getCreatedBy()));
				}
				if(grn.getCreatedDateTime() != null) {
					grnForm.setCreatedDateTime(MartUtilities.getStringDateInMMMFromDate(grn.getCreatedDateTime()));
				}
				if(grn.getDateOfReceive() != null) {
					grnForm.setDateOfRecInStore(MartUtilities.getStringDateInMMMFromDate(grn.getDateOfReceive()));
				}
				Collection<GrnDetails> grnDetails = grn.getLstGrnDetails();
				if(grnDetails != null && !grnDetails.isEmpty() && grnDetails.size()>0) {
					List<GrnDetailForm> detailForms = new ArrayList<GrnDetailForm>();
					for(GrnDetails details : grnDetails) {
						if(details.getStatus() != null && details.getStatus().compareTo(StatusEnum.A)==0) {
							GrnDetailForm detailForm = this.transformGrnDetailFormFromEntity(details);
							if(detailForm != null) {
								if(detailForm.getTotalAmount() != null){
									grnTotalAmt += Double.parseDouble(detailForm.getTotalAmount());
								}
								if(detailForm.getTotalAmountWithTax() != null){
									grnTotalAmtWithTax += Double.parseDouble(detailForm.getTotalAmountWithTax());
								}
								
								detailForms.add(detailForm);
							}
						}
					}
					grnForm.setLstGrnDetailForm(detailForms);
				}
				grnForm.setTotalAmount(String.format("%.2f", grnTotalAmt));
				grnForm.setTotalAmountWithTax(String.format("%.2f", grnTotalAmtWithTax ));
				if(!StringUtils.isNullOrEmpty(grn.getGrnNo())){
					grnForm.setRefNo(grn.getGrnNo());
				}
				grnForm.setId(String.valueOf(grn.getId()));
				if(grn.getInvoiceDate() != null) {
					grnForm.setInvoiceDate(MartUtilities.getStringDateInMMMFromDate(grn.getInvoiceDate()));
				}
				if(!StringUtils.isNullOrEmpty(grn.getInvoiceNo())){
					grnForm.setInvoiceRefNo(grn.getInvoiceNo());
				}
//				if(grn.getService() != null) {
//					grnForm.setCategoryId(String.valueOf(grn.getService().getId()));
//					grnForm.setCategoryCode(grn.getService().getServiceCode());
//					grnForm.setCategoryName(grn.getService().getServiceName());
//				}
				if(grn.getStatus() != null) {
					grnForm.setStatus(String.valueOf(grn.getStatus()));
				}
				if(grn.getStoreMaster() != null) {
					grnForm.setStoreId(String.valueOf(grn.getStoreMaster().getId()));
					grnForm.setStoreName(grn.getStoreMaster().getStoreName());
				}
				if(grn.getLastUpdatedBy() != null) {
					grnForm.setUpdatedById(String.valueOf(grn.getLastUpdatedBy().getId()));
					grnForm.setUpdatedByName(MartUtilities.getUserFullName(grn.getLastUpdatedBy()));
				}
				if(grn.getLastUpdatedDateTime() != null) {
					grnForm.setUpdatedDateTime(MartUtilities.getStringDateInMMMFromDate(grn.getLastUpdatedDateTime()));
				}
				if(grn.getVendorMaster() != null) {
					grnForm.setVendorId(String.valueOf(grn.getVendorMaster().getId()));
					grnForm.setVendorName(grn.getVendorMaster().getVendorName());
				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return grnForm;
	}
	/**
	 * Transforming GrnDetailForm from GrnDetails Entity
	 * @param grnDetails
	 * @return GrnDetailForm
	 */
	private GrnDetailForm transformGrnDetailFormFromEntity(GrnDetails grnDetails) {
		GrnDetailForm grnDetailForm = null;
		try {
			if(grnDetails != null) {
				grnDetailForm = new GrnDetailForm();
				if(!StringUtils.isNullOrEmpty(grnDetails.getBatchNo())){
					grnDetailForm.setBatchNo(grnDetails.getBatchNo());
				}
				if(grnDetails.getExpiryDate() != null) {
					grnDetailForm.setExpiryDate(MartUtilities.getStringDateInMMMFromDate(grnDetails.getExpiryDate()));
					grnDetailForm.setOldExpiryDate(MartUtilities.getStringDateInMMMFromDate(grnDetails.getExpiryDate()));
				}
				if(grnDetails.getGrnId() != null) {
					grnDetailForm.setGrnId(String.valueOf(grnDetails.getGrnId().getId()));
				}
				grnDetailForm.setId(String.valueOf(grnDetails.getId()));
				if(grnDetails.getItemMaster() != null) {
					ItemMaster itemMaster = grnDetails.getItemMaster();
					grnDetailForm.setItemMasterId(String.valueOf(itemMaster.getId()));
					grnDetailForm.setItemMasterCode(itemMaster.getItemCode());
					grnDetailForm.setItemMasterDesc(itemMaster.getItemName());
					grnDetailForm.setItemTypeId(itemMaster.getItemTypeId().getId().toString());
					
				}
				if(grnDetails.getMfgDate() != null) {
					grnDetailForm.setMfgDate(MartUtilities.getStringDateInMMMFromDate(grnDetails.getMfgDate()));
				}
				if(grnDetails.getMrp() != null) {
					grnDetailForm.setMrp(String.format("%.2f", grnDetails.getMrp()));
				}
				if(grnDetails.getPackSize() != null) {
					grnDetailForm.setPackSize(String.valueOf(grnDetails.getPackSize()));
					grnDetailForm.setOldPackSize(String.valueOf(grnDetails.getPackSize()));
				}
				if(grnDetails.getQuantityAccepted() != null) {
					grnDetailForm.setQuantityAccepted(grnDetails.getQuantityAccepted().intValue()+"");
				}
				if(grnDetails.getQuantityReceived() != null) {
					grnDetailForm.setQuantityReceived(grnDetails.getQuantityReceived().intValue()+"");
					grnDetailForm.setOldQuantity(grnDetails.getQuantityReceived().intValue()+"");
				}
				if(grnDetails.getRate() != null) {
					grnDetailForm.setRate(String.format("%.2f", grnDetails.getRate()));
				}
				if(grnDetails.getRate() != null && grnDetails.getQuantityReceived() != null){
					Double totalAmt = grnDetails.getRate() * grnDetails.getQuantityReceived();
					grnDetailForm.setTotalAmount(String.format("%.2f", totalAmt));
					if(grnDetails.getTotalTaxAmount() != null){
						Double totalAmtWithTax = grnDetails.getTotalTaxAmount() + totalAmt;
						grnDetailForm.setTotalAmountWithTax(String.format("%.2f", totalAmtWithTax));
					}
				}
				if(grnDetails.getStatus() != null) {
					grnDetailForm.setStatus(String.valueOf(grnDetails.getStatus()));
				}
				if(grnDetails.getTotalTaxAmount() != null) {
					grnDetailForm.setTotalTaxAmount(String.format("%.2f", grnDetails.getTotalTaxAmount()));
				}
//				if(grnDetails.getStockGrnDetails() != null && !grnDetails.getStockGrnDetails().isEmpty()){
//					StockGrnDetails stockGrnDetails = grnDetails.getStockGrnDetails().iterator().next();
//					if(stockGrnDetails != null && stockGrnDetails.getStockStatus() != null){
//						grnDetailForm.setStockStatusId(stockGrnDetails.getStockStatus().getId().toString());
//					}
//				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}return grnDetailForm;
	}
	/**
	 * Transforming GrnForm to Grn
	 * @param grnForm
	 * @return Grn
	 */
	private Grn transformGrnEntityFromGrnForm(GrnForm grnForm) {
		try {
			Grn grn = null;
			if(grnForm != null) {
				if(!StringUtils.isNullOrEmpty(grnForm.getId())){
					grn = grnEJBIf.getGrnById(Integer.parseInt(grnForm.getId()));
				}else {
					grn = new Grn();
				}
//				if(!StringUtils.isNullOrEmpty(grnForm.getCategoryId())) {
//					Service serviceId = new Service();
//					serviceId.setId(Integer.parseInt(grnForm.getCategoryId()));
//					grn.setService(serviceId);
//				}
				if(!StringUtils.isNullOrEmpty(grnForm.getVendorId())) {
					VendorMaster vendorMaster = new VendorMaster();
					vendorMaster.setId(Integer.parseInt(grnForm.getVendorId()));
					grn.setVendorMaster(vendorMaster);
				}
				if(!StringUtils.isNullOrEmpty(grnForm.getStoreId())) {
					StoreMaster storeMaster = new StoreMaster();
					storeMaster.setId(Integer.parseInt(grnForm.getStoreId()));
					grn.setStoreMaster(storeMaster);
				}
				if(!StringUtils.isNullOrEmpty(grnForm.getDateOfRecInStore())){
					grn.setDateOfReceive(MartUtilities.getDateFromStringWithMMM(grnForm.getDateOfRecInStore()));
				}
				if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceDate())) {
					grn.setInvoiceDate(MartUtilities.getDateFromStringWithMMM(grnForm.getInvoiceDate()));
				}
				if(!StringUtils.isNullOrEmpty(grnForm.getInvoiceRefNo())){
					grn.setInvoiceNo(grnForm.getInvoiceRefNo());
				}			
				grn.setStatus(StatusEnum.A);
				grn.setLstGrnDetails(this.transformGrnDetailsFromGrnForm(grnForm, grn));
			}
			return grn;
		}catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}
	/**
	 * Transforming GrnDetails from GrnForm
	 * @param grnForm
	 * @return
	 */
	private List<GrnDetails> transformGrnDetailsFromGrnForm(GrnForm grnForm, Grn grn) {
		List<GrnDetails> grnDetails = null;
		try {
			Collection<GrnDetailForm> grnDetailForms = grnForm.getLstGrnDetailForm();
			if(grnDetailForms != null && !grnDetailForms.isEmpty() && grnDetailForms.size()>0) {
				grnDetails = new ArrayList<GrnDetails>();
				for(GrnDetailForm detailForm : grnDetailForms) {
					GrnDetails details = null;
					Boolean newDrnDetails = true;
					if(!StringUtils.isNullOrEmpty(detailForm.getId())){
						newDrnDetails = false;
						details = grnEJBIf.getGrnDetailsById(Integer.parseInt(detailForm.getId()));
						// Get StockStatusId 
//						if(details.getStockGrnDetails() != null && !details.getStockGrnDetails().isEmpty()){
//							StockGrnDetails stockGrnDetails = details.getStockGrnDetails().iterator().next();
//							if(stockGrnDetails != null && stockGrnDetails.getStockStatus() != null){
//								detailForm.setStockStatusId(stockGrnDetails.getStockStatus().getId().toString());
//							}
//						}
					}else {
						details = new GrnDetails();
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getBatchNo())) {
						details.setBatchNo(detailForm.getBatchNo());
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getExpiryDate())) {
						details.setExpiryDate(MartUtilities.getDateFromStringWithMMM(detailForm.getExpiryDate()));
					}
					details.setGrnId(grn);
					if(!StringUtils.isNullOrEmpty(detailForm.getId())) {
						details.setId(Integer.parseInt(detailForm.getId()));
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getItemMasterId())) {
						ItemMaster itemMaster = new ItemMaster();
						itemMaster.setId(Integer.parseInt(detailForm.getItemMasterId()));
						details.setItemMaster(itemMaster);
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getMfgDate())) {
						details.setMfgDate(MartUtilities.getDateFromStringWithMMM(detailForm.getMfgDate()));
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getMrp())) {
						details.setMrp(Double.parseDouble(detailForm.getMrp()));
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getPackSize())) {
						details.setPackSize(Integer.parseInt(detailForm.getPackSize()));
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getQuantityAccepted())) {
						details.setQuantityAccepted(Double.parseDouble(detailForm.getQuantityAccepted()));
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getQuantityReceived())) {
						details.setQuantityReceived(Double.parseDouble(detailForm.getQuantityReceived()));
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getRate())) {
						details.setRate(Double.parseDouble(detailForm.getRate()));
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getStatus())) {
						details.setStatus(StatusEnum.valueOf(detailForm.getStatus()));
					}else {
						details.setStatus(StatusEnum.A);
					}
					if(!StringUtils.isNullOrEmpty(detailForm.getTotalTaxAmount())) {
						details.setTotalTaxAmount(Double.parseDouble(detailForm.getTotalTaxAmount()));
					}else {
						details.setTotalTaxAmount(null);
					}
					detailForm.setDateOfRecInStore(grnForm.getDateOfRecInStore());
					detailForm.setStoreId(grnForm.getStoreId());
					/**
					 * Adding/Updating/Deleting Stock Status
					 */
					StockStatus stockStatus = null;
					ResponseStatus ResponseStatus = this.addUpdateStockStatus(detailForm);
					if (ResponseStatus.getStatus()) {
						logger.info("Stock Status Updated Successfully");
						logger.info("Stock Id : "+ResponseStatus.getPersistingId());
						stockStatus = new StockStatus();
						stockStatus.setId(ResponseStatus.getPersistingId());
					}else {
						logger.error("Stock Status Not Updated Successfully");
						logger.error("Stock Error : "+ResponseStatus.getErrorMessage());
					}
					if (stockStatus != null) {
						Collection<StockGrnDetails> stockGrnDetails = null;
						if (newDrnDetails) {
							stockGrnDetails = new ArrayList<StockGrnDetails>();
							StockGrnDetails stockGrnDetail = new StockGrnDetails();
							stockGrnDetail.setGrnDetailsId(details);
							stockGrnDetail.setStockStatusId(stockStatus);
							stockGrnDetails.add(stockGrnDetail);
//							details.setStockGrnDetails(stockGrnDetails);
						}else {
//							stockGrnDetails = details.getStockGrnDetails();
//							if (stockGrnDetails != null && !stockGrnDetails.isEmpty() && stockGrnDetails.size()>0) {
//								boolean stockExisting = false;
//								for (StockGrnDetails stockGrnDetail : stockGrnDetails) {
//									if(stockGrnDetail.getStockStatus() != null 
//											&& stockStatus.getId()==stockGrnDetail.getStockStatus().getId()) {
//										stockExisting = true;
//									}
//								}
//								if (!stockExisting) {
//									StockGrnDetails stockGrnDetail = new StockGrnDetails();
//									stockGrnDetail.setGrnDetailId(details);
//									stockGrnDetail.setStockStatus(stockStatus);
//									stockGrnDetails.add(stockGrnDetail);
//								}
//								details.setStockGrnDetails(stockGrnDetails);
//							}
						}
						
					}
					grnDetails.add(details);					
				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return grnDetails;
	}
	/**
	 * Transforming StockStatus Entity
	 * @param stockStatus
	 * @param detailForm
	 * @return StockStatus
	 */
	private ResponseStatus addUpdateStockStatus(GrnDetailForm detailForm) {
		ResponseStatus ResponseStatus = new ResponseStatus();
		try {
			StockStatusTO stockStatusTO = new StockStatusTO();
			if(!StringUtils.isNullOrEmpty(detailForm.getStockStatusId())){
				if(!StringUtils.isNullOrEmpty(detailForm.getStockStatusId())) {
					stockStatusTO.setId(detailForm.getStockStatusId());
				}
			}else{
				if(!StringUtils.isNullOrEmpty(detailForm.getBatchNo())) {
					stockStatusTO.setBatchNo(detailForm.getBatchNo());
				}
				if(!StringUtils.isNullOrEmpty(detailForm.getExpiryDate())) {
					stockStatusTO.setExpDate(detailForm.getExpiryDate());
				}
				if(!StringUtils.isNullOrEmpty(detailForm.getOldExpiryDate())) {
					stockStatusTO.setOldExpDate(detailForm.getOldExpiryDate());
				}
				if(!StringUtils.isNullOrEmpty(detailForm.getItemMasterId())) {
					stockStatusTO.setItemMasterId(detailForm.getItemMasterId());
				}
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getStockStatusId())) {
				stockStatusTO.setId(detailForm.getStockStatusId());
			}
			StockStatus existingStockStatus = stockStatusEJBIf.getStockStatusByGrnDetails(stockStatusTO);
			Boolean newStock = true;
			StockStatus newStockStatus = null;
			Double quantity = 0.00;
			Double weightedAvgPurchasePrice = 0.00;
			Double mrp = 0.00;
			if(existingStockStatus != null) {
				newStock = false;
				newStockStatus = existingStockStatus;
				quantity = newStockStatus.getQtyInStock();			
			}else {
				newStockStatus = new StockStatus();
			}
			Integer grnDetailPackSize = 0;
			Integer grnDetailPackQty = 0;
			Double grnDetailMrp = 0.00;
			Double grnDetailRate = 0.00;
			Double totalTaxPerPkgAmount = 0.00;
			
			if(!StringUtils.isNullOrEmpty(detailForm.getPackSize())) {
				grnDetailPackSize = Integer.parseInt(detailForm.getPackSize());
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getQuantityReceived())) {					
				grnDetailPackQty = Integer.parseInt(detailForm.getQuantityReceived());
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getTotalTaxAmount())) {
				totalTaxPerPkgAmount = Double.parseDouble(detailForm.getTotalTaxAmount()) /  grnDetailPackQty;
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getMrp())) {
				grnDetailMrp = Double.parseDouble(detailForm.getMrp());
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getRate())) {
				grnDetailRate = Double.parseDouble(detailForm.getRate());
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getQuantityReceived())) {					
				Double newQuantity = Double.parseDouble(detailForm.getQuantityReceived());
				Double oldQuantity = 0.00;
				if(!StringUtils.isNullOrEmpty(detailForm.getOldQuantity())) {
					oldQuantity = Double.parseDouble(detailForm.getOldQuantity());
				}
				Double oldPackSize = 0.00;
				if(!StringUtils.isNullOrEmpty(detailForm.getOldPackSize())) {
					oldPackSize = Double.parseDouble(detailForm.getOldPackSize());
				}
				if(newStock) {
					quantity = (grnDetailPackSize * newQuantity);
				}else if( !newStock 
						&& !StringUtils.isNullOrEmpty(detailForm.getId()) 
						&& !StringUtils.isNullOrEmpty(detailForm.getStatus()) 
						&& detailForm.getStatus().equals("I")) {
					quantity = quantity -  (oldQuantity * oldPackSize);
				}else {
					quantity = quantity -  (oldQuantity * oldPackSize);
					quantity = quantity + (grnDetailPackSize * newQuantity);
				}
			}
			if (grnDetailPackSize > 0) {
				weightedAvgPurchasePrice = grnDetailRate / grnDetailPackSize;
				mrp = (grnDetailMrp + totalTaxPerPkgAmount ) / grnDetailPackSize;
			}else {
				weightedAvgPurchasePrice = grnDetailRate;
				mrp = (grnDetailMrp + totalTaxPerPkgAmount);
			}
			DecimalFormat df = new DecimalFormat("#.##");      
			newStockStatus.setQtyInStock(quantity);
			newStockStatus.setWeightedAvgPurchasePrice(Double.valueOf(df.format(weightedAvgPurchasePrice)));
			newStockStatus.setMrp(Double.valueOf(df.format(mrp)));
			newStockStatus.setPackSize(grnDetailPackSize);
			
			if(!StringUtils.isNullOrEmpty(detailForm.getRate())) {
				newStockStatus.setAmountPerUnit(Double.parseDouble(detailForm.getRate()));
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getBatchNo())) {
				newStockStatus.setBatchNo(detailForm.getBatchNo());
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getDateOfRecInStore())) {
				newStockStatus.setDateOfReceive(MartUtilities.getDateFromStringWithMMM(detailForm.getDateOfRecInStore()));
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getExpiryDate())) {
				newStockStatus.setExpiryDate(MartUtilities.getDateFromStringWithMMM(detailForm.getExpiryDate()));
			}
			if(!StringUtils.isNullOrEmpty(detailForm.getItemMasterId())) {
				ItemMaster itemMaster = new ItemMaster();
				itemMaster.setId(Integer.parseInt(detailForm.getItemMasterId()));
				newStockStatus.setItemMaster(itemMaster);
			}	
			if(!StringUtils.isNullOrEmpty(detailForm.getMfgDate())) {
				newStockStatus.setMfgDate(MartUtilities.getDateFromStringWithMMM(detailForm.getMfgDate()));
			}			
			newStockStatus.setPackSize(null);			
			if(!StringUtils.isNullOrEmpty(detailForm.getStoreId())) {
				StoreMaster storeMaster = new StoreMaster();
				storeMaster.setId(Integer.parseInt(detailForm.getStoreId()));
				newStockStatus.setStoreMaster(storeMaster);
			}			
			newStockStatus.setStatus(StatusEnum.A);
			if(newStock) {
				ResponseStatus = stockStatusEJBIf.addStockStatus(newStockStatus);
			}else {
				ResponseStatus = stockStatusEJBIf.updateStockStatus(newStockStatus);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
			ResponseStatus.setStatus(false);
			ResponseStatus.setErrorMessage(exception.getMessage());
		}
		return ResponseStatus;
	}

	@Override
	public Response validateGrnDetails(GrnDetailForm grnDetailForm) {
		Map<String, String> responseObj = new HashMap<String, String>();
		try{
			
			
			
			
			if(grnDetailForm.getSimilarMrpCheck() != null 
					&& grnDetailForm.getSimilarMrpCheck()){
				
				StockStatusTO stockStatusTO = new StockStatusTO();
				if(grnDetailForm.getMrp() != null && grnDetailForm.getPackSize() != null){
					Double totalTaxAmt = 0.00;
					if(!StringUtils.isNullOrEmpty(grnDetailForm.getTotalTaxAmount()) 
						 && !StringUtils.isNullOrEmpty(grnDetailForm.getQuantityAccepted())	){
						totalTaxAmt = Double.parseDouble(grnDetailForm.getTotalTaxAmount()) / Integer.parseInt(grnDetailForm.getQuantityAccepted());
					}
					
					Double screenMrp = (Double.valueOf(grnDetailForm.getMrp()) + totalTaxAmt ) / Integer.parseInt(grnDetailForm.getPackSize());
					String mrp  = String.format("%.2f", screenMrp);
					stockStatusTO.setMrpNotEqual(mrp);
				}
				stockStatusTO.setItemMasterId(grnDetailForm.getItemMasterId());
				stockStatusTO.setQtyInStock("0");
				Collection<StockStatus> colStockStatus = stockStatusEJBIf.getStockStatus(stockStatusTO);
				if(colStockStatus != null && !colStockStatus.isEmpty()){
					StringBuilder grnUpdateMsg = new StringBuilder();
					grnUpdateMsg.append("<table class='newGrnDetMsg'><tr><td colspan='4' class='newGrnAlrtConfTop' >Careful another Entry of the same Item has the MRP for individual unit as</td></tr>");
					
					grnUpdateMsg.append("<tr class='newGrnHeadr'>");
					grnUpdateMsg.append("<td>Item No</td>");
					grnUpdateMsg.append("<td>MRP/Unit</td>");
					grnUpdateMsg.append("<td>Batch No</td>");
					grnUpdateMsg.append("<td>Expiry Date</td>");
					grnUpdateMsg.append("</tr>");
					
					for(StockStatus stockStatus : colStockStatus){
						
						String mrp  = String.format("%.2f", stockStatus.getMrp());
						
						grnUpdateMsg.append("<tr>");
						if(stockStatus.getItemMaster() != null){
							grnUpdateMsg.append("<td>"+stockStatus.getItemMaster().getItemCode()+"</td>");
						}else{
							grnUpdateMsg.append("<td> </td>");
						}
						grnUpdateMsg.append("<td>"+mrp+"</td>");
						grnUpdateMsg.append("<td>"+stockStatus.getBatchNo()+"</td>");
						if(stockStatus.getExpiryDate() != null){
							grnUpdateMsg.append("<td>"+MartUtilities.getStringDateInMMMFromDate(stockStatus.getExpiryDate())+"</td>");
						}else{
							grnUpdateMsg.append("<td> </td>");
						}
						grnUpdateMsg.append("</tr>");
					}
					
					grnUpdateMsg.append("<tr><td colspan='4' class='newGrnAlrtConf' >Do you want to continue? </td></tr>");
					grnUpdateMsg.append("</table>");
					responseObj.put("similarMrp", grnUpdateMsg.toString());
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					return builder.build();
					
				}
			}
			
			if(grnDetailForm.getSimilarExpCheck() != null 
					&& grnDetailForm.getSimilarExpCheck()){
				
				GrnDetailForm grnDetForm = new GrnDetailForm();
				grnDetForm.setBatchNo(grnDetailForm.getBatchNo());
				grnDetForm.setItemMasterId(grnDetailForm.getItemMasterId());
				if(!StringUtils.isNullOrEmpty(grnDetailForm.getExpiryDate())){
					String expDate = MartUtilities.getMysqlOnlyDateMMMGUIToDb(grnDetailForm.getExpiryDate());
					grnDetForm.setExpiryDateNotIn(expDate);
				}
				if(!StringUtils.isNullOrEmpty(grnDetailForm.getId())){
					grnDetForm.setIdNotIn(grnDetailForm.getId());
				}
				List<GrnDetails> lstGrnDetails = grnEJBIf.getGrnDetails(grnDetForm);
				if(lstGrnDetails != null && lstGrnDetails.size() > 0){
					
					StringBuilder grnUpdateMsg = new StringBuilder();
					
					grnUpdateMsg.append("<table class='newGrnDetMsg'><tr><td colspan='4' class='newGrnAlrtConfTop' >Please check the expiry date. Another GRN has the same batch with the follwing expiry date.</td></tr>");
					
					grnUpdateMsg.append("<tr class='newGrnHeadr' >");
					grnUpdateMsg.append("<td>Item No</td>");
					grnUpdateMsg.append("<td>GRN #</td>");
					grnUpdateMsg.append("<td>Batch No</td>");
					grnUpdateMsg.append("<td>Vendor</td>");
					grnUpdateMsg.append("<td>Expiry Date</td>");
					grnUpdateMsg.append("</tr>");
					
					for(GrnDetails grnDetails : lstGrnDetails){
						
						Grn grn = grnDetails.getGrnId();
						
						grnUpdateMsg.append("<tr>");
						if(grnDetails.getItemMaster() != null){
							grnUpdateMsg.append("<td>"+grnDetails.getItemMaster().getItemCode()+"</td>");
						}else{
							grnUpdateMsg.append("<td> </td>");
						}
						grnUpdateMsg.append("<td>"+grn.getGrnNo()+"</td>");
						grnUpdateMsg.append("<td>"+grnDetails.getBatchNo()+"</td>");
						if(grn.getVendorMaster() != null){
							grnUpdateMsg.append("<td>"+grn.getVendorMaster().getVendorName()+"</td>");
						}else{
							grnUpdateMsg.append("<td> </td>");
						}
						if(grnDetails.getExpiryDate() != null){
							grnUpdateMsg.append("<td>"+MartUtilities.getStringDateInMMMFromDate(grnDetails.getExpiryDate())+"</td>");
						}else{
							grnUpdateMsg.append("<td> </td>");
						}
						grnUpdateMsg.append("</tr>");
					}
					
					grnUpdateMsg.append("<tr><td colspan='4' class='newGrnAlrtConf' >Do you want to continue? </td></tr>");
					grnUpdateMsg.append("</table>");
					
					responseObj.put("similarExp", grnUpdateMsg.toString());
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					return builder.build();
				}
			}
			
			if(grnDetailForm.getSimilarItemCheck() != null 
					&& grnDetailForm.getSimilarItemCheck()){
				
				GrnDetailForm grnDetForm = new GrnDetailForm();
				grnDetForm.setBatchNo(grnDetailForm.getBatchNo());
				grnDetForm.setItemMasterId(grnDetailForm.getItemMasterId());
				if(!StringUtils.isNullOrEmpty(grnDetailForm.getId())){
					grnDetForm.setIdNotIn(grnDetailForm.getId());
				}
				List<GrnDetails> lstGrnDetails = grnEJBIf.getGrnDetails(grnDetForm);
				if(lstGrnDetails != null && lstGrnDetails.size() > 0){
					
					StringBuilder grnUpdateMsg = new StringBuilder();
					
					grnUpdateMsg.append("<table class='newGrnDetMsg'><tr><td colspan='4' class='newGrnAlrtConfTop' >The following Vendors are Selling the same Item at a Different Price.</td></tr>");
					
					grnUpdateMsg.append("<tr class='newGrnHeadr' >");
					grnUpdateMsg.append("<td>Item No</td>");
					grnUpdateMsg.append("<td>MRP/Unit</td>");
					grnUpdateMsg.append("<td>Batch No</td>");
					grnUpdateMsg.append("<td>Vendor</td>");
					grnUpdateMsg.append("<td>Expiry Date</td>");
					grnUpdateMsg.append("</tr>");
					
					for(GrnDetails grnDetails : lstGrnDetails){
						
						String batchNo = grnDetails.getBatchNo();
						VendorMaster vendor = grnDetails.getGrnId().getVendorMaster();
						String vendorName = vendor.getVendorName();
						Double perRate = grnDetails.getRate() / grnDetails.getPackSize();
						String rate  = String.format("%.2f", perRate);
						
						String screenBatchNo = grnDetailForm.getBatchNo();
						Double screenRate = Double.valueOf(grnDetailForm.getRate()) / Integer.parseInt(grnDetailForm.getPackSize());
						if(screenRate != perRate && batchNo.equalsIgnoreCase(screenBatchNo)){
							
							Grn grn = grnDetails.getGrnId();
							
							grnUpdateMsg.append("<tr>");
							if(grnDetails.getItemMaster() != null){
								grnUpdateMsg.append("<td>"+grnDetails.getItemMaster().getItemCode()+"</td>");
							}else{
								grnUpdateMsg.append("<td> </td>");
							}
							grnUpdateMsg.append("<td>"+rate+"</td>");
							grnUpdateMsg.append("<td>"+grnDetails.getBatchNo()+"</td>");
							if(grn.getVendorMaster() != null){
								grnUpdateMsg.append("<td>"+vendorName+"</td>");
							}else{
								grnUpdateMsg.append("<td> </td>");
							}
							if(grnDetails.getExpiryDate() != null){
								grnUpdateMsg.append("<td>"+MartUtilities.getStringDateInMMMFromDate(grnDetails.getExpiryDate())+"</td>");
							}else{
								grnUpdateMsg.append("<td> </td>");
							}
							grnUpdateMsg.append("</tr>");
		                }
					}
					
					grnUpdateMsg.append("<tr><td colspan='4' class='newGrnAlrtConf' >Do you want to continue? </td></tr>");
					grnUpdateMsg.append("</table>");
					
					responseObj.put("similarItem", grnUpdateMsg.toString());
					builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
					return builder.build();
				}
			}
			
			
		}catch(Exception e){
			
		}
		responseObj.put("Success", "All checks executed");
		builder = Response.status(Response.Status.OK).entity(responseObj);
		return builder.build();
	}
}
