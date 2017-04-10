/**
 * 
 */
package com.main.mart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
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

import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="CHARGE_SLIP")
public class ChargeSlip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique=true)
	private Integer id;
	
	//bi-directional many-to-one association to InventoryChargeSlipDetail
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "chargeSlipId", orphanRemoval = true ,fetch=FetchType.LAZY)
	private Collection<ChargeSlipDetail> chargeSlipDetails;

	@Column(name="CHARGE_SLIP_NO")
	private String chargeSlipNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY", referencedColumnName = "ID")
	private User createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE_TIME")
	private Date createdDateTime;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DEL_RECIEPT_ID", referencedColumnName = "ID")
	private Receipt delRecieptId;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="LAST_UPDATED_BY", referencedColumnName = "ID")
	private User lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED_DATE_TIME")
	private Date lastUpdatedDateTime;
	
	@Column(name="NO_OF_UPDATES")
	private Integer noOfUpdates;

	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="RECIEPT_ID", referencedColumnName = "ID")
	private Receipt receiptId;*/

	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "chargeSlip", fetch=FetchType.LAZY)
	private Collection<Refund> refundDetails;*/

	@Column(name="REMARKS" , columnDefinition = "TEXT")
	private String remarks;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", columnDefinition="enum('A','I')  default 'Y' ", length=1)
	private StatusEnum status;

	@Column(name="TOTAL_CHARGES")
	private Double totalCharges;
	
	@Column(name="TOTAL_DIS_AMT")
	private Double totalDisAmt;
	
	@Column(name="TOTAL_DIS_PER")
	private Double totalDisPer;
	
	@Column(name="TOTAL_INV_AMOUNT")
	private Double totalInvAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ChargeSlipDetail> getChargeSlipDetails() {
		return chargeSlipDetails;
	}

	public void setChargeSlipDetails(Collection<ChargeSlipDetail> chargeSlipDetails) {
		this.chargeSlipDetails = chargeSlipDetails;
	}

	public String getChargeSlipNo() {
		return chargeSlipNo;
	}

	public void setChargeSlipNo(String chargeSlipNo) {
		this.chargeSlipNo = chargeSlipNo;
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

	public Integer getNoOfUpdates() {
		return noOfUpdates;
	}

	public void setNoOfUpdates(Integer noOfUpdates) {
		this.noOfUpdates = noOfUpdates;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public Double getTotalDisAmt() {
		return totalDisAmt;
	}

	public void setTotalDisAmt(Double totalDisAmt) {
		this.totalDisAmt = totalDisAmt;
	}

	public Double getTotalDisPer() {
		return totalDisPer;
	}

	public void setTotalDisPer(Double totalDisPer) {
		this.totalDisPer = totalDisPer;
	}

	public Double getTotalInvAmount() {
		return totalInvAmount;
	}

	public void setTotalInvAmount(Double totalInvAmount) {
		this.totalInvAmount = totalInvAmount;
	}

}
