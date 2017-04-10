/**
 * 
 */
package com.main.mart.entity;

import java.io.Serializable;

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

import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="CHARGE_SLIP_DETAIL")
public class ChargeSlipDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique=true)
	private Integer id;
	
	@Column(name="AMOUNT")
	private Double amount;
	
	@Column(name="BALANCE_AMOUNT")
	private Double balanceAmount;

	@JoinColumn(name = "CHARGE_SLIP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ChargeSlip chargeSlipId;

	@Column(name="DISCOUNT_AMT")
	private Double discountAmt;

	@Column(name="DIS_PERCENT")
	private Double disPercent;

	//bi-directional many-to-one association to ItemMaster
	@ManyToOne
	@JoinColumn(name="ITEM_MASTER_ID", referencedColumnName = "ID")
	private ItemMaster itemMaster;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", columnDefinition="enum('A','I')", length=1)
	private StatusEnum status;

	//bi-directional many-to-one association to StockStatus
	@ManyToOne
	@JoinColumn(name="STOCK_STATUS_ID", referencedColumnName = "ID")
	private StockStatus stockStatus;

	@Column(name="UNIT", length=11)
	private Integer unit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public ChargeSlip getChargeSlipId() {
		return chargeSlipId;
	}

	public void setChargeSlipId(ChargeSlip chargeSlipId) {
		this.chargeSlipId = chargeSlipId;
	}

	public Double getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(Double discountAmt) {
		this.discountAmt = discountAmt;
	}

	public Double getDisPercent() {
		return disPercent;
	}

	public void setDisPercent(Double disPercent) {
		this.disPercent = disPercent;
	}

	public ItemMaster getItemMaster() {
		return itemMaster;
	}

	public void setItemMaster(ItemMaster itemMaster) {
		this.itemMaster = itemMaster;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public StockStatus getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(StockStatus stockStatus) {
		this.stockStatus = stockStatus;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

}
