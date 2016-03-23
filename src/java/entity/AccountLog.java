/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MyPC
 */
@Entity
@Table(name = "AccountLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountLog.findAll", query = "SELECT a FROM AccountLog a"),
    @NamedQuery(name = "AccountLog.findByTranferLogID", query = "SELECT a FROM AccountLog a WHERE a.tranferLogID = :tranferLogID"),
    @NamedQuery(name = "AccountLog.findByAmount", query = "SELECT a FROM AccountLog a WHERE a.amount = :amount"),
    @NamedQuery(name = "AccountLog.findByLogType", query = "SELECT a FROM AccountLog a WHERE a.logType = :logType"),
    @NamedQuery(name = "AccountLog.findByLogDate", query = "SELECT a FROM AccountLog a WHERE a.logDate = :logDate")})
public class AccountLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tranferLogID")
    private Integer tranferLogID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "logType")
    private Integer logType;
    @Column(name = "logDate")
    @Temporal(TemporalType.DATE)
    private Date logDate;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Account accountID;

    public AccountLog() {
    }

    public AccountLog(Integer tranferLogID) {
        this.tranferLogID = tranferLogID;
    }

    public Integer getTranferLogID() {
        return tranferLogID;
    }

    public void setTranferLogID(Integer tranferLogID) {
        this.tranferLogID = tranferLogID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tranferLogID != null ? tranferLogID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountLog)) {
            return false;
        }
        AccountLog other = (AccountLog) object;
        if ((this.tranferLogID == null && other.tranferLogID != null) || (this.tranferLogID != null && !this.tranferLogID.equals(other.tranferLogID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccountLog[ tranferLogID=" + tranferLogID + " ]";
    }
    
}
