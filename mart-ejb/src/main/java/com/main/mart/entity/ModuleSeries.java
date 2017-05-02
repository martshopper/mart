/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.mart.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "MODULE_SERIES")
public class ModuleSeries implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, unique=true)
    private Integer id;
    
    @JoinColumn(name = "MODULE_ID", referencedColumnName = "ID", nullable=false,  unique=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Modules moduleId;
    
    @JoinColumn(name = "SERIES_MASTER_ID", referencedColumnName = "ID", nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private SeriesMaster seriesMasterId;
    
    public ModuleSeries() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Modules getModuleId() {
		return moduleId;
	}

	public void setModuleId(Modules moduleId) {
		this.moduleId = moduleId;
	}

	public SeriesMaster getSeriesMasterId() {
		return seriesMasterId;
	}

	public void setSeriesMasterId(SeriesMaster seriesMasterId) {
		this.seriesMasterId = seriesMasterId;
	}

}
