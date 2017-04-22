package com.main.mart.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

import javax.security.auth.Subject;

public class SecurityPrincipal implements Principal,Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Collection<Role> colRole;
	private Subject subj;	
	public SecurityPrincipal(String name) {
		this.username = name;
	}
	public SecurityPrincipal(String name, String verifyCode, Collection<Role> colRole) {
		this.username = name;
		this.password = verifyCode;
		this.colRole = colRole;
	}
	
	
	@Override
	public String getName() {
		return username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getColRole() {
		return colRole;
	}
	public void setColRole(Collection<Role> colRole) {
		this.colRole = colRole;
	}
	public Subject getSubj() {
		return subj;
	}
	public void setSubj(Subject subj) {
		this.subj = subj;
	}
	
}
