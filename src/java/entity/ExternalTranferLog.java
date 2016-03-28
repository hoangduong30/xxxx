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
@Table(name = "ExternalTranferLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalTranferLog.findAll", query = "SELECT e FROM ExternalTranferLog e"),
    @NamedQuery(name = "ExternalTranferLog.findByExternalTranferLogID", query = "SELECT e FROM ExternalTranferLog e WHERE e.externalTranferLogID = :externalTranferLogID"),
    @NamedQuery(name = "ExternalTranferLog.findByExternalAccountID", query = "SELECT e FROM ExternalTranferLog e WHERE e.externalAccountID = :externalAccountID"),
    @NamedQuery(name = "ExternalTranferLog.findByExternalAmount", query = "SELECT e FROM ExternalTranferLog e WHERE e.externalAmount = :externalAmount"),
    @NamedQuery(name = "ExternalTranferLog.findByExternalTranferType", query = "SELECT e FROM ExternalTranferLog e WHERE e.externalTranferType = :externalTranferType"),
    @NamedQuery(name = "ExternalTranferLog.findByExternalTranferDate", query = "SELECT e FROM ExternalTranferLog e WHERE e.externalTranferDate = :externalTranferDate"),
    @NamedQuery(name = "ExternalTranferLog.findByExternalTranferNote", query = "SELECT e FROM ExternalTranferLog e WHERE e.externalTranferNote = :externalTranferNote"),
    @NamedQuery(name = "ExternalTranferLog.findByNoteLog", query = "SELECT e FROM ExternalTranferLog e WHERE e.noteLog = :noteLog")})
public class ExternalTranferLog implements Serializable {

    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Account accountID;
    @JoinColumn(name = "ExternalBankID", referencedColumnName = "ExternalBankID")
    @ManyToOne
    private ExternalBank externalBankID;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ExternalTranferLogID")
    private Integer externalTranferLogID;
    @Column(name = "ExternalAccountID")
    private Integer externalAccountID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ExternalAmount")
    private BigDecimal externalAmount;
    @Column(name = "ExternalTranferType")
    private Boolean externalTranferType;
    @Column(name = "ExternalTranferDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date externalTranferDate;
    @Size(max = 350)
    @Column(name = "ExternalTranferNote")
    private String externalTranferNote;
    @Size(max = 150)
    @Column(name = "noteLog")
    private String noteLog;

    public ExternalTranferLog() {
    }

    public ExternalTranferLog(Integer externalTranferLogID) {
        this.externalTranferLogID = externalTranferLogID;
    }

    public Integer getExternalTranferLogID() {
        return externalTranferLogID;
    }

    public void setExternalTranferLogID(Integer externalTranferLogID) {
        this.externalTranferLogID = externalTranferLogID;
    }

    public Integer getExternalAccountID() {
        return externalAccountID;
    }

    public void setExternalAccountID(Integer externalAccountID) {
        this.externalAccountID = externalAccountID;
    }

    public BigDecimal getExternalAmount() {
        return externalAmount;
    }

    public void setExternalAmount(BigDecimal externalAmount) {
        this.externalAmount = externalAmount;
    }

    public Boolean getExternalTranferType() {
        return externalTranferType;
    }

    public void setExternalTranferType(Boolean externalTranferType) {
        this.externalTranferType = externalTranferType;
    }

    public Date getExternalTranferDate() {
        return externalTranferDate;
    }

    public void setExternalTranferDate(Date externalTranferDate) {
        this.externalTranferDate = externalTranferDate;
    }

    public String getExternalTranferNote() {
        return externalTranferNote;
    }

    public void setExternalTranferNote(String externalTranferNote) {
        this.externalTranferNote = externalTranferNote;
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
        hash += (externalTranferLogID != null ? externalTranferLogID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalTranferLog)) {
            return false;
        }
        ExternalTranferLog other = (ExternalTranferLog) object;
        if ((this.externalTranferLogID == null && other.externalTranferLogID != null) || (this.externalTranferLogID != null && !this.externalTranferLogID.equals(other.externalTranferLogID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExternalTranferLog[ externalTranferLogID=" + externalTranferLogID + " ]";
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
    }

    public ExternalBank getExternalBankID() {
        return externalBankID;
    }

    public void setExternalBankID(ExternalBank externalBankID) {
        this.externalBankID = externalBankID;
    }
    
}
