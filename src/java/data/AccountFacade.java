/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Account;
import entity.Customer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MyPC
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "Project2_hoangPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
  
   public List<Account> getAccountBySearch(String accountType, String searchString) {
        return em.createQuery("SELECT a FROM Account a WHERE a." + accountType + " like :account").setParameter("account", "%" + searchString + "%").getResultList();
    }
   public List<Account> getAccountBySearchInt(String customerType, Integer searchString) {
        return em.createQuery("SELECT a FROM Account a WHERE a." + customerType + " = :account").setParameter("account",  searchString).getResultList();
    }
   public List<Account> getAccountBySearchBigInt(String customerType, BigDecimal searchString) {
        return em.createQuery("SELECT a FROM Account a WHERE a." + customerType + " = :account").setParameter("account",  searchString).getResultList();
    }
    public boolean checkAccount(Integer accountID) {
       try {
            em.createQuery("SELECT a FROM Account a WHERE a.accountID = :accountID").setParameter("accountID", accountID).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
