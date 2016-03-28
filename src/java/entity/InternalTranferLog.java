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
@Table(name = "InternalTranferLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InternalTranferLog.findAll", query = "SELECT i FROM InternalTranferLog i"),
    @NamedQuery(name = "InternalTranferLog.findByInternalTranferLogID", query = "SELECT i FROM InternalTranferLog i WHERE i.internalTranferLogID = :internalTranferLogID"),
    @NamedQuery(name = "InternalTranferLog.findByInternalAmount", query = "SELECT i FROM InternalTranferLog i WHERE i.internalAmount = :internalAmount"),
    @NamedQuery(name = "InternalTranferLog.findByInternalTranferType", query = "SELECT i FROM InternalTranferLog i WHERE i.internalTranferType = :internalTranferType"),
    @NamedQuery(name = "InternalTranferLog.findByInternalTranferDate", query = "SELECT i FROM InternalTranferLog i WHERE i.internalTranferDate = :internalTranferDate"),
    @NamedQuery(name = "InternalTranferLog.findByInternalTranferNote", query = "SELECT i FROM InternalTranferLog i WHERE i.internalTranferNote = :internalTranferNote"),
    @NamedQuery(name = "InternalTranferLog.findByNoteLog", query = "SELECT i FROM InternalTranferLog i WHERE i.noteLog = :noteLog")})
public class InternalTranferLog implements Serializable {

    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Account accountID;
    @JoinColumn(name = "InternalAccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Account internalAccountID;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "InternalTranferLogID")
    private Integer internalTranferLogID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "InternalAmount")
    private BigDecimal internalAmount;
    @Column(name = "InternalTranferType")
    private Boolean internalTranferType;
    @Column(name = "InternalTranferDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date internalTranferDate;
    @Size(max = 350)
    @Column(name = "InternalTranferNote")
    private String internalTranferNote;
    @Size(max = 150)
    @Column(name = "noteLog")
    private String noteLog;

    public InternalTranferLog() {
    }

    public InternalTranferLog(Integer internalTranferLogID) {
        this.internalTranferLogID = internalTranferLogID;
    }

    public Integer getInternalTranferLogID() {
        return internalTranferLogID;
    }

    public void setInternalTranferLogID(Integer internalTranferLogID) {
        this.internalTranferLogID = internalTranferLogID;
    }

    public BigDecimal getInternalAmount() {
        return internalAmount;
    }

    public void setInternalAmount(BigDecimal internalAmount) {
        this.internalAmount = internalAmount;
    }

    public Boolean getInternalTranferType() {
        return internalTranferType;
    }

    public void setInternalTranferType(Boolean internalTranferType) {
        this.internalTranferType = internalTranferType;
    }

    public Date getInternalTranferDate() {
        return internalTranferDate;
    }

    public void setInternalTranferDate(Date internalTranferDate) {
        this.internalTranferDate = internalTranferDate;
    }

    public String getInternalTranferNote() {
        return internalTranferNote;
    }

    public void setInternalTranferNote(String internalTranferNote) {
        this.internalTranferNote = internalTranferNote;
    }

    public String getNoteLog() {
        return noteLog;
    }

    public void setNoteLog(String noteLog) {
        this.noteLog = noteLog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (internalTranferLogID != null ? internalTranferLogID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InternalTranferLog)) {
            return false;
        }
        InternalTranferLog other = (InternalTranferLog) object;
        if ((this.internalTranferLogID == null && other.internalTranferLogID != null) || (this.internalTranferLogID != null && !this.internalTranferLogID.equals(other.internalTranferLogID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.InternalTranferLog[ internalTranferLogID=" + internalTranferLogID + " ]";
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
    }

    public Account getInternalAccountID() {
        return internalAccountID;
    }

    public void setInternalAccountID(Account internalAccountID) {
        this.internalAccountID = internalAccountID;
    }
    
}
