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
@Table(name = "Cardtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cardtype.findAll", query = "SELECT c FROM Cardtype c"),
    @NamedQuery(name = "Cardtype.findByCardType", query = "SELECT c FROM Cardtype c WHERE c.cardType = :cardType"),
    @NamedQuery(name = "Cardtype.findByInterestRate", query = "SELECT c FROM Cardtype c WHERE c.interestRate = :interestRate"),
    @NamedQuery(name = "Cardtype.findByRangeMonth", query = "SELECT c FROM Cardtype c WHERE c.rangeMonth = :rangeMonth"),
    @NamedQuery(name = "Cardtype.findByDescriptions", query = "SELECT c FROM Cardtype c WHERE c.descriptions = :descriptions")})
public class Cardtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CardType")
    private String cardType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "interestRate")
    private Double interestRate;
    @Column(name = "rangeMonth")
    private Integer rangeMonth;
    @Size(max = 300)
    @Column(name = "descriptions")
    private String descriptions;
    @OneToMany(mappedBy = "cardType")
    private Collection<Card> cardCollection;

    public Cardtype() {
    }

    public Cardtype(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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
    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(Collection<Card> cardCollection) {
        this.cardCollection = cardCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardType != null ? cardType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cardtype)) {
            return false;
        }
        Cardtype other = (Cardtype) object;
        if ((this.cardType == null && other.cardType != null) || (this.cardType != null && !this.cardType.equals(other.cardType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cardtype[ cardType=" + cardType + " ]";
    }
    
}
