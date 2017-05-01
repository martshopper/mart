/**
 * 
 */
package com.main.mart.common.dto;

/**
 * @author Hitesh
 *
 */
public class StoreMasterTO {
	private String id;
	private String location;
	private String status;
	private String storeCode;
	private String storeName;
	private String storeTypeId;
	private String storeType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public String getStoreTypeId() {
		return storeTypeId;
	}
	public void setStoreTypeId(String storeTypeId) {
		this.storeTypeId = storeTypeId;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
}
