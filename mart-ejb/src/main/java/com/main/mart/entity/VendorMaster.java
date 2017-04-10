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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.main.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="VENDOR_MASTER")
public class VendorMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
	
	@Column(name="ACCOUNT_NO", length=50)
	private String accountNo;

	@Column(name="ADDRESS1", length=100)
	private String address1;

	@Column(name="ADDRESS2", length=100)
	private String address2;

	@Column(name="ADDRESS3", length=100)
	private String address3;

	@Column(name="CITY", length=20)
	private String city;

	@Column(name="CONTACT_PERSON", length=50)
	private String contactPerson;

	@Column(name="CST_NO", length=30)
	private String cstNo;

	@Column(name="EMAIL_ADDRESS", length=60)
	private String emailAddress;

	@Column(name="FAX_NO", length=20)
	private String faxNo;
	
	@Column(name="MOBILE_NO", length=15)
	private String mobileNo;

	@Column(name="PHONE1", length=15)
	private String phone1;

	@Column(name="PHONE2", length=15)
	private String phone2;

	@Column(name = "RATING", length=10)
	private Integer rating;

	@Column(name="REMARKS", length=250)
	private String remarks;

	@Column(name="STATE", length=20)
	private String state;
	
	@Enumerated(EnumType.STRING)    
	@Column(name = "STATUS",columnDefinition = "enum ('A', 'I') default 'A'", nullable=false)
	private StatusEnum status;
	
	@JoinColumn(name = "STORE_MASTER_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private StoreMaster storeMasterId;
	
	@Column(name="TIN_NO", length=30)
	private String tinNo;
	
	@Column(name="VENDOR_CODE", length=10)
	private String vendorCode;
	
	@Column(name="VENDOR_NAME", length=50)
	private String vendorName;
	
	@JoinColumn(name = "VENDOR_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private TypeItems vendorType;
	
	@Column(name="ZIP", length=10)
	private String zip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCstNo() {
		return cstNo;
	}

	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public StoreMaster getStoreMasterId() {
		return storeMasterId;
	}

	public void setStoreMasterId(StoreMaster storeMasterId) {
		this.storeMasterId = storeMasterId;
	}

	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public TypeItems getVendorType() {
		return vendorType;
	}

	public void setVendorType(TypeItems vendorType) {
		this.vendorType = vendorType;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
