/**
 * 
 */
package com.mart.entity;

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

import com.mart.utilities.SexEnum;
import com.mart.utilities.StatusEnum;

/**
 * @author Hitesh
 *
 */
@Entity
@Table(name="USER")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
	
	 	@Column(name = "ADDRESS", length = 100)
	    private String address;
	 	
	    @Column(name = "CITY", length = 50)
	    private String city;
	    
	    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = false)
	    @ManyToOne(fetch = FetchType.LAZY)
		private User createdBy;
	    
	    @Temporal(TemporalType.TIMESTAMP)
		@Column(name="CREATED_DATE_TIME")
		private Date createdDateTime;
	    
	    @Column(name = "DOB")
	    @Temporal(TemporalType.DATE)
	    private Date dob;
	    
	    @Column(name = "EMAIL", nullable = false, length = 50, unique=true)
	    private String email;
	    
	    @Column(name = "END_DATE")
	    @Temporal(TemporalType.DATE)
	    private Date endDate;
	    
	    @Column(name = "FAX", length = 20)
	    private String Fax;
	    
	    @Column(name = "FIRST_NAME", length = 100 , nullable = false)
	    private String firstName;
	    
	    @Column(name = "HPHONE", length = 20)
	    private String hphone;
	    
		@Column(name = "JOIN_DATE")
	    @Temporal(TemporalType.DATE)
	    private Date joinDate;
		
	    @Column(name = "LAST_NAME", length = 100, nullable = false)
	    private String lastName;
	    
	    @JoinColumn(name = "LAST_UPDATED_BY", referencedColumnName = "ID")
	    @ManyToOne(fetch = FetchType.LAZY)
		private User lastUpdatedBy;
	    
	    @Temporal(TemporalType.TIMESTAMP)
		@Column(name="LAST_UPDATED_DATE_TIME")
		private Date lastUpdatedDateTime;
	    
	    @JoinColumn(name = "LEVEL", referencedColumnName = "ID", nullable = false)
	    @ManyToOne(fetch = FetchType.LAZY)
	    private TypeItems level;
	    
	    @Column(name = "MIDDLE_NAME", length = 100)
	    private String middleName;
	    
	    @Column(name = "PASSWORD", length = 255, nullable = false)
	    private String password;
	    
	    @Column(name = "PHONE", length = 20)
	    private String phone;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "SEX", columnDefinition = "enum ('M', 'F','O') default 'M'")
	    private SexEnum sex;
	    
	    @Column(name = "SSN", length = 15)
	    private String ssn; 
	    
	    @Column(name = "STATE", length = 2)
	    private String state;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name="STATUS",columnDefinition = "enum ('A', 'I') default 'A'", nullable=false)
	    private StatusEnum statusEnum;
	    
	    @Column(name="UPDATED_COUNT", columnDefinition = "int default 0")
		private Integer updatedCount;
	    
	    @Column(name = "USER_NAME", nullable = false, length = 50, unique=true)
	    private String userName;
	    
	    @Column(name = "WPHONE", length = 20)
	    private String wphone;
	    
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

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
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

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public String getFax() {
			return Fax;
		}

		public void setFax(String fax) {
			Fax = fax;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getHphone() {
			return hphone;
		}

		public void setHphone(String hphone) {
			this.hphone = hphone;
		}

		public Date getJoinDate() {
			return joinDate;
		}

		public void setJoinDate(Date joinDate) {
			this.joinDate = joinDate;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
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

		public TypeItems getLevel() {
			return level;
		}

		public void setLevel(TypeItems level) {
			this.level = level;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public SexEnum getSex() {
			return sex;
		}

		public void setSex(SexEnum sex) {
			this.sex = sex;
		}

		public String getSsn() {
			return ssn;
		}

		public void setSsn(String ssn) {
			this.ssn = ssn;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public StatusEnum getStatusEnum() {
			return statusEnum;
		}

		public void setStatusEnum(StatusEnum statusEnum) {
			this.statusEnum = statusEnum;
		}

		public Integer getUpdatedCount() {
			return updatedCount;
		}

		public void setUpdatedCount(Integer updatedCount) {
			this.updatedCount = updatedCount;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getWphone() {
			return wphone;
		}

		public void setWphone(String wphone) {
			this.wphone = wphone;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}

}
