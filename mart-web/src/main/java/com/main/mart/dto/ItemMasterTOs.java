/**
 * 
 */
package com.main.mart.dto;

import java.util.List;

import com.main.mart.common.dto.ItemMasterTO;

/**
 * @author Hitesh
 *
 */
public class ItemMasterTOs {
	private String draw;
	private String recordsTotal;
	private String recordsFiltered;
	private List<ItemMasterTO> itemMasters;
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
	public List<ItemMasterTO> getItemMasters() {
		return itemMasters;
	}
	public void setItemMasters(List<ItemMasterTO> itemMasters) {
		this.itemMasters = itemMasters;
	}
}
