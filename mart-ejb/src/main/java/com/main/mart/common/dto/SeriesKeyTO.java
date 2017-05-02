/**
 * 
 */
package com.main.mart.common.dto;

/**
 * @author Hitesh
 *
 */
public class SeriesKeyTO {
	private String seriesKey;
	private Integer seriesMasterId;
	private String errorMessage;
	public String getSeriesKey() {
		return seriesKey;
	}
	public void setSeriesKey(String seriesKey) {
		this.seriesKey = seriesKey;
	}
	public Integer getSeriesMasterId() {
		return seriesMasterId;
	}
	public void setSeriesMasterId(Integer seriesMasterId) {
		this.seriesMasterId = seriesMasterId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
