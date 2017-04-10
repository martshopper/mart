/**
 * 
 */
package com.mart.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="GRN_DETAILS")
public class GrnDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
	
	@Column(name="BATCH_NO")
	private String batchNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GRN_ID", referencedColumnName = "ID")
	private Grn grnId;

	//bi-directional many-to-one association to ItemMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_MASTER_ID", referencedColumnName = "ID")
	private ItemMaster itemMaster;

	@Temporal(TemporalType.DATE)
	@Column(name="MFG_DATE")
	private Date mfgDate;

	@Column(name="MRP")
	private Double mrp;

	@Column(name="PACK_SIZE")
	private Integer packSize;

	@Column(name="QUANTITY_ACCEPTED")
	private Double quantityAccepted;

	@Column(name="QUANTITY_RECEIVED")
	private Double quantityReceived;

	@Column(name="RATE")
	private Double rate;

	@Enumerated(EnumType.STRING)    
	@Column(name = "STATUS",columnDefinition = "enum ('A', 'I') default 'A'")
	private StatusEnum status;
	
	@Column(name="TOTAL_TAX_AMOUNT")
	private Double totalTaxAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Grn getGrnId() {
		return grnId;
	}

	public void setGrnId(Grn grnId) {
		this.grnId = grnId;
	}

	public ItemMaster getItemMaster() {
		return itemMaster;
	}

	public void setItemMaster(ItemMaster itemMaster) {
		this.itemMaster = itemMaster;
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

	public Double getQuantityAccepted() {
		return quantityAccepted;
	}

	public void setQuantityAccepted(Double quantityAccepted) {
		this.quantityAccepted = quantityAccepted;
	}

	public Double getQuantityReceived() {
		return quantityReceived;
	}

	public void setQuantityReceived(Double quantityReceived) {
		this.quantityReceived = quantityReceived;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

}
