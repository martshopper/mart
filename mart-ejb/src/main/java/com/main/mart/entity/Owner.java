/**
 * 
 */
package com.main.mart.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.main.mart.utilities.CommonEnum;
import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="OWNER")
public class Owner implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	private Integer id;	
	
	@Column(name = "ADDRESS", length = 200)
	private String address;
	
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;   
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE_TIME", nullable = false)
	private Date createdDateTime;
    
	@Column(name = "CITY", length = 100)
	private String city;
	
	@Column(name = "CLOSE_TIME", length = 10)
	private String closingTime;
	
	@Column(name = "EMAIL", length = 100)
    private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "FRIDAY", columnDefinition = "enum ('Y', 'N') default 'N'",nullable = false)
	private CommonEnum friday;
	
	@Column(name = "FULL_NAME", length = 100, nullable = false)
	private String fullName;
	
	@JoinColumn(name = "LAST_UPDATED_BY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
	private User lastUpdatedBy;    
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED_DATE_TIME")
	private Date lastUpdatedDateTime;
    
    @Enumerated(EnumType.STRING)
	@Column(name = "MONDAY", columnDefinition = "enum ('Y', 'N') default 'N'",nullable = false)
	private CommonEnum monday;
	
	@Column(name = "OPENING_TIME", length = 10)
	private String openingTime;
	
	@Column(name = "PHONE", length = 15)
	private String phone;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SATURDAY", columnDefinition = "enum ('Y', 'N') default 'N'",nullable = false)
	private CommonEnum saturday;
	
	@Column(name = "SHORT_NAME", length = 20, nullable = false, unique=true)
	private String shortName;
	
	@Column(name = "STATE", length = 50)
	private String state;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS",columnDefinition = "enum ('A', 'I') default 'A'",nullable = false)
	private StatusEnum status;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SUNDAY", columnDefinition = "enum ('Y', 'N') default 'N'",nullable = false)
	private CommonEnum sunday;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "THURSDAY", columnDefinition = "enum ('Y', 'N') default 'N'",nullable = false)
	private CommonEnum thursday;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TUESDAY", columnDefinition = "enum ('Y', 'N') default 'N'",nullable = false)
	private CommonEnum tuesday;
	
	@JoinColumn(name = "TYPE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
	private TypeItems type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "WEDNESDAY", columnDefinition = "enum ('Y', 'N') default 'N'",nullable = false)
	private CommonEnum wednesday;
	
	@Column(name = "WORK_PHONE", length = 15)
	private String wPhone;
	
	@Column(name = "ZIP", length = 10)
	private String zip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CommonEnum getFriday() {
		return friday;
	}

	public void setFriday(CommonEnum friday) {
		this.friday = friday;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public User getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public CommonEnum getMonday() {
		return monday;
	}

	public void setMonday(CommonEnum monday) {
		this.monday = monday;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CommonEnum getSaturday() {
		return saturday;
	}

	public void setSaturday(CommonEnum saturday) {
		this.saturday = saturday;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public CommonEnum getSunday() {
		return sunday;
	}

	public void setSunday(CommonEnum sunday) {
		this.sunday = sunday;
	}

	public CommonEnum getThursday() {
		return thursday;
	}

	public void setThursday(CommonEnum thursday) {
		this.thursday = thursday;
	}

	public CommonEnum getTuesday() {
		return tuesday;
	}

	public void setTuesday(CommonEnum tuesday) {
		this.tuesday = tuesday;
	}

	public TypeItems getType() {
		return type;
	}

	public void setType(TypeItems type) {
		this.type = type;
	}

	public CommonEnum getWednesday() {
		return wednesday;
	}

	public void setWednesday(CommonEnum wednesday) {
		this.wednesday = wednesday;
	}

	public String getwPhone() {
		return wPhone;
	}

	public void setwPhone(String wPhone) {
		this.wPhone = wPhone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
