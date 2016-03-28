/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Customer;
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
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Project2_hoangPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    public boolean checkUser(String userName, String userPass) {
        try {
            em.createQuery("SELECT c FROM Customer c WHERE c.userName = :userName and c.userPass = :userPass").setParameter("userName", userName).setParameter("userPass", userPass).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean checkVerify(String userName) {
        try {
            em.createQuery("SELECT c FROM Customer c WHERE c.userName = :userName and c.isVerify = 1").setParameter("userName", userName).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean checkStatus(String userName) {
        try {
            em.createQuery("SELECT c FROM Customer c WHERE c.userName = :userName and c.isStatus = 1").setParameter("userName", userName).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
   public boolean checkID(BigInteger id) {
        try {
            em.createNamedQuery("Customer.findByIdentityNumber").setParameter("identityNumber", id).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
      public boolean checkExistUserName(String userName) {
       try {
            em.createQuery("SELECT c FROM Customer c WHERE c.userName = :userName").setParameter("userName", userName).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    public Customer findbyUserName(String userName) {
        return (Customer) em.createNamedQuery("Customer.findByUserName").setParameter("userName", userName).getSingleResult();
    }
     public Customer findbyCustomerEmail(String customerEmail) {
        return (Customer) em.createNamedQuery("Customer.findByCustomerEmail").setParameter("customerEmail", customerEmail).getSingleResult();
    }
   public List<Customer> getCustomerBySearch(String customerType, String searchString) {
        return em.createQuery("SELECT c FROM Customer c WHERE c." + customerType + " like :customer").setParameter("customer", "%" + searchString + "%").getResultList();
    }
   public List<Customer> getCustomerBySearchID(String customerType, Integer searchString) {
        return em.createQuery("SELECT c FROM Customer c WHERE c." + customerType + " = :customer").setParameter("customer",  searchString).getResultList();
    }
   public List<Customer> getCustomerBySearchBigInt(String customerType, BigInteger searchString) {
        return em.createQuery("SELECT c FROM Customer c WHERE c." + customerType + " = :customer").setParameter("customer",  searchString).getResultList();
    }
}
