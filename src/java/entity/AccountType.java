/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "AccountType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountType.findAll", query = "SELECT a FROM AccountType a"),
    @NamedQuery(name = "AccountType.findByAccountType", query = "SELECT a FROM AccountType a WHERE a.accountType = :accountType"),
    @NamedQuery(name = "AccountType.findByInterestRate", query = "SELECT a FROM AccountType a WHERE a.interestRate = :interestRate"),
    @NamedQuery(name = "AccountType.findByRangeMonth", query = "SELECT a FROM AccountType a WHERE a.rangeMonth = :rangeMonth"),
    @NamedQuery(name = "AccountType.findByDescriptions", query = "SELECT a FROM AccountType a WHERE a.descriptions = :descriptions")})
public class AccountType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AccountType")
    private String accountType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "interestRate")
    private Double interestRate;
    @Column(name = "rangeMonth")
    private Integer rangeMonth;
    @Size(max = 300)
    @Column(name = "descriptions")
    private String descriptions;
    @OneToMany(mappedBy = "accountType")
    private List<Account> accountList;

    public AccountType() {
    }

    public AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getRangeMonth() {
        return rangeMonth;
    }

    public void setRangeMonth(Integer rangeMonth) {
        this.rangeMonth = rangeMonth;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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
        hash += (accountType != null ? accountType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountType)) {
            return false;
        }
        AccountType other = (AccountType) object;
        if ((this.accountType == null && other.accountType != null) || (this.accountType != null && !this.accountType.equals(other.accountType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccountType[ accountType=" + accountType + " ]";
    }
    
}
