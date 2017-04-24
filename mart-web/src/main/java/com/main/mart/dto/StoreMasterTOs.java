/**
 * 
 */
package com.main.mart.dto;

import java.util.List;

import com.main.mart.common.dto.StoreMasterTO;

/**
 * @author Hitesh
 *
 */
public class StoreMasterTOs {
	private String draw;
	private String recordsTotal;
	private String recordsFiltered;
	private List<StoreMasterTO> storeMaster;
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(String recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public String getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(String recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<StoreMasterTO> getStoreMaster() {
		return storeMaster;
	}
	public void setStoreMaster(List<StoreMasterTO> storeMaster) {
		this.storeMaster = storeMaster;
	}
}
