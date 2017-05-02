package com.main.mart.common.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GrnForm {
	
	private String refNo;
//	@NotNull(message="Please select Service")
//	@Size(min=1, message = "Please select Service")
	private String categoryId;
	private String categoryName;
	private String categoryCode;
	@NotNull(message="Please select Vendor")
	@Size(min=1, message = "Please select Vendor")
	private String vendorId;
	private String vendorName;
	@NotNull(message="Please select Store")
	@Size(min=1, message = "Please select Store")
	private String storeId;
	private String storeName;
	@NotNull(message="Please enter Invoice Number")
	@Size(min=1, message = "Please enter Invoice Number")
	private String invoiceRefNo;
	@NotNull(message="Please enter Invoice Date")
	@Size(min=1, message = "Please enter Invoice Date")
	private String invoiceDate;
	@NotNull(message="Please enter Receipt Date")
	@Size(min=1, message = "Please enter Receipt Date")
	private String dateOfRecInStore;
	private String createdDateTime;
	private String updatedDateTime;
	private String createdById;
	private String createdByName;
	private String updatedById;
	private String updatedByName;
	private String totalAmount;
	private String totalAmountWithTax;
	private String status;
	private String invoiceFromDate;
	private String invoiceToDate;
	private String receiptFromDate;
	private String receiptToDate;
	private String id;
	
	private String itemTypeId;
	
	
	List<GrnDetailForm> lstGrnDetailForm;
	
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getInvoiceRefNo() {
		return invoiceRefNo;
	}
	public void setInvoiceRefNo(String invoiceRefNo) {
		this.invoiceRefNo = invoiceRefNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getDateOfRecInStore() {
		return dateOfRecInStore;
	}
	public void setDateOfRecInStore(String dateOfRecInStore) {
		this.dateOfRecInStore = dateOfRecInStore;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(String updatedById) {
		this.updatedById = updatedById;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInvoiceFromDate() {
		return invoiceFromDate;
	}
	public void setInvoiceFromDate(String invoiceFromDate) {
		this.invoiceFromDate = invoiceFromDate;
	}
	public String getInvoiceToDate() {
		return invoiceToDate;
	}
	public void setInvoiceToDate(String invoiceToDate) {
		this.invoiceToDate = invoiceToDate;
	}
	public String getReceiptFromDate() {
		return receiptFromDate;
	}
	public void setReceiptFromDate(String receiptFromDate) {
		this.receiptFromDate = receiptFromDate;
	}
	public String getReceiptToDate() {
		return receiptToDate;
	}
	public void setReceiptToDate(String receiptToDate) {
		this.receiptToDate = receiptToDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<GrnDetailForm> getLstGrnDetailForm() {
		return lstGrnDetailForm;
	}
	public void setLstGrnDetailForm(List<GrnDetailForm> lstGrnDetailForm) {
		this.lstGrnDetailForm = lstGrnDetailForm;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getTotalAmountWithTax() {
		return totalAmountWithTax;
	}
	public void setTotalAmountWithTax(String totalAmountWithTax) {
		this.totalAmountWithTax = totalAmountWithTax;
	}
	
	
}
