/**
 * 
 */
package com.main.mart.entity;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="ITEM_TYPE")
public class ItemType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique=true)
	private Integer id;

	//bi-directional many-to-one association to ItemMaster
	@OneToMany(mappedBy="itemTypeId")
	private List<ItemMaster> itemMasters;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", columnDefinition="enum('A','I')", length=1, nullable = false)
	private StatusEnum status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TYPE_CATEGORY_ID", referencedColumnName = "ID", nullable = false)
	private TypeItems typeCategoryId;

	@Column(name="TYPE_CODE", nullable = false, unique=true)
	private String typeCode;

	@Column(name="TYPE_NAME", nullable = false)
	private String typeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ItemMaster> getItemMasters() {
		return itemMasters;
	}

	public void setItemMasters(List<ItemMaster> itemMasters) {
		this.itemMasters = itemMasters;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public TypeItems getTypeCategoryId() {
		return typeCategoryId;
	}

	public void setTypeCategoryId(TypeItems typeCategoryId) {
		this.typeCategoryId = typeCategoryId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
