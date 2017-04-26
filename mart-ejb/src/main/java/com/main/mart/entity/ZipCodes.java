/**
 * 
 */
package com.main.mart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="ZIP_CODES")
public class ZipCodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
	private Integer id;
	
	@Column(name="CITY", length=100, nullable=false)
	private String city;
	
	@Column(name="CITY_ABBREVIATION", length=50)
	private String cityAbberivation;
	
	@Column(name="CITY_KEY", length=10)
	private String cityKey;
	
	@Column(name="COUNTY", length=50)
	private String country;
	
	@Column(name="MAILCODE", length=11, nullable=false)
	private String mailCode;
	
	@Column(name="STATE", length=50, nullable=false)
	private String state;
	
	@Enumerated(EnumType.STRING)    
	@Column(name="STATUS",columnDefinition = "enum ('A', 'I') default 'A'", nullable=false)
	private StatusEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityAbberivation() {
		return cityAbberivation;
	}

	public void setCityAbberivation(String cityAbberivation) {
		this.cityAbberivation = cityAbberivation;
	}

	public String getCityKey() {
		return cityKey;
	}

	public void setCityKey(String cityKey) {
		this.cityKey = cityKey;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
