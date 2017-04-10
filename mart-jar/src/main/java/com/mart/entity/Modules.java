/**
 * 
 */
package com.mart.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mart.utilities.ModuleTypeEnum;
import com.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name = "MODULES")
public class Modules implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
	@Column(name = "CLASS_NAME")
    private String className;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleId" , fetch=FetchType.LAZY)
    private Collection<Credentials> colCredentials;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "FLAG",columnDefinition = "enum ('C', 'A', 'B') default 'B'", nullable = false)
    private ModuleTypeEnum flag;
    
    @Column(name = "MODULE_CODE", length = 254, unique=true)
    private String moduleCode;
    
    @Column(name = "MODULE_NAME", length = 254)
    private String moduleName;
    
    @Enumerated(EnumType.STRING)    
	@Column(name = "STATUS",columnDefinition = "enum ('A', 'I') default 'A'")
    private StatusEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Collection<Credentials> getColCredentials() {
		return colCredentials;
	}

	public void setColCredentials(Collection<Credentials> colCredentials) {
		this.colCredentials = colCredentials;
	}

	public ModuleTypeEnum getFlag() {
		return flag;
	}

	public void setFlag(ModuleTypeEnum flag) {
		this.flag = flag;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
