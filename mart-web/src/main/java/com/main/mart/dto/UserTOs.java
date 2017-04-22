/**
 * 
 */
package com.main.mart.dto;

import java.util.Collection;

import com.main.mart.common.dto.UserTO;

/**
 * @author Hitesh
 *
 */
public class UserTOs {
	private String draw;
	private String recordsTotal;
	private String recordsFiltered;
	private Collection<UserTO> userTOs;
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
	public Collection<UserTO> getUserTOs() {
		return userTOs;
	}
	public void setUserTOs(Collection<UserTO> userTOs) {
		this.userTOs = userTOs;
	}
}
