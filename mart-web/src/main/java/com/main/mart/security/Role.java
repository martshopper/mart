 package com.main.mart.security;

import java.io.Serializable;

public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int roleId;
	
	private String roleName;
	public Role()
	{}
	public Role(int id, String roleName)
	{
		this.roleId = id;
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
