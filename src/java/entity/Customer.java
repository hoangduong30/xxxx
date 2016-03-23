/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MyPC
 */
@Entity
@Table(name = "Customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomerID", query = "SELECT c FROM Customer c WHERE c.customerID = :customerID"),
    @NamedQuery(name = "Customer.findByCustomerName", query = "SELECT c FROM Customer c WHERE c.customerName = :customerName"),
    @NamedQuery(name = "Customer.findByCustomerAddress", query = "SELECT c FROM Customer c WHERE c.customerAddress = :customerAddress"),
    @NamedQuery(name = "Customer.findByCustomerPhone", query = "SELECT c FROM Customer c WHERE c.customerPhone = :customerPhone"),
    @NamedQuery(name = "Customer.findByCustomerEmail", query = "SELECT c FROM Customer c WHERE c.customerEmail = :customerEmail"),
    @NamedQuery(name = "Customer.findByIsVerify", query = "SELECT c FROM Customer c WHERE c.isVerify = :isVerify"),
    @NamedQuery(name = "Customer.findByIdentityNumber", query = "SELECT c FROM Customer c WHERE c.identityNumber = :identityNumber"),
    @NamedQuery(name = "Customer.findByUserName", query = "SELECT c FROM Customer c WHERE c.userName = :userName"),
    @NamedQuery(name = "Customer.findByUserPass", query = "SELECT c FROM Customer c WHERE c.userPass = :userPass"),
    @NamedQuery(name = "Customer.findByDateRegister", query = "SELECT c FROM Customer c WHERE c.dateRegister = :dateRegister"),
    @NamedQuery(name = "Customer.findByIsReset", query = "SELECT c FROM Customer c WHERE c.isReset = :isReset"),
    @NamedQuery(name = "Customer.findByResetCode", query = "SELECT c FROM Customer c WHERE c.resetCode = :resetCode"),
    @NamedQuery(name = "Customer.findByIsStatus", query = "SELECT c FROM Customer c WHERE c.isStatus = :isStatus")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "customerID")
    private Integer customerID;
    @Size(max = 200)
    @Column(name = "customerName")
    private String customerName;
    @Size(max = 200)
    @Column(name = "customerAddress")
    private String customerAddress;
    @Column(name = "customerPhone")
    private BigInteger customerPhone;
    @Size(max = 50)
    @Column(name = "customerEmail")
    private String customerEmail;
    @Column(name = "isVerify")
    private Integer isVerify;
    @Column(name = "identityNumber")
    private BigInteger identityNumber;
    @Size(max = 50)
    @Column(name = "userName")
    private String userName;
    @Size(max = 50)
    @Column(name = "userPass")
    private String userPass;
    @Column(name = "dateRegister")
    @Temporal(TemporalType.DATE)
    private Date dateRegister;
    @Column(name = "isReset")
    private Integer isReset;
    @Size(max = 50)
    @Column(name = "resetCode")
    private String resetCode;
    @Column(name = "isStatus")
    private Integer isStatus;
    @OneToMany(mappedBy = "customerID")
    private List<Account> accountList;

    public Customer() {
    }

    public Customer(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public BigInteger getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(BigInteger customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public BigInteger getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(BigInteger identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Integer getIsReset() {
        return isReset;
    }

    public void setIsReset(Integer isReset) {
        this.isReset = isReset;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public Integer getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Integer isStatus) {
        this.isStatus = isStatus;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerID != null ? customerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerID == null && other.customerID != null) || (this.customerID != null && !this.customerID.equals(other.customerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ customerID=" + customerID + " ]";
    }
    
}
