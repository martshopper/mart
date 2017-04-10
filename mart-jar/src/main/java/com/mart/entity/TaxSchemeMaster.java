/**
 * 
 */
package com.mart.entity;

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

import com.mart.utilities.BasedOnEnum;
import com.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="TAX_SCHEME_MASTER")
public class TaxSchemeMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
	
	@JoinColumn(name = "TAX_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private TaxMaster taxId;
	
	@Column(name="SCHEME_CODE", nullable = false)
	private String schemeCode;

	@Column(name="SCHEME_NAME", nullable = false)
	private String schemeName;
	
	@Column(name="APPLY_ORDER")
	private String applyOrder;
	
	@Column(name="TAX_PERCENTAGE")
	private Double taxPercentage;
	
	@Enumerated(EnumType.STRING)    
	@Column(name = "BASED_ON",columnDefinition = "enum ('MRP','CP') default 'MRP'")
	private BasedOnEnum basedOn;
	
	@Enumerated(EnumType.STRING)    
	@Column(name = "STATUS",columnDefinition = "enum ('A', 'I') default 'A'", nullable = false, length = 1)
	private StatusEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TaxMaster getTaxId() {
		return taxId;
	}

	public void setTaxId(TaxMaster taxId) {
		this.taxId = taxId;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getApplyOrder() {
		return applyOrder;
	}

	public void setApplyOrder(String applyOrder) {
		this.applyOrder = applyOrder;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public BasedOnEnum getBasedOn() {
		return basedOn;
	}

	public void setBasedOn(BasedOnEnum basedOn) {
		this.basedOn = basedOn;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
