/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
@Table(name = "Card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c"),
    @NamedQuery(name = "Card.findByCardID", query = "SELECT c FROM Card c WHERE c.cardID = :cardID"),
    @NamedQuery(name = "Card.findByDateOpen", query = "SELECT c FROM Card c WHERE c.dateOpen = :dateOpen"),
    @NamedQuery(name = "Card.findByDateStart", query = "SELECT c FROM Card c WHERE c.dateStart = :dateStart"),
    @NamedQuery(name = "Card.findByIStatus", query = "SELECT c FROM Card c WHERE c.iStatus = :iStatus")})
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cardID")
    private Integer cardID;
    @Column(name = "dateOpen")
    @Temporal(TemporalType.DATE)
    private Date dateOpen;
    @Column(name = "dateStart")
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Column(name = "iStatus")
    private Integer iStatus;
    @JoinColumn(name = "CardType", referencedColumnName = "CardType")
    @ManyToOne
    private Cardtype cardType;
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    @ManyToOne
    private Customer customerID;

    public Card() {
    }

    public Card(Integer cardID) {
        this.cardID = cardID;
    }

    public Integer getCardID() {
        return cardID;
    }

    public void setCardID(Integer cardID) {
        this.cardID = cardID;
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

    public Integer getIStatus() {
        return iStatus;
    }

    public void setIStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    public Cardtype getCardType() {
        return cardType;
    }

    public void setCardType(Cardtype cardType) {
        this.cardType = cardType;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardID != null ? cardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.cardID == null && other.cardID != null) || (this.cardID != null && !this.cardID.equals(other.cardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Card[ cardID=" + cardID + " ]";
    }
    
}
