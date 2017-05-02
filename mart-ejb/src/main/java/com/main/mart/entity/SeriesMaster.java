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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "SERIES_MASTER")
public class SeriesMaster implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(SeriesMaster.class);
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, unique=true)
    private Integer id;
    
    
    @Column(name = "CREATED_YEAR", length = 10)
    private String createdYear;
    
    @Column(name = "SERIES", length = 50)
    private String series;
    
    @Column(name = "SEQUENCE_NO")
    private Integer sequenceNo;
    
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Service serviceId;
    
    public SeriesMaster() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreatedYear() {
		return createdYear;
	}

	public void setCreatedYear(String createdYear) {
		this.createdYear = createdYear;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}


	public Service getServiceId() {
		return serviceId;
	}

	public void setServiceId(Service serviceId) {
		this.serviceId = serviceId;
	}

	
    
}
