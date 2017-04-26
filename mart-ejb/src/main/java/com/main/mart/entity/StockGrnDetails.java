/**
 * 
 */
package com.main.mart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="STOCK_GRN_DETAILS")
public class StockGrnDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="STOCK_STATUS_ID", referencedColumnName = "ID", nullable=false)
	private StockStatus stockStatusId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GRN_DETAILS_ID", referencedColumnName = "ID", nullable=false)
	private GrnDetails grnDetailsId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StockStatus getStockStatusId() {
		return stockStatusId;
	}

	public void setStockStatusId(StockStatus stockStatusId) {
		this.stockStatusId = stockStatusId;
	}

	public GrnDetails getGrnDetailsId() {
		return grnDetailsId;
	}

	public void setGrnDetailsId(GrnDetails grnDetailsId) {
		this.grnDetailsId = grnDetailsId;
	}

}
