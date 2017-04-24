/**
 * 
 */
package com.main.mart.dto;

import java.util.List;

import com.main.mart.common.dto.ItemTypeTO;

/**
 * @author Hitesh
 *
 */
public class ItemTypeTOs {
	private String draw;
	private String recordsTotal;
	private String recordsFiltered;
	public List<ItemTypeTO> itemTypes;
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
	public List<ItemTypeTO> getItemTypes() {
		return itemTypes;
	}
	public void setItemTypes(List<ItemTypeTO> itemTypes) {
		this.itemTypes = itemTypes;
	}
}
