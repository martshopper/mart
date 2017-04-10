/**
 * 
 */
package com.mart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mart.utilities.StatusEnum;
import com.mart.utilities.ViewEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name = "CREDENTIALS")
public class Credentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
	 
	@Enumerated(EnumType.STRING)    
  	@Column(name = "ADD_RIGHTS",columnDefinition = "enum ('F', 'T') default 'F'")
    private ViewEnum add;
    
    @Enumerated(EnumType.STRING)    
  	@Column(name = "DELETE_RIGHTS",columnDefinition = "enum ('F', 'T') default 'F'")
    private ViewEnum delete;
    
    @Enumerated(EnumType.STRING)    
  	@Column(name = "FORM_PRINT",columnDefinition = "enum ('F', 'T') default 'F'")
    private ViewEnum formPrint;
    
    @JoinColumn(name = "MODULE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modules moduleId;
    
    @Enumerated(EnumType.STRING)    
  	@Column(name = "STATUS",columnDefinition = "enum ('A', 'I') default 'A'")
    private StatusEnum status;
    
    @Enumerated(EnumType.STRING)    
  	@Column(name = "UPDATE_RIGHTS",columnDefinition = "enum ('F', 'T') default 'F'")
    private ViewEnum update;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;
    
    @Enumerated(EnumType.STRING)    
  	@Column(name = "VIEW_RIGHTS",columnDefinition = "enum ('F', 'T') default 'F'")
    private ViewEnum view;
    
    @Enumerated(EnumType.STRING)    
  	@Column(name = "VIEW_REPORTS",columnDefinition = "enum ('F', 'T') default 'F'")
    private ViewEnum viewReports;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ViewEnum getAdd() {
		return add;
	}

	public void setAdd(ViewEnum add) {
		this.add = add;
	}

	public ViewEnum getDelete() {
		return delete;
	}

	public void setDelete(ViewEnum delete) {
		this.delete = delete;
	}

	public ViewEnum getFormPrint() {
		return formPrint;
	}

	public void setFormPrint(ViewEnum formPrint) {
		this.formPrint = formPrint;
	}

	public Modules getModuleId() {
		return moduleId;
	}

	public void setModuleId(Modules moduleId) {
		this.moduleId = moduleId;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public ViewEnum getUpdate() {
		return update;
	}

	public void setUpdate(ViewEnum update) {
		this.update = update;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public ViewEnum getView() {
		return view;
	}

	public void setView(ViewEnum view) {
		this.view = view;
	}

	public ViewEnum getViewReports() {
		return viewReports;
	}

	public void setViewReports(ViewEnum viewReports) {
		this.viewReports = viewReports;
	}

}
