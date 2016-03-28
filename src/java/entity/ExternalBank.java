/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MyPC
 */
@Entity
@Table(name = "ExternalBank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalBank.findAll", query = "SELECT e FROM ExternalBank e"),
    @NamedQuery(name = "ExternalBank.findByExternalBankID", query = "SELECT e FROM ExternalBank e WHERE e.externalBankID = :externalBankID"),
    @NamedQuery(name = "ExternalBank.findByExternalBankName", query = "SELECT e FROM ExternalBank e WHERE e.externalBankName = :externalBankName")})
public class ExternalBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ExternalBankID")
    private Integer externalBankID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ExternalBankName")
    private String externalBankName;
    @OneToMany(mappedBy = "externalBankID")
    private Collection<ExternalTranferLog> externalTranferLogCollection;

    public ExternalBank() {
    }

    public ExternalBank(Integer externalBankID) {
        this.externalBankID = externalBankID;
    }

    public ExternalBank(Integer externalBankID, String externalBankName) {
        this.externalBankID = externalBankID;
        this.externalBankName = externalBankName;
    }

    public Integer getExternalBankID() {
        return externalBankID;
    }

    public void setExternalBankID(Integer externalBankID) {
        this.externalBankID = externalBankID;
    }

    public String getExternalBankName() {
        return externalBankName;
    }

    public void setExternalBankName(String externalBankName) {
        this.externalBankName = externalBankName;
    }

    @XmlTransient
    public Collection<ExternalTranferLog> getExternalTranferLogCollection() {
        return externalTranferLogCollection;
    }

    public void setExternalTranferLogCollection(Collection<ExternalTranferLog> externalTranferLogCollection) {
        this.externalTranferLogCollection = externalTranferLogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (externalBankID != null ? externalBankID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalBank)) {
            return false;
        }
        ExternalBank other = (ExternalBank) object;
        if ((this.externalBankID == null && other.externalBankID != null) || (this.externalBankID != null && !this.externalBankID.equals(other.externalBankID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExternalBank[ externalBankID=" + externalBankID + " ]";
    }
    
}
