/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Card;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MyPC
 */
@Stateless
public class CardFacade extends AbstractFacade<Card> {

    @PersistenceContext(unitName = "Project2_hoangPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CardFacade() {
        super(Card.class);
    }
    
}
