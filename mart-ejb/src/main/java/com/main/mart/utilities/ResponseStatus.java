/**
 * 
 */
package com.main.mart.utilities;

/**
 * @author Hitesh
 *
 */
public class ResponseStatus {
	private String errorMessage;
	private Integer persistingId;
	private Boolean status;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getPersistingId() {
		return persistingId;
	}
	public void setPersistingId(Integer persistingId) {
		this.persistingId = persistingId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
