/**
 * 
 */
package com.main.mart.entity;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="TYPE_ITEMS")
public class TypeItems implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "ID", nullable = false, unique=true)
	 private Integer id;
	 
	 @Column(name = "CODE", length = 20, nullable = false)
	 private String code;
	 
	 @Lob
	 @Column(name = "DESCRIPTION", columnDefinition="TEXT")
	 private String description;
	 
	 @Enumerated(EnumType.STRING)
	 @Column(name="STATUS", nullable=false)
	 private StatusEnum statusEnum;
	 
	 @Lob
	 @Column(name = "COMMENTS", columnDefinition="TEXT")
	 private String comments;
	    
	 @Column(name = "HTML_COMMENTS", length = 250)
	 private String htmlComments;
	   
	 @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID", nullable = false)
	 @ManyToOne(fetch = FetchType.LAZY)
	 private Type typeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getHtmlComments() {
		return htmlComments;
	}

	public void setHtmlComments(String htmlComments) {
		this.htmlComments = htmlComments;
	}

	public Type getTypeId() {
		return typeId;
	}

	public void setTypeId(Type typeId) {
		this.typeId = typeId;
	}
}
