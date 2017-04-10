/**
 * 
 */
package com.main.mart.entity;

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

import com.main.mart.utilities.CommonEnum;
import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="ITEM_MASTER")
public class ItemMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique=true)
	private Integer id;
	
	@JoinColumn(name="CREATED_BY", referencedColumnName="ID")
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
	private User createdBy;

	@Column(name="CREATED_DATETIME", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;

	@Column(name="DISPENSING_UNIT")
	private String dispensingUnit;

	@Column(name="GENERIC_NAME")
	private String genericName;

	@Column(name="ITEM_CODE", nullable = false, unique=true)
	private String itemCode;

	@Column(name="ITEM_NAME", nullable = false)
	private String itemName;

	@Column(name="REMARKS", columnDefinition="LONGTEXT")
	private String remarks;

	//bi-directional many-to-one association to InventoryChargeSlipDetail
	@OneToMany(mappedBy="itemMaster")
	private List<ChargeSlipDetail> inventoryChargeSlipDetails;

	//bi-directional many-to-one association to ItemType
	@ManyToOne
	@JoinColumn(name="ITEM_TYPE_ID", nullable = false)
	private ItemType itemTypeId;

	//bi-directional many-to-one association to StockStatus
	@OneToMany(mappedBy="itemMaster")
	private List<StockStatus> stockStatuses;
	
	@Column(name="SHORT_NAME")
	private String shortName;
	
	@JoinColumn(name = "TAX_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private TaxMaster taxId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", columnDefinition="enum('A','I')", length=1, nullable = false)
	private StatusEnum status;

	@Enumerated(EnumType.STRING)
	@Column(name="TAXABLE",  columnDefinition="enum('Y','N')", length=1)
	private CommonEnum taxable;
	
	@JoinColumn(name = "UNIT_OF_MEASURE", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private TypeItems unitOfMeasure;
	
	@JoinColumn(name="UPDATED_BY", referencedColumnName="ID")
    @ManyToOne(fetch=FetchType.LAZY)
	private User updatedBy;
	
	@Column(name="UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedDateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDispensingUnit() {
		return dispensingUnit;
	}

	public void setDispensingUnit(String dispensingUnit) {
		this.dispensingUnit = dispensingUnit;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<ChargeSlipDetail> getInventoryChargeSlipDetails() {
		return inventoryChargeSlipDetails;
	}

	public void setInventoryChargeSlipDetails(List<ChargeSlipDetail> inventoryChargeSlipDetails) {
		this.inventoryChargeSlipDetails = inventoryChargeSlipDetails;
	}

	public ItemType getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(ItemType itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public List<StockStatus> getStockStatuses() {
		return stockStatuses;
	}

	public void setStockStatuses(List<StockStatus> stockStatuses) {
		this.stockStatuses = stockStatuses;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public TaxMaster getTaxId() {
		return taxId;
	}

	public void setTaxId(TaxMaster taxId) {
		this.taxId = taxId;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public CommonEnum getTaxable() {
		return taxable;
	}

	public void setTaxable(CommonEnum taxable) {
		this.taxable = taxable;
	}

	public TypeItems getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(TypeItems unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
}
