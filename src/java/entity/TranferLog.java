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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MyPC
 */
@Entity
@Table(name = "TranferLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TranferLog.findAll", query = "SELECT t FROM TranferLog t"),
    @NamedQuery(name = "TranferLog.findByTranferLogID", query = "SELECT t FROM TranferLog t WHERE t.tranferLogID = :tranferLogID"),
    @NamedQuery(name = "TranferLog.findByAccountID1", query = "SELECT t FROM TranferLog t WHERE t.accountID1 = :accountID1"),
    @NamedQuery(name = "TranferLog.findByAmount", query = "SELECT t FROM TranferLog t WHERE t.amount = :amount"),
    @NamedQuery(name = "TranferLog.findByTranferLogType", query = "SELECT t FROM TranferLog t WHERE t.tranferLogType = :tranferLogType"),
    @NamedQuery(name = "TranferLog.findByTranferDate", query = "SELECT t FROM TranferLog t WHERE t.tranferDate = :tranferDate"),
    @NamedQuery(name = "TranferLog.findByNote", query = "SELECT t FROM TranferLog t WHERE t.note = :note")})
public class TranferLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tranferLogID")
    private Integer tranferLogID;
    @Column(name = "AccountID1")
    private Integer accountID1;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "tranferLogType")
    private Integer tranferLogType;
    @Column(name = "tranferDate")
    @Temporal(TemporalType.DATE)
    private Date tranferDate;
    @Size(max = 50)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Account accountID;

    public TranferLog() {
    }

    public TranferLog(Integer tranferLogID) {
        this.tranferLogID = tranferLogID;
    }

    public Integer getTranferLogID() {
        return tranferLogID;
    }

    public void setTranferLogID(Integer tranferLogID) {
        this.tranferLogID = tranferLogID;
    }

    public Integer getAccountID1() {
        return accountID1;
    }

    public void setAccountID1(Integer accountID1) {
        this.accountID1 = accountID1;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTranferLogType() {
        return tranferLogType;
    }

    public void setTranferLogType(Integer tranferLogType) {
        this.tranferLogType = tranferLogType;
    }

    public Date getTranferDate() {
        return tranferDate;
    }

    public void setTranferDate(Date tranferDate) {
        this.tranferDate = tranferDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        if (!(object instanceof TranferLog)) {
            return false;
        }
        TranferLog other = (TranferLog) object;
        if ((this.tranferLogID == null && other.tranferLogID != null) || (this.tranferLogID != null && !this.tranferLogID.equals(other.tranferLogID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TranferLog[ tranferLogID=" + tranferLogID + " ]";
    }
    
}
