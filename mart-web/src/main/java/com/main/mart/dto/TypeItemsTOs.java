/**
 * 
 */
package com.main.mart.dto;

import java.util.Collection;

import com.main.mart.common.dto.TypeItemsTO;

/**
 * @author Hitesh
 *
 */
public class TypeItemsTOs {
	private String draw;
	private String recordsTotal;
	private String recordsFiltered;
	private Collection<TypeItemsTO> typeItemsTOs;
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
	public Collection<TypeItemsTO> getTypeItemsTOs() {
		return typeItemsTOs;
	}
	public void setTypeItemsTOs(Collection<TypeItemsTO> typeItemsTOs) {
		this.typeItemsTOs = typeItemsTOs;
	}
}
