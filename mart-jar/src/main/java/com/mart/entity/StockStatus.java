/**
 * 
 */
package com.mart.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="STOCK_STATUS")
public class StockStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique=true)
	private Integer id;
	
	@Column(name="AMOUNT_PER_UNIT")
	private Double amountPerUnit;

	@Column(name="BATCH_NO", length=50)
	private String batchNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY", referencedColumnName = "ID")
	private User createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATETIME")
	private Date createdDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_OF_RECEIVE")
	private Date dateOfReceive;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;

	@Column(name="INTERNAL_TRANSFER_PRICE")
	private Double internalTransferPrice;

	//bi-directional many-to-one association to InventoryChargeSlipDetail
	@OneToMany(mappedBy="stockStatus")
	private List<ChargeSlipDetail> inventoryChargeSlipDetails;

	//bi-directional many-to-one association to ItemMaster
	@ManyToOne
	@JoinColumn(name="ITEM_MASTER_ID", referencedColumnName = "ID")
	private ItemMaster itemMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="LAST_UPDATED_BY", referencedColumnName = "ID")
	private User lastUpdatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED_DATETIME")
	private Date lastUpdatedDateTime;

	@Temporal(TemporalType.DATE)
	@Column(name="MFG_DATE")
	private Date mfgDate;

	@Column(name="MRP")
	private Double mrp;

	@Column(name="PACK_SIZE", length=11)
	private Integer packSize;

	@Column(name="QTY_IN_STOCK")
	private Double qtyInStock;

	@Column(name="SELLING_PRICE")
	private Double sellingPrice;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", columnDefinition="enum('A','I')", length=1)
	private StatusEnum status;

	//bi-directional many-to-one association to StoreMaster
	@ManyToOne
	@JoinColumn(name="STORE_MASTER_ID", referencedColumnName = "ID")
	private StoreMaster storeMaster;

	@Column(name="WEIGHTED_AVG_PURCHASE_PRICE")
	private Double weightedAvgPurchasePrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmountPerUnit() {
		return amountPerUnit;
	}

	public void setAmountPerUnit(Double amountPerUnit) {
		this.amountPerUnit = amountPerUnit;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getDateOfReceive() {
		return dateOfReceive;
	}

	public void setDateOfReceive(Date dateOfReceive) {
		this.dateOfReceive = dateOfReceive;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getInternalTransferPrice() {
		return internalTransferPrice;
	}

	public void setInternalTransferPrice(Double internalTransferPrice) {
		this.internalTransferPrice = internalTransferPrice;
	}

	public List<ChargeSlipDetail> getInventoryChargeSlipDetails() {
		return inventoryChargeSlipDetails;
	}

	public void setInventoryChargeSlipDetails(List<ChargeSlipDetail> inventoryChargeSlipDetails) {
		this.inventoryChargeSlipDetails = inventoryChargeSlipDetails;
	}

	public ItemMaster getItemMaster() {
		return itemMaster;
	}

	public void setItemMaster(ItemMaster itemMaster) {
		this.itemMaster = itemMaster;
	}

	public User getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Integer getPackSize() {
		return packSize;
	}

	public void setPackSize(Integer packSize) {
		this.packSize = packSize;
	}

	public Double getQtyInStock() {
		return qtyInStock;
	}

	public void setQtyInStock(Double qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public StoreMaster getStoreMaster() {
		return storeMaster;
	}

	public void setStoreMaster(StoreMaster storeMaster) {
		this.storeMaster = storeMaster;
	}

	public Double getWeightedAvgPurchasePrice() {
		return weightedAvgPurchasePrice;
	}

	public void setWeightedAvgPurchasePrice(Double weightedAvgPurchasePrice) {
		this.weightedAvgPurchasePrice = weightedAvgPurchasePrice;
	}

}
