/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MyPC
 */
@Entity
@Table(name = "Account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountID", query = "SELECT a FROM Account a WHERE a.accountID = :accountID"),
    @NamedQuery(name = "Account.findByAcountAmount", query = "SELECT a FROM Account a WHERE a.acountAmount = :acountAmount"),
    @NamedQuery(name = "Account.findByDateOpen", query = "SELECT a FROM Account a WHERE a.dateOpen = :dateOpen"),
    @NamedQuery(name = "Account.findByDateStart", query = "SELECT a FROM Account a WHERE a.dateStart = :dateStart"),
    @NamedQuery(name = "Account.findByIsStatus", query = "SELECT a FROM Account a WHERE a.isStatus = :isStatus")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AccountID")
    private Integer accountID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AcountAmount")
    private BigDecimal acountAmount;
    @Column(name = "dateOpen")
    @Temporal(TemporalType.DATE)
    private Date dateOpen;
    @Column(name = "dateStart")
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Column(name = "isStatus")
    private Integer isStatus;
    @JoinColumn(name = "AccountType", referencedColumnName = "AccountType")
    @ManyToOne
    private AccountType accountType;
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    @ManyToOne
    private Customer customerID;
    @OneToMany(mappedBy = "accountID")
    private List<AccountLog> accountLogList;
    @OneToMany(mappedBy = "accountID")
    private List<TranferLog> tranferLogList;

    public Account() {
    }

    public Account(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public BigDecimal getAcountAmount() {
        return acountAmount;
    }

    public void setAcountAmount(BigDecimal acountAmount) {
        this.acountAmount = acountAmount;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Integer isStatus) {
        this.isStatus = isStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    @XmlTransient
    public List<AccountLog> getAccountLogList() {
        return accountLogList;
    }

    public void setAccountLogList(List<AccountLog> accountLogList) {
        this.accountLogList = accountLogList;
    }

    @XmlTransient
    public List<TranferLog> getTranferLogList() {
        return tranferLogList;
    }

    public void setTranferLogList(List<TranferLog> tranferLogList) {
        this.tranferLogList = tranferLogList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountID != null ? accountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountID == null && other.accountID != null) || (this.accountID != null && !this.accountID.equals(other.accountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Account[ accountID=" + accountID + " ]";
    }
    
}
