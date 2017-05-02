/**
 * 
 */
package com.main.mart.common.dto;

/**
 * @author Albin
 *
 */
public class StockStatusTO {
	
	private String id;
	private String itemMasterId;
	private String itemCode;
	private String itemName;
	private String itemTypeId;
	private String itemTypeCode;
	private String itemTypeName;
	private String storeMasterId;
	private String qtyInStock;
	private String batchNo;
	private String mfgDate;
	private String expDate;
	private String dateOfReptInStore;
	private String packSize;
	private String amtPerUnit;
	private String mrp;
	private String mrpNotEqual;
	private String weightedAvgPurPrice;
	private String internalTransferPrice;
	private String sellingPrice;
	private String createdDateTime;
	private String createdBy;
	private String lastUpdatedBy;
	private String lastUpdatedDateTime;
	private String lastGrnId;
	private String status;
	private Integer limit;
	private String oldExpDate;
	private String reportFlag;
	private String itemNameReport;
	private String itemCodeReport;
	private Double rateAmount;
	private Double mrpAmount;
	private Double totalAmount;
	private Double quantity;
	private String storeId;
	private String storeName;
	private String storeCode;
	private String formatType;
	private boolean stockQtyCheck;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemMasterId() {
		return itemMasterId;
	}
	public void setItemMasterId(String itemMasterId) {
		this.itemMasterId = itemMasterId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getStoreMasterId() {
		return storeMasterId;
	}
	public void setStoreMasterId(String storeMasterId) {
		this.storeMasterId = storeMasterId;
	}
	public String getQtyInStock() {
		return qtyInStock;
	}
	public void setQtyInStock(String qtyInStock) {
		this.qtyInStock = qtyInStock;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getDateOfReptInStore() {
		return dateOfReptInStore;
	}
	public void setDateOfReptInStore(String dateOfReptInStore) {
		this.dateOfReptInStore = dateOfReptInStore;
	}
	public String getPackSize() {
		return packSize;
	}
	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}
	public String getAmtPerUnit() {
		return amtPerUnit;
	}
	public void setAmtPerUnit(String amtPerUnit) {
		this.amtPerUnit = amtPerUnit;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getWeightedAvgPurPrice() {
		return weightedAvgPurPrice;
	}
	public void setWeightedAvgPurPrice(String weightedAvgPurPrice) {
		this.weightedAvgPurPrice = weightedAvgPurPrice;
	}
	public String getInternalTransferPrice() {
		return internalTransferPrice;
	}
	public void setInternalTransferPrice(String internalTransferPrice) {
		this.internalTransferPrice = internalTransferPrice;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}
	public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}
	public String getLastGrnId() {
		return lastGrnId;
	}
	public void setLastGrnId(String lastGrnId) {
		this.lastGrnId = lastGrnId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getItemTypeCode() {
		return itemTypeCode;
	}
	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public String getOldExpDate() {
		return oldExpDate;
	}
	public void setOldExpDate(String oldExpDate) {
		this.oldExpDate = oldExpDate;
	}
	public String getMrpNotEqual() {
		return mrpNotEqual;
	}
	public void setMrpNotEqual(String mrpNotEqual) {
		this.mrpNotEqual = mrpNotEqual;
	}
	public String getReportFlag() {
		return reportFlag;
	}
	public void setReportFlag(String reportFlag) {
		this.reportFlag = reportFlag;
	}
	public String getItemNameReport() {
		return itemNameReport;
	}
	public void setItemNameReport(String itemNameReport) {
		this.itemNameReport = itemNameReport;
	}
	public String getItemCodeReport() {
		return itemCodeReport;
	}
	public void setItemCodeReport(String itemCodeReport) {
		this.itemCodeReport = itemCodeReport;
	}
	public Double getRateAmount() {
		return rateAmount;
	}
	public void setRateAmount(Double rateAmount) {
		this.rateAmount = rateAmount;
	}
	public Double getMrpAmount() {
		return mrpAmount;
	}
	public void setMrpAmount(Double mrpAmount) {
		this.mrpAmount = mrpAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
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
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getFormatType() {
		return formatType;
	}
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}


	public boolean isStockQtyCheck() {
		return stockQtyCheck;
	}
	public void setStockQtyCheck(boolean stockQtyCheck) {
		this.stockQtyCheck = stockQtyCheck;
	}


	
}
