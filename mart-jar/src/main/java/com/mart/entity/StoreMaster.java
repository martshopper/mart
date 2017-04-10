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

import com.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="STORE_MASTER")
public class StoreMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique=true)
	private Integer id;

	@Column(name="LOCATION", length=255)
	private String location;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", columnDefinition="enum('A','I')", nullable=false)
	private StatusEnum status;

	@Column(name="STORE_CODE", length=50, nullable=false)
	private String storeCode;

	@Column(name="STORE_NAME", length=100)
	private String storeName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="STORE_TYPE_ID", referencedColumnName = "ID", nullable=false)
	private TypeItems storeTypeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public TypeItems getStoreTypeId() {
		return storeTypeId;
	}

	public void setStoreTypeId(TypeItems storeTypeId) {
		this.storeTypeId = storeTypeId;
	}

}
