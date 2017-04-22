/**
 * 
 */
package com.main.mart.dto;

import java.util.Collection;

import com.main.mart.common.dto.OwnerTO;

/**
 * @author Hitesh
 *
 */
public class OwnerTOs {
	private String draw;
	private String recordsTotal;
	private String recordsFiltered;
	private Collection<OwnerTO> owners;
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
	public Collection<OwnerTO> getOwners() {
		return owners;
	}
	public void setOwners(Collection<OwnerTO> owners) {
		this.owners = owners;
	}
}
