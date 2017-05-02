/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.mart.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.main.mart.utilities.ServiceCategoryEnum;


@Entity
@Table(name = "SERVICE", uniqueConstraints = {
@UniqueConstraint(columnNames = {"ID","SERVICE_CODE"})})
@XmlRootElement
public class Service implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, unique=true)
    private Integer id;
    @Column(name = "SERVICE_CODE", length = 50, unique=true)
    private String serviceCode;
    @Column(name = "SERVICE_NAME", length = 50)
    private String serviceName;
    @Column(name = "STATUS", length = 2)
    private String status;
    
    @Column(name="TYPE", columnDefinition = "enum ('PA','SR','IP','SC')", length=2)
    @Enumerated(EnumType.STRING)
    private ServiceCategoryEnum type;
    
    @JoinColumn(name="CATEGORY", referencedColumnName="id")
    @ManyToOne
    private TypeItems category;
    
    
    @Column(name = "VISTA_IEN")
    private Integer vistaIen;
    
    public Service() {
    }

    public Service(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

	public ServiceCategoryEnum getType() {
		return type;
	}

	public void setType(ServiceCategoryEnum type) {
		this.type = type;
	}

	public TypeItems getCategory() {
		return category;
	}

	public void setCategory(TypeItems category) {
		this.category = category;
	}

	public Integer getVistaIen() {
		return vistaIen;
	}

	public void setVistaIen(Integer vistaIen) {
		this.vistaIen = vistaIen;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.main.mart.entity.Service[ id=" + id + " ]";
    }
    
   
}
