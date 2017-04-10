/**
 * 
 */
package com.main.mart.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="TAX_MASTER")
public class TaxMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

	@Column(name="TAX_CODE", nullable = false)
	private String taxCode;

	@Column(name="TAX_DESCRIPTION", nullable = false, length=250)
	private String taxDescription;

	@Enumerated(EnumType.STRING)    
	@Column(name = "STATUS",columnDefinition = "enum ('A', 'I') default 'A'", nullable = false, length = 1)
	private StatusEnum status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="taxId", fetch=FetchType.LAZY)
	private Collection<TaxSchemeMaster> taxSchemeMasters;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxDescription() {
		return taxDescription;
	}

	public void setTaxDescription(String taxDescription) {
		this.taxDescription = taxDescription;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Collection<TaxSchemeMaster> getTaxSchemeMasters() {
		return taxSchemeMasters;
	}

	public void setTaxSchemeMasters(Collection<TaxSchemeMaster> taxSchemeMasters) {
		this.taxSchemeMasters = taxSchemeMasters;
	}

}
