/**
 * 
 */
package com.main.mart.common.dto;

import java.util.Collection;

/**
 * @author Hitesh
 *
 */
public class TypeTOs {
	private String draw;
	private String recordsTotal;
	private String recordsFiltered;
	private Collection<TypeTO> typeTOs;

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
	public Collection<TypeTO> getTypeTOs() {
		return typeTOs;
	}
	public void setTypeTOs(Collection<TypeTO> typeTOs) {
		this.typeTOs = typeTOs;
	}
	
}
