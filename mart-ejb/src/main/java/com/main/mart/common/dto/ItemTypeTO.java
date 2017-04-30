/**
 * 
 */
package com.main.mart.common.dto;

/**
 * @author Hitesh
 *
 */
public class ItemTypeTO {
	private String id;
	private String typeCategoryId;
	private String typeCategoryCode;
	private String typeCategoryDes;
	private String status;
	private String typeCode;
	private String typeName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeCategoryId() {
		return typeCategoryId;
	}
	public void setTypeCategoryId(String typeCategoryId) {
		this.typeCategoryId = typeCategoryId;
	}
	public String getTypeCategoryCode() {
		return typeCategoryCode;
	}
	public void setTypeCategoryCode(String typeCategoryCode) {
		this.typeCategoryCode = typeCategoryCode;
	}
	public String getTypeCategoryDes() {
		return typeCategoryDes;
	}
	public void setTypeCategoryDes(String typeCategoryDes) {
		this.typeCategoryDes = typeCategoryDes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
